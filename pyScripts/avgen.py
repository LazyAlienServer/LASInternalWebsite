import numpy as np
import requests
import re
import argparse
from io import BytesIO
import asyncio
import os
from matplotlib import pyplot as plt
import sys

async def get_uuid(player_name):
    try:
        print(f"\r  [step 1/3] Getting uuid for player {player_name}...", end="")
        headers = {'User-Agent': 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/62.0.3202.89 Safari/537.36'}
        uuid_rep = await loop.run_in_executor(None, lambda: requests.get(f"https://mcuuid.net/?q={player_name}", headers=headers))
        uuid_rep.raise_for_status()
        uuid_match = re.search('<input id="results_raw_id".+?value="(.+?)">', uuid_rep.text)
        if uuid_match:
            uuid = uuid_match.groups()[0]
        else:
            print(f"\r  * Player {player_name} not found.")
            return None, None
        print(f"\r  [step 2/3] Getting skin for player {player_name}...", end="")
        skin_rep = await loop.run_in_executor(None, lambda: requests.get(f"https://crafatar.com/skins/{uuid}"))
        skin_rep.raise_for_status()
        print(f"\r  [step 3/3] Saving skin for player {player_name}...", end="")
        return player_name, BytesIO(skin_rep.content)
    except requests.RequestException as e:
        print(f"\r  * Error when getting data for player {player_name}: {e}")
        return None, None
    except Exception as e:
        print(f"\r  * Error: {e}")
        return None, None

def img_array_zoom(array, scaling):
    return np.kron(array, np.ones((scaling, scaling, 1)))

def avatar_generate(skin, size=360):
    if skin is None:
        return None

    if size % 72 != 0:
        raise ValueError("`size` must be a multiple of 72, but `%s`" % size)

    shape = skin.shape
    if len(shape) != 3 or shape[0] not in [32, 64] or shape[1] != 64 or shape[2] not in [3, 4]:
        raise ValueError("Mismatched skin size `%s`" % shape)

    alpha_flag = shape[2] == 3

    avatar_bottom = skin[8:16, 8:16]
    avatar_top = skin[8:16, 40:48]

    pixel_multi = size // 9
    pixel_padding = pixel_multi // 2

    avatar_bottom = np.pad(img_array_zoom(avatar_bottom, pixel_multi), ((pixel_padding, pixel_padding), (pixel_padding, pixel_padding), (0, 0)))
    avatar_top = img_array_zoom(avatar_top, size // 8)

    if alpha_flag:
        alpha_final = alpha_bottom = alpha_top = np.ones((size, size, 1))
    else:
        alpha_bottom = avatar_bottom[:, :, 3].reshape(size, size, 1)
        alpha_top = avatar_top[:, :, 3].reshape(size, size, 1)
        alpha_final = alpha_top + alpha_bottom * (255 - alpha_top) / 255

    alpha_bottom, alpha_top = np.tile(alpha_bottom / 255, 3), np.tile(alpha_top / 255, 3)
    color = np.nan_to_num(avatar_top[:, :, :3] * alpha_top + avatar_bottom[:, :, :3] * alpha_bottom * (1 - alpha_top) / np.tile(alpha_final / 255, 3), 0)

    avatar_new = np.zeros((size, size, 4))
    avatar_new[:, :, :3] = color
    avatar_new[:, :, 3] = alpha_final.reshape(size, size)
    avatar_new = np.uint8(avatar_new)

    return avatar_new

async def save_avatar(player_name_skin_bytes):
    player_name, skin_bytes = player_name_skin_bytes
    if skin_bytes:
        print(f"  [Loading] Generating avatar for player {player_name}...", end="")
        skin = plt.imread(skin_bytes) * 255
        avatar = avatar_generate(skin)
        if avatar is None:
            print(f"\r  * Error generating avatar for player {player_name}.")
            return

        save_path = "avatar"
        try:
            os.makedirs(save_path, exist_ok=True)
            file = os.path.join(save_path, f"AVATAR_{player_name}.png")
            plt.imsave(file, avatar)
            print(f"\r  [Finish] Player {player_name}'s avatar is now saved at {file}.")
        except OSError as e:
            print(f"\r  * Error saving avatar for player {player_name}: {e}")

async def main():
    np.seterr(divide='ignore', invalid='ignore')

    parser = argparse.ArgumentParser(description='Generate avatar for Minecraft player')
    parser.add_argument('player_names', type=str, help='The names of the Minecraft players, separated by comma (,)') 
    args = parser.parse_args()

    if not args.player_names:
        print("Error: No player names provided.")
        sys.exit(1)

    player_names = args.player_names.split(',')
    tasks = [get_uuid(player_name) for player_name in player_names]
    results = await asyncio.gather(*tasks)

    for result in results:
        await save_avatar(result)

if __name__ == "__main__":
    loop = asyncio.get_event_loop()
    loop.run_until_complete(main())

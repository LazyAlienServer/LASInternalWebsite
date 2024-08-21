# MinecraftAvatarGenerator
一个python编写的简易Minecraft双层皮肤的头像生成器，输入玩家昵称获取双层皮肤头像

支持alpha通道（透明度）

## 用法

```
python avgen.py <player_name>
python avgen.py <player_name_1,player_name_2...>
```

## 依赖

```
numpy
matplotlib
requests
nuitka
```

## 使用的api

`mcuuid.net` 用于获取玩家uuid

`crafatar.com` 用于获取玩家皮肤

## 从源码编译

```
python -m nuitka --lto=no --onefile avgen.py
```

## 样例

![image](skin2.png)
![](http://localhost:9000/usersignin/api/getFile/Fanzhitianyu)
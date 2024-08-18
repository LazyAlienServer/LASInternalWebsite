import {request} from '@/main'
import {Ref} from "vue";
import {ElNotification} from "element-plus";
import {getCookie} from "typescript-cookie";

function getUserAvatar(this: any, avatar: Ref<string>): void {
    request.post('usersignin/crud/getAvatarByToken?token=' + getCookie('token'), {}).then(r => {
        avatar.value = String(r.data)
        sessionStorage.setItem("avatar", String(avatar.value))
    }).catch((e) => {
        if (e.data && 'status' in e.data) {
            ElNotification({
                title: "提示",
                message: "LASLog : 获取用户头像失败-" + e.data.status != null ? e.data.status : "unknown error",
                type: "error",
                customClass: "UserInfoError",
                duration: 800
            });
        } else {
            ElNotification({
                title: "提示",
                message: "LASLog : unknown error",
                type: "error",
                customClass: "UserInfoError",
                duration: 800
            });
        }
    });
}

export default getUserAvatar;
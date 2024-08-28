import {request} from '../../../main'
import {Ref} from "vue";
import {ElNotification} from "element-plus";
import {getCookie} from "typescript-cookie";

function getUserInfo(this: any, userInfo: Ref<string>): void {
    request.post('usersignin/crud/getUserByToken?token=' + getCookie('token'), {}).then(r => {
        userInfo.value = String(r.data)
        sessionStorage.setItem("avatar", String(userInfo.value))
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

export default getUserInfo;
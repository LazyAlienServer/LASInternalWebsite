import {Ref} from "vue";
import {request} from "@/main";
import {getCookie} from "typescript-cookie";
import {ElNotification} from "element-plus";
import {SessionStorageKeys, setSessionStorageItem} from "@/store/sessionStorageManager";

function getUserIdByToken(this: any, userId: Ref<number | undefined>): void {
    if (getCookie('token') != undefined) {
        request.post('usersignin/crud/getUserIdByToken/?token=' + getCookie('token'), {}).then(r => {
            userId.value = Number(r.data)
            setSessionStorageItem(SessionStorageKeys.userId, String(userId.value));
            sessionStorage.setItem("userId", String(userId.value))
        }).catch((e) => {
            userId.value = -1
            setSessionStorageItem(SessionStorageKeys.userId, String(userId.value));

            if (e.data && 'status' in e.data) {
                ElNotification({
                    title: "提示",
                    message: "LASLog : 获取用户Id失败-" + e.data.status != null ? e.data.status : "unknown error",
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
    } else {
        userId.value = -1
    }
}

export default getUserIdByToken;
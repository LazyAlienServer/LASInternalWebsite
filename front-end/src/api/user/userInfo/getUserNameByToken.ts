import {Ref} from "vue";
import {request} from "@/main";
import {getCookie} from "typescript-cookie";
import {ElNotification} from "element-plus";
import {SessionStorageKeys, setSessionStorageItem} from "@/store/sessionStorageManager";

function getUserNameByToken(this: any, username: Ref<string | undefined>): void {
    if (getCookie('token') != undefined) {
        request.post('usersignin/crud/getUserNameByToken/?token=' + getCookie('token'), {}).then(r => {
            username.value = String(r.data)
            setSessionStorageItem(SessionStorageKeys.userName, String(username.value));
        }).catch((e) => {
            username.value = "null"
            setSessionStorageItem(SessionStorageKeys.userName, String(username.value));

            if (e.data && 'status' in e.data) {
                ElNotification({
                    title: "提示",
                    message: "LASLog : 获取用户名失败-" + e.data.status != null ? e.data.status : "unknown error",
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
        username.value = "null"
    }
}

export default getUserNameByToken;
import {request} from '@/main'
import router from "@/router";
import {ElNotification} from "element-plus";
import {getCookie} from "typescript-cookie";

function getLoginState(): void {
    request.post('usersignin/crud/getLoginState/?token=' + getCookie('token'), {}).then(r => {
        if (getCookie('token') != undefined) {
            if (Boolean(r.data)) {
                router.replace("/user");
            }
        }
    }).catch(() => {
        ElNotification({
            title: "提示",
            message: "LASLog服务端连接失败",
            type: "error",
            customClass: "ConnectError",
            duration: 800
        });
    });
}

export default getLoginState;
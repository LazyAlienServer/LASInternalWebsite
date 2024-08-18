import {request} from '@/main'
import {Ref} from "vue";
import router from "@/router";
import {ElNotification} from "element-plus";
import {setCookie} from "typescript-cookie";

function UserLogin(this: any, username: string | undefined, password: string | undefined, message: Ref<string>): void {
    request.post('/usersignin/login', {
        "userName": username,
        "password": password
    }).then(r => {
        message.value = r.data.msg;
        if (r.data.code === 200) {
            ElNotification({
                title: "成功",
                message: message.value,
                type: "success",
                customClass: "LoginSuccess",
                duration: 800
            });
            setCookie('token',r.data.data);
            // noinspection JSIgnoredPromiseFromCall
            router.replace("/user");
        } else {
            ElNotification({
                title: "失败",
                message: message.value,
                type: "error",
                customClass: "LoginError",
                duration: 800
            });
        }
    }).catch((e) => {
        if (e.data && 'status' in e.data) {
            ElNotification({
                title: "提示",
                message: "LASLog : 用户登录失败-" + e.data.status != null ? e.data.status : "unknown error",
                type: "error",
                customClass: "UserInfoError",
                duration: 800
            });
        } else {
            ElNotification({
                title: "提示",
                message: "LASLog : unknown error",
                type: "error",
                customClass: "ConnectError",
                duration: 800
            });
        }
    });
}

export default UserLogin;
import {request} from '@/main'
import {Ref} from "vue";
import router from "@/router";
import {ElNotification} from "element-plus";
import {setCookie} from "typescript-cookie";

function UserLogin(this: any, username: string | undefined, password: string | undefined, message: Ref<string>): void {
    request.post('/usersignin/login', {
        "username": username,
        "password": password
    }).then(r => {
        console.log(r.data.msg);
        //this.$cookies.set('token',r.data.token);
        message.value = r.data.msg;
        if (r.data.code === 200) {
            ElNotification({
                title: "成功",
                message: message.value,
                type: "success",
                customClass: "LoginSuccess",
                duration: 800
            });
            setCookie('userName', username);
            setCookie('passwd', password);
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
    });
}

export default UserLogin;
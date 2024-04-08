import {request} from '@/main'
import {Ref} from "vue/dist/vue";
import {ElNotification} from "element-plus";

function UserRegister(username: string, password: string, message: Ref<string>): void {
    request.post('/usersignin/registered', {
        "username": username,
        "password": password
    }).then(r => {
        console.log(r.data.msg);
        message.value = r.data.msg;
        if (r.data.code === 200) {
            ElNotification({
                title: "成功",
                message: message.value,
                type: "success",
                customClass: "RegisterSuccess",
                duration: 800
            });
        }
    });
}

export default UserRegister;
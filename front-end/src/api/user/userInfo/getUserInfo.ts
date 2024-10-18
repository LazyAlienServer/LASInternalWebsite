import { request } from "../../../main";
import { Reactive } from "vue";
import { ElNotification } from "element-plus";
import { getCookie } from "typescript-cookie";
import { UserInfo } from "@/utils/model/UserInfo.ts";

function getUserInfo(
  this: any,
  userInfo: Reactive<UserInfo>,
  fetchUserInfo: Function,
): void {
  request
    .post("usersignin/crud/getUserByToken?token=" + getCookie("token"), {})
    .then((r) => {
      Object.getOwnPropertyNames(userInfo).forEach((key) => {
        userInfo[key] = r.data.data[key];
        console.log(key + " : " + r.data.data[key]);
      });
      fetchUserInfo();
      //sessionStorage.setItem("avatar", String(userInfo.value))
    })
    .catch((e) => {
      if (e.data && "status" in e.data) {
        ElNotification({
          title: "提示",
          message:
            "LASLog : 获取用户信息失败-" + e.data.status != null
              ? e.data.status
              : "unknown error",
          type: "error",
          customClass: "UserInfoError",
          duration: 800,
        });
      } else {
        ElNotification({
          title: "提示",
          message: "LASLog : unknown error",
          type: "error",
          customClass: "UserInfoError",
          duration: 800,
        });
      }
    });
}

export default getUserInfo;
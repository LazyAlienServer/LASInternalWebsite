<template>
  <div class="login">
    <!--    原神？启动！-->
    <div class="el-container ">
      <div class="el-aside">
        <el-image :fit="'cover'" src="login/floatingcity.jpg" class="el-image" draggable="false"/>
      </div>
      <div id="form">
        <div id="form-container">
          <el-col>
            <el-row align="top" justify="center">
              <el-text class="text">账户验证</el-text>
            </el-row>
            <el-row id="user" justify="center">
              <el-input v-model="username" class="in" clearable maxlength=16 minlength=3 placeholder="用户名"
                        @keyup.enter="Login()">
                <template #prefix>
                  <el-icon class="el-input__icon">
                    <svg fill="none" height="31" viewBox="0 0 32.1923828125 31"
                         width="32.1923828125" xmlns="http://www.w3.org/2000/svg">
                      <g opacity="1" transform="translate(0 0)">
                        <path id="Combined Shape"
                              d="M22.8052 11.6267C22.8052 13.6867 21.7952 15.5367 20.2252 16.7167L24.8652 18.7467C26.0552 19.2667 26.8252 20.4167 26.8252 21.6867L26.8252 22.6067C26.8252 24.3867 25.3252 25.8367 23.4752 25.8367L8.71523 25.8367C6.86523 25.8367 5.36523 24.3867 5.36523 22.6067L5.36523 21.6867C5.36523 20.4167 6.13523 19.2667 7.33523 18.7467L11.9652 16.7167C10.3952 15.5367 9.38523 13.6867 9.38523 11.6267C9.38523 8.05669 12.3952 5.16669 16.0952 5.16669C19.8052 5.16669 22.8052 8.05669 22.8052 11.6267ZM16.1009 6.45829C19.0609 6.45829 21.4609 8.76829 21.4609 11.6283C21.4609 14.4783 19.0609 16.7883 16.1009 16.7883C13.1309 16.7883 10.7309 14.4783 10.7309 11.6283C10.7309 8.76829 13.1309 6.45829 16.1009 6.45829ZM16.0965 18.088C17.0665 18.088 17.9965 17.888 18.8265 17.528L24.3065 19.928C25.0265 20.238 25.4865 20.928 25.4865 21.688L25.4865 22.608C25.4865 23.678 24.5865 24.538 23.4765 24.538L8.71653 24.538C7.60653 24.538 6.70653 23.678 6.70653 22.608L6.70653 21.688C6.70653 20.928 7.16653 20.238 7.88653 19.928L13.3665 17.528C14.1965 17.888 15.1165 18.088 16.0965 18.088Z"
                              fill-rule="evenodd" opacity="1"
                              style="fill:#808080"></path>
                      </g>
                    </svg>
                  </el-icon>
                </template>
              </el-input>
              <div class="line"></div>
            </el-row>
            <el-row id="user" justify="center" style="margin-top: 38px">
              <el-input v-model="password" class="in" clearable maxlength=18 minlength=6 placeholder="密码"
                        @keyup.enter="Login()"
                        show-password type="password">
                <template #prefix>
                  <el-icon class="el-input__icon">
                    <svg fill="none" height="31" viewBox="0 0 31 31" width="31" xmlns="http://www.w3.org/2000/svg">
                      <g opacity="1" transform="translate(0 0)">
                        <path id="Shape"
                              d="M21.3085 27.125C23.0985 27.125 24.5385 25.675 24.5385 23.895L24.5385 14.855C24.5385 13.295 23.4285 11.985 21.9585 11.685L21.9585 10.335C21.9585 6.765 19.0685 3.875 15.4985 3.875C11.9385 3.875 9.0385 6.765 9.0385 10.335L9.0385 11.685C7.5685 11.985 6.4585 13.295 6.4585 14.855L6.4585 23.895C6.4585 25.675 7.8985 27.125 9.6885 27.125L21.3085 27.125ZM10.3335 11.6266L10.3335 10.3366C10.3335 7.4766 12.6435 5.1666 15.5035 5.1666C18.3535 5.1666 20.6635 7.4766 20.6635 10.3366L20.6635 11.6266L10.3335 11.6266ZM23.25 14.8566C23.25 13.8366 22.46 12.9966 21.46 12.9266C21.25 12.9366 21.2 12.9366 21.14 12.9166L9.69 12.9166C8.62 12.9166 7.75 13.7866 7.75 14.8566L7.75 23.8966C7.75 24.9666 8.62 25.8366 9.69 25.8366L21.31 25.8366C22.38 25.8366 23.25 24.9666 23.25 23.8966L23.25 14.8566ZM12.9165 18.58C12.9165 19.63 13.1565 20.66 13.6265 21.6L13.9865 22.32C14.2765 22.89 14.8565 23.25 15.4965 23.25C16.1365 23.25 16.7265 22.89 17.0165 22.32L17.3665 21.6C17.8365 20.66 18.0865 19.63 18.0865 18.58L18.0865 18.08C18.0865 16.66 16.9265 15.5 15.4965 15.5C14.0765 15.5 12.9165 16.66 12.9165 18.08L12.9165 18.58ZM16.2185 21.0216C16.5985 20.2616 16.7885 19.4216 16.7885 18.5816L16.7885 18.0816C16.7885 17.3716 16.2085 16.7916 15.4985 16.7916C14.7885 16.7916 14.2085 17.3716 14.2085 18.0816L14.2085 18.5816C14.2085 19.4216 14.4085 20.2616 14.7885 21.0216L15.1385 21.7416C15.2085 21.8716 15.3485 21.9616 15.4985 21.9616C15.6485 21.9616 15.7885 21.8716 15.8585 21.7416L16.2185 21.0216Z"
                              fill-rule="evenodd" opacity="1"
                              style="fill:#808080"></path>
                      </g>
                    </svg>
                  </el-icon>
                </template>
              </el-input>
              <div class="line"></div>
            </el-row>
            <el-row id="login" justify="center">
              <div class="ButtonBg">
                <el-button class="loginButton" type="primary" @click="Login">
                  <p>登录</p>
                </el-button>
              </div>
            </el-row>
            <el-row id="activate" justify="end">
              <div class="ButtonBg">
                <el-button class="activateButton" type="text">
                  <p>激活账号</p>
                </el-button>
                <svg style="width: 3px; height: 12px; margin-top: 2px" xmlns="http://www.w3.org/2000/svg">
                  <rect height="12" style="fill:rgb(204,136,252)"
                        width="3"/>
                </svg>
              </div>
            </el-row>
          </el-col>
        </div>
      </div>
    </div>
  </div>
</template>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.login {
  display: flex;
  position: absolute;
  justify-content: center;
  align-items: center;
  height: 100%;
  width: 100%;
  background-color: rgb(231, 228, 241);
  padding: 0;
  flex-shrink: 0;
}

.el-container {
  height: 443px;
  width: 833px;
  flex-grow: 0;
  background-color: transparent;
  overflow: hidden;
  box-shadow: 0 0 35px rgb(128, 128, 128);
}

.el-aside {
  width: 291px;
  background-color: transparent;
  overflow: hidden;
}

.el-aside::-webkit-scrollbar {
  display: none;
}

.el-image {
  object-fit: fill;
  height: 100%;
  width: 100%;
  z-index: 0;
  user-select: none;
}

#form {
  background-color: white;
  z-index: 1;
  width: 542px;
  height: 100%;
}

@media (max-width: 1100px) {
  .el-aside {
    display: none;
  }

  .el-container {
    width: 665px;
  }
}

.text {
  letter-spacing: 10px;
  font-size: 30px;
  margin-top: 43px;
  color: transparent;
  background-clip: text;
  -webkit-background-clip: text;
  background-image: linear-gradient(103.58deg, rgba(130, 149, 255, 1) 0%, rgba(201, 124, 252, 1) 25%, rgba(130, 149, 255, 1) 50%, rgba(201, 124, 252, 1) 75%);
  background-size: 200% auto;
  animation: gradientAnimate 1.5s linear infinite;
  user-select: none;
}

@keyframes gradientAnimate {
  0% {
    background-position: 0 0;
  }
  100% {
    background-position: 100% 0;
  }
}

#form-container {
  position: relative;
}

#user {
  margin-top: 43px;
  width: 372px;
  margin-left: auto;
  margin-right: auto;
}

#form >>> #user .el-input {
  border: none;
  margin: 0;
  --el-input-border: 0;
  --el-input-hover-border: 0;
  --el-input-focus-border: 0;
  --el-input-border-color: 0;
  --el-input-hover-border-color: 0;
  --el-input-focus-border-color: 0;
  --el-input-text-color: rgba(128, 128, 128, 1);
}

.in {
  font-weight: 100;
  color: rgba(0, 0, 0, 1);
}

.el-input >>> .el-input__inner {
  margin-left: 10px;
  font-size: 18px;
  font-weight: lighter;
}

.el-input:focus >>> .el-input__inner {
  margin-top: -20px;
}

.el-input >>> .el-input__icon {
  width: 31px;
  height: 31px;
}

.el-input svg {
  width: 31px;
  height: 31px;
  color: #808080;
}

#login {
  margin-top: 54px;
  margin-left: auto;
  margin-right: auto;
  height: 46px;
  width: 329px;
}

.loginButton {
  z-index: 2;
  height: 100%;
  width: 100%;
  text-align: center;
  font-size: 24px;
  letter-spacing: 20px;
  --el-button-bg-color: rgba(0, 0, 0, 0);
  --el-button-border-color: rgba(0, 0, 0, 0);
  --el-button-outline-color: rgba(0, 0, 0, 0);
  --el-button-active-color: rgba(0, 0, 0, 0);
  --el-button-hover-link-text-color: rgba(0, 0, 0, 0);
  --el-button-hover-bg-color: rgba(0, 0, 0, 0);
  --el-button-hover-border-color: rgba(0, 0, 0, 0);
  --el-button-active-bg-color: rgba(0, 0, 0, 0);
  --el-button-active-border-color: rgba(0, 0, 0, 0);
}

.loginButton p {
  margin-right: -20px;
}

#login .ButtonBg {
  z-index: 1;
  height: 100%;
  width: 100%;
  border-radius: 10px;
  background-image: repeating-linear-gradient(330deg, rgba(207, 214, 255, 1) 0%, rgba(226, 184, 255, 1) 50%, rgba(207, 214, 255, 1) 100%);
  background-size: 200% auto;
  animation: loginButtonHover 2s linear infinite;
  animation-play-state: paused;
}

#login .ButtonBg:hover {
  animation-play-state: running;
}

@keyframes loginButtonHover {
  from {
    background-position: 0 0
  }
  to {
    background-position: 200% 0
  }
}

.line {
  margin-top: 8px;
  height: 1.5px;
  width: 100%;
  background-color: black;
  text-align: center;
  background-image: linear-gradient(45deg, rgba(130, 149, 255, 1) 0%, rgba(201, 124, 252, 1) 100%);
  background-size: 200% auto;
  animation: an 2s linear infinite;
}

#activate {
  margin-top: 30px;
  margin-right: 73px;
}

.activateButton p {
  font-size: 12px;
  color: rgb(178, 82, 247);
  padding-right: 7px;
  margin-top: -3px;
  font-weight: lighter;
  transition: color 300ms;
}

.activateButton:hover p {
  color: #ce8cff;
}

@media (max-width: 690px) {
  .el-container {
    box-shadow: none;
    width: 100%;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
  }

  .login {
    background-image: none;
    background-color: white;
  }

  #form {
    width: 100%;
  }

  #user {
    width: 70%;
    left: 15%;
  }

  #login {
    width: 64%;
  }

  #activate {
    margin-right: 15%;
  }
}
</style>

<script lang="ts" setup>
import {onMounted, ref} from "vue";
import UserLogin from "@/api/user/UserLogin";
import {ElNotification} from "element-plus";
import getLoginState from "@/api/user/getLoginState";

let username = ref("");
let password = ref("");

let message = ref("");

function Login() {
  if (username.value.length > 0 && password.value.length > 0) {
    if (username.value.length >= 3 && username.value.length <= 16) {
      if (password.value.length >= 6 && password.value.length <= 18) {
        UserLogin(username.value, password.value, message);
      } else ElNotification({
        title: "提示",
        message: "密码必须在6到18位之间",
        type: "warning",
        customClass: "RegisterWarn",
        duration: 800
      });
    } else ElNotification({
      title: "提示",
      message: "用户名必须在3到16位之间",
      type: "warning",
      customClass: "RegisterWarn",
      duration: 800
    });
  } else ElNotification({
    title: "提示",
    message: "用户名和密码不能为空",
    type: "warning",
    customClass: "LoginWarn",
    duration: 800
  });
}

function init() {
  getLoginState()
  // request.post('usersignin/crud/getLoginState/?token='+getCookie('token'), {
  // }).then(r => {
  //   if (getCookie('token') != undefined) {
  //     if(Boolean(r.data)){
  //       router.replace("/user");
  //     }
  //   }
  // });
}

onMounted(() => {
  init();
});
</script>

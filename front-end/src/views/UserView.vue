<template>
  <div class="user">
    <div class="left_menu_bg"></div>
    <!--    <div :class={overlay:isMenuLayout.isMenuLayout.value}></div>-->
    <div id="left_menu" class="left_menu">
      <div class="player_Avatar">
        <div class="player_Avatar_circle">
          <div class="player_Avatar_img">
            <el-avatar :size="50"  :src="avatar" />
          </div>

          <div class="player_info">
            <p id="userName" style="font-size: 20px; margin-bottom: 8px">{{ UserName }}</p>
            <p id="uid" style="font-size: 18px">uid:{{UserId}}</p>
          </div>
        </div>
      </div>
      <div class="bar"></div>
      <div class="menu_container">
        <el-scrollbar class="menu_list">
          <div v-for="menu in UserViewMenu" class="menu">
            <el-button :id="menu.id" class="menu_button" @click="setPage(menu.id)">
              <svg :height="menu.icon.height" :viewBox="menu.icon.viewBox" :width="menu.icon.width"
                   fill="none" xmlns="http://www.w3.org/2000/svg">
                <path :d="menu.icon.d" :opacity="menu.icon.opacity" fill-rule="evenodd" style="fill:#646438"></path>
              </svg>
              <div class="title-div">{{ menu.title }}</div>
            </el-button>
          </div>
        </el-scrollbar>
      </div>
    </div>
    <div class="main">
      <router-view></router-view>
    </div>
  </div>
</template>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.user {
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


.left_menu_bg {
  position: relative;
  top: 0;
  bottom: 0;
  width: 54px;
  height: auto;
  z-index: 1;
}

.menu_container {
  position: absolute;
  z-index: 0;
  left: 54px;
  top: 117px;
  bottom: 0;
  text-align: left;
  transition: width 0.3s ease-in-out;
}

.menu_container:hover {
  width: 215px;
}

.left_menu {
  border-radius: 30px;
  box-shadow: 5px 0 10px rgba(0, 0, 0, 0.25);
  background-color: rgb(250, 250, 252);
  position: absolute;
  top: 0;
  bottom: 0;
  left: -40px;
  width: 130px;
  height: auto;
  z-index: 1;
  transition: width 0.3s ease-in-out;
  overflow: hidden;
}


.left_menu:hover {
  width: 268px;
}

.menu_list {
  height: 100%;
}

.player_Avatar {
  position: absolute;
  z-index: 7;
  left: 47.2px;
  top: 26.4px;
  width: 44.8px;
  height: 42.4px;
}

.player_Avatar_circle {
  position: absolute;
  z-index: 0;
  left: 10px;
  top: 0;
  width: 51px;
  height: 49px;
  border-radius: 48px;
  background: rgba(235, 235, 240, 1);
}

.player_Avatar_img {
  position: absolute;
  z-index: 1;
  border: unset !important;
  box-shadow: unset;
  width: 20px;
  height: 20px;
  background-size: cover;
}

.left_menu:hover .player_info {
  color: #646464;
  transition: color 0.3s ease-in-out, background-color 0.3s ease-in-out;
}

.player_info {
  user-select: none;
  width: 120px;
  margin-left: 62px;
  color: transparent;
  transition: color 0.3s ease-in-out, background-color 0.3s ease-in-out;
}

.player_info #userName {
  overflow: hidden;
  text-overflow: ellipsis;
  text-align: left;
}

.player_info #uid {
  text-align: left;
}

.menu {
  position: relative;
  z-index: 2;
  margin-top: 2px;
  margin-bottom: 8px;
}

.title-div {
  margin-left: 32px;
  font-weight: lighter;
  text-align: center;
  white-space: nowrap;
  user-select: none;
  position: relative;
  z-index: 4;
  width: 100px;
  font-size: 24px;
  color: #646464;
}

.menu_list >>> button {
  overflow: hidden;
  left: 0;
  background-color: rgba(0, 0, 0, 0);
  width: 60px;
  padding: 0 15px;
  height: 51px;
  border: none;
  margin-bottom: 5px;
  border-radius: 10px;
  color: #646464;
  transition: width 0.3s ease-in-out, background-color 0.3s ease-in-out;
  justify-content: left;
}

.left_menu:hover .menu_list >>> button {
  width: 200px;
}

.menu_list >>> button:hover {
  background-color: rgba(230, 235, 255, 70%) !important;
}

.bar {
  position: absolute;
  z-index: 3;
  border: unset !important;
  box-shadow: unset;
  left: 55px;
  top: 100px;
  right: 14px;
  height: 1px;
  background-color: rgb(166, 166, 166);
  background-size: cover;
}

.main {
  position: relative;
  height: 100%;
  min-height: 100%;
  width: 100%;
  z-index: 0;
  overflow-y: scroll;
}

.main::-webkit-scrollbar {
  width: 8px;
  background-color: transparent;
}

.main::-webkit-scrollbar-track {
  border-radius: 10px;
  background-color: transparent;
}

.main::-webkit-scrollbar-thumb {
  border-radius: 10px;
  -webkit-box-shadow: inset 0 0 8px rgba(0, 0, 0, .5);
  background-color: transparent;
}

</style>

<script lang="ts" setup>
import {onMounted, Ref, ref} from "vue";
import {getCookie} from "typescript-cookie";
import UserViewMenu from "@/utils/userViewMenu";
import {useRouter} from "vue-router";
import router from "@/router";
import {request} from "@/main";

let Page = 1;
let UserName: Ref<string | undefined> = ref("");
let UserId :Ref<number | undefined> = ref(0)
let route = useRouter();
let transitionName = ref("view");
let avatar = ref("https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png")

function init() {
  if (getCookie('token') != undefined) {
    request.post('usersignin/crud/getUserNameByToken/?token='+getCookie('token'), {
    }).then(r => {
      UserName.value = String(r.data)
    });
    request.post('usersignin/crud/getUserIdByToken/?token='+getCookie('token'), {
    }).then(r => {
      UserId.value = Number(r.data)
    });
  } else {
    router.replace("/");
  }
}

function setPage(page: number) {
  for (const item of UserViewMenu.value) {
    if (item.id == page) {
      router.replace(item.route)
      setElementBgColor(item.id.toString(), "#E6EBFF");
    } else {
      setElementBgColor(item.id.toString(), "#FAFAFC")
    }
  }
}

function getAvatar(){
  request.post('usersignin/crud/getAvatarByToken?token='+getCookie('token'), {
  }).then(r => {
    avatar.value = String(r.data)
  });
}

function setElementBgColor(id: string, color: string) {
  let element = document.getElementById(id);
  if (element != null) {
    element.style.backgroundColor = color;
  }
}

onMounted(() => {
  setPage(1);
  init();
  getAvatar();
});
</script>
<template>
  <div class="user">
    <div class="left_menu_bg"></div>
    <!--    <div :class={overlay:isMenuLayout.isMenuLayout.value}></div>-->
    <div id="left_menu" class="left_menu">
      <div class="player_Avatar">
        <div class="player_Avatar_circle">
          <div class="player_Avatar_img">
            <svg fill="none" height="36.000091552734375" viewBox="0 0 35.93017578125 36.000091552734375"
                 width="35.93017578125" xmlns="http://www.w3.org/2000/svg">
              <path
                  d="M6.11 11.2201C6.11 14.9601 8.05 18.2701 11.03 20.3101C8.83 21.1901 6.91 22.4101 5.26 23.9801C3.61 25.5401 2.32 27.3601 1.41 29.3801C0.52 31.3601 0.05 33.4701 0 35.6301C0 35.8301 0.17 36.0001 0.38 36.0001L3.25 36.0001C3.46 36.0001 3.63 35.8401 3.63 35.6501C3.73 32.1501 5.21 28.8901 7.83 26.4101C10.53 23.8401 14.13 22.4301 17.96 22.4301C21.79 22.4301 25.39 23.8401 28.1 26.4101C30.72 28.8901 32.2 32.1501 32.3 35.6501C32.3 35.8401 32.47 36.0001 32.68 36.0001L35.55 36.0001C35.76 36.0001 35.93 35.8301 35.93 35.6301C35.88 33.4701 35.41 31.3601 34.52 29.3801C33.61 27.3501 32.32 25.5301 30.67 23.9801C29.02 22.4101 27.1 21.1901 24.96 20.3301C27.88 18.2701 29.81 14.9601 29.81 11.2201C29.81 5.0201 24.51 0.0001 17.96 0.0001C11.42 0.0001 6.11 5.0201 6.11 11.2201ZM23.7763 16.7172C25.3263 15.2472 26.1863 13.2872 26.1863 11.2172C26.1863 9.1372 25.3263 7.1872 23.7763 5.7172C22.2163 4.2472 20.1563 3.4372 17.9663 3.4372C15.7763 3.4372 13.7063 4.2472 12.1563 5.7172C10.5963 7.1872 9.7463 9.1372 9.7463 11.2172C9.7463 13.2872 10.5963 15.2472 12.1563 16.7172C13.7063 18.1872 15.7763 18.9972 17.9663 18.9972C20.1563 18.9972 22.2163 18.1872 23.7763 16.7172Z"
                  fill-rule="evenodd" opacity="0.65"
                  style="fill:#383838"></path>
            </svg>
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
  left: 8px;
  top: 5px;
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

function setElementBgColor(id: string, color: string) {
  let element = document.getElementById(id);
  if (element != null) {
    element.style.backgroundColor = color;
  }
}

onMounted(() => {
  setPage(1);
  init();
});
</script>
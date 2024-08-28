<template>
  <div>
    <Background></Background>
  <div class="user">
    <el-button :style="shouldShowSideBar ? {
      width: '235px', height: '100%', top: 0
    } : {}" class="sidebarButton" @click="shouldShowSideBar = true">
      <img :style="shouldShowSideBar ? {
        opacity: 0
      } : {
        transition: `opacity 0.15s ${ getAnimateDelay(0.35, 0.15) }s ease-out`
      }" alt="show-menu" src="../resource/hamburger-menu.svg">
    </el-button>
    <div id="sidebar" ref="sidebar" :style="isMobileSideBar ? shouldShowSideBar ? {
      width: '235px', height: '100%', left: '-40px'
    } : {
      width: '90px',
      transition: 'width 0.5s ease-out, height 0.5s ease-out, top 0.3s 0.1s ease-out, left 0s 0.5s'
    } : {}" class="sidebar">
      <div class="player_Avatar">
        <div :style="isMobileSideBar ? shouldShowSideBar ? {
          opacity: 1
        } : {
          transition: `opacity 0.2s ${ getAnimateDelay(0.25, 0.25) }s ease-in-out`
        } : {}" class="player_Avatar_img">
          <el-avatar :size="45" :src="avatar" @error="() => true">
            <img
                src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
                alt="no-avatar"/>
          </el-avatar>
        </div>

        <div class="player_info">
          <p id="userName" style="font-size: 20px; margin-bottom: 8px">{{ UserName }}</p>
          <p id="uid" style="font-size: 18px">uid: {{ UserId }}</p>
        </div>
      </div>
      <div class="bar"></div>
      <div class="menu_container">
        <el-scrollbar class="menu_list">
          <div v-for="menu in UserViewMenu" class="menu" :key="menu.id">
            <el-button :id="menu.id" :style="(shouldShowSideBar && isMobileSideBar) ? {width: '168px'} : {}"
                       class="menu_button"
                       @click="setPage(menu.id)">
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
    <div :style="shouldShowSideBar ? {width: '100vw'} : {}" class="left-menu-extending-bg"
         @click="shouldShowSideBar = false"></div>
    <div class="main">
      <router-view></router-view>
    </div>
  </div>
  </div>
</template>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

.sidebarButton {
  display: none;
  position: fixed;
  left: -40px;
  top: -40px;
  width: 90px;
  height: 90px;
  border-radius: 30px;
  box-shadow: 5px 0 10px rgba(0, 0, 0, 0.25);
  background-color: rgb(250, 250, 252);
  padding: 0;
  transition: width 0.5s ease-out, height 0.5s ease-out, top 0.3s 0.1s ease-out;
  z-index: 1;
}

.sidebarButton :deep(span){
  position: fixed;
  left: 5px;
  top: 5px;
}

.sidebarButton :deep(img) {
  opacity: 1;
  transition: opacity 0.1s ease-out;
}

.user {
  display: flex;
  position: absolute;
  justify-content: center;
  align-items: center;
  height: 100%;
  width: 100%;
  background-color: transparent;
/*background-color: rgb(231, 228, 241);*/
  padding: 0;
  flex-shrink: 0;
}

.menu_container {
  position: absolute;
  z-index: 0;
  left: 54px;
  top: 107px;
  bottom: 0;
  text-align: left;
  transition: width 0.4s ease-out;
}

.menu_container:hover {
  width: 215px;
}

.sidebar {
  border-radius: 30px;
  box-shadow: 5px 0 10px rgba(0, 0, 0, 0.25);
  background-color: rgb(250, 250, 252);
  position: absolute;
  top: 0;
  bottom: 0;
  left: -40px;
  width: 116px;
  height: 100%;
  z-index: 2;
  transition: width 0.4s ease-out;
  overflow: hidden;
}


.sidebar:hover {
  width: 235px;
}

.menu_list {
  height: 100%;
}

.player_Avatar {
  position: absolute;
  z-index: 7;
  left: 54px;
  top: 26px;
  width: 45px;
  height: 42px;
}

.player_Avatar_img {
  position: absolute;
  z-index: 1;
  border: unset !important;
  box-shadow: unset;
  width: 16px;
  height: 16px;
  background-size: cover;
}

.sidebar:hover .player_info {
  color: #646464;
  transition: color 0.4s ease-in-out, background-color 0.4s ease-in-out;
}

.player_info {
  user-select: none;
  width: 120px;
  margin-left: 62px;
  margin-top: 2px;
  color: transparent;
  transition: color 0.4s ease-in-out, background-color 0.4s ease-in-out;
}

.player_info #userName {
  overflow: hidden;
  text-overflow: ellipsis;
  text-align: left;
  font-size: 18px !important;
  margin-bottom: 5px !important;
}

.player_info #uid {
  text-align: left;
  font-size: 14px !important;
}

.menu {
  position: relative;
  z-index: 2;
  margin-top: 2px;
  margin-bottom: 8px;
}

.title-div {
  margin-left: 22px;
  font-weight: lighter;
  text-align: center;
  white-space: nowrap;
  user-select: none;
  position: relative;
  z-index: 4;
  width: 100px;
  font-size: 20px;
  color: #646464;
}

.menu_list :deep(button) {
  overflow: hidden;
  left: 0;
  background-color: rgba(0, 0, 0, 0);
  width: 46px;
  padding: 0 10px;
  height: 41px;
  border: none;
  margin-bottom: 5px;
  border-radius: 10px;
  color: #646464;
  transition: width 0.4s ease-out, background-color 0.2s ease-in-out;
  justify-content: left;
}

.sidebar:hover .menu_list :deep(button) {
  width: 168px;
}

.menu_list :deep(button:hover) {
  background-color: rgba(230, 235, 255, 70%) !important;
}

.bar {
  position: absolute;
  z-index: 3;
  border: unset !important;
  box-shadow: unset;
  left: 55px;
  top: 90px;
  right: 16px;
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

.left-menu-extending-bg {
  position: fixed;
  height: 100%;
  width: 0;
  left: 0;
  z-index: 1;
  background-color: transparent;
}

@media (max-width: 768px) {
  .sidebar {
    left: -200px;
    width: 116px;
    height: 50px;
    border-radius: 30px;
    box-shadow: none;
    background-color: transparent;
    transition: width 0.5s ease-out, height 0.5s ease-out, top 0.3s 0.1s ease-out;
  }

  .sidebarButton {
    display: block;
  }

  .menu_container {
    width: 215px;
  }

  .sidebar .menu_list :deep(button) {
    width: 21px;
    transition: width 0.5s ease-out, background-color 0.2s ease-in-out;
  }

  .sidebar .player_info {
    color: #646464;
  }

  .player_Avatar_img {
    opacity: 0;
    transition: opacity 0.2s ease-out;
  }
}

</style>

<script lang="ts" setup>
import {onMounted, Ref, ref} from "vue";
import {getCookie} from "typescript-cookie";
import UserViewMenu from "@/utils/userViewMenu";
import router from "@/router";
import getUserAvatar from "@/api/user/userInfo/getUserAvatar";
import getUserNameByToken from "@/api/user/userInfo/getUserNameByToken";
import getUserIdByToken from "@/api/user/userInfo/getUserIdByToken";
import {SessionStorageKeys, setSessionStorageItem} from "@/store/sessionStorageManager";
import Background from "@/components/Background.vue";
import {throttle} from "@/utils/optimizeUtils.ts";

let isMobileSideBar = ref(document.body.clientWidth < 768)
let shouldShowSideBar = ref(false)
// let Page = 1;
let UserName: Ref<string | undefined> = ref("");
let UserId :Ref<number | undefined> = ref(0)
// let route = useRouter();
// let transitionName = ref("view");
let avatar = ref("https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png")
let sidebar = ref()
let isMounted = ref(false)

function getAnimateDelay(originalDelay: number, endDelay: number = 0.15) {
  return isMounted.value ? Math.max(sidebar.value.clientWidth / 235 * (originalDelay + endDelay) - endDelay, 0) : originalDelay;
}



function init() {
  if (getCookie('token') != undefined) {
    getUserNameByToken(UserName);
    getUserIdByToken(UserId);
    // request.post('usersignin/crud/getUserNameByToken/?token='+getCookie('token'), {
    // }).then(r => {
    //   UserName.value = String(r.data)
    // });
    // request.post('usersignin/crud/getUserIdByToken/?token='+getCookie('token'), {
    // }).then(r => {
    //   UserId.value = Number(r.data)
    // });
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
  getUserAvatar(avatar)
  setSessionStorageItem(SessionStorageKeys.avatar, avatar.value)
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
  isMounted.value = true
  window.onresize = throttle(function (){
    isMobileSideBar.value = document.body.clientWidth < 768
    shouldShowSideBar.value = shouldShowSideBar.value && isMobileSideBar.value
  })
});

</script>
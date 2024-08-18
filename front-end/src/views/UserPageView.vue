<template>
  <div class="Main">
    <div class="userInfo">
      <div class="userAvatarFill"></div>
      <div class="userAvatarBorder">
        <div class="userAvatar">
          <svg fill="none" height="123.29998779296875" viewBox="0 0 122.953369140625 123.29998779296875"
               width="122.953369140625" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink">
            <path id="形状"
                  d="M20.9269 38.4192C20.9269 51.2298 27.554 62.5731 37.7345 69.564C30.2098 72.5724 23.6441 76.7645 18.0006 82.1279C12.3571 87.4667 7.93076 93.6931 4.82005 100.635C1.78312 107.416 0.160139 114.617 0.0003 122.027C-0.0119953 122.73 0.578179 123.297 1.3036 123.297L11.1152 123.297C11.8407 123.297 12.4062 122.754 12.4308 122.088C12.7505 110.129 17.8162 98.9333 26.7794 90.4381C36.0501 81.6594 48.37 76.8262 61.4767 76.8262C74.5835 76.8262 86.9034 81.6594 96.174 90.4381C105.137 98.9333 110.203 110.129 110.523 122.088C110.547 122.766 111.113 123.297 111.838 123.297L121.65 123.297C122.375 123.297 122.965 122.73 122.953 122.027C122.793 114.617 121.17 107.416 118.133 100.622C115.035 93.6808 110.596 87.4543 104.953 82.1156C99.3093 76.7645 92.7437 72.5724 85.4157 69.6256C95.3994 62.5731 102.027 51.2298 102.027 38.4192C102.027 17.1876 83.8788 0 61.4767 0C39.0747 0 20.9269 17.1876 20.9269 38.4192ZM81.3593 57.25C86.6743 52.2146 89.599 45.5222 89.599 38.4141C89.599 31.306 86.6743 24.6135 81.3593 19.5782C76.0442 14.5429 68.9799 11.7721 61.4769 11.7721C53.9739 11.7721 46.9097 14.5429 41.5946 19.5782C36.2795 24.6135 33.3548 31.306 33.3548 38.4141C33.3548 45.5222 36.2795 52.2146 41.5946 57.25C46.9097 62.2853 53.9739 65.056 61.4769 65.056C68.9799 65.056 76.0442 62.2853 81.3593 57.25Z"
                  fill-rule="evenodd" opacity="0.65"
                  style="fill:#383838"></path>
          </svg>
        </div>
      </div>
      <div class="info">
        <div class="userName">{{ getSessionStorageItem(SessionStorageKeys.userName) }}</div>
        <div class="uid">uid:{{ getSessionStorageItem(SessionStorageKeys.userId) }}</div>
        <!--        <input v-model="introduction" id="introduction-input" placeholder="简介"></input>-->
        <div id="user_Introduction">
          <p id="introduction_text">{{ introduction }}</p>
          <el-icon id="introductionEditIcon" @click="setIntroduction()">
            <EditPen/>
          </el-icon>
          <el-dialog v-model="setIntroductionView" append-to-body
                     style="border-radius: 10px;background-color: #fae7e7CC"
                     top="40vh" width="40vw">
            <template #header>
              <div id="introductionEditView-header">修改简介</div>
            </template>
            <el-input v-model="setIntroductionTemp" placeholder="简介"
                      @keyup.enter="setIntroductionConfirm()"></el-input>
            <template #footer>
              <el-button id="introductionEditView-cancel" @click="setIntroductionView = false">取消</el-button>
              <el-button id="introductionEditView-confirm" type="primary" @click="setIntroductionConfirm()">
                确认
              </el-button>
            </template>
          </el-dialog>
        </div>
        <Tags id="user-tag" :radius="'50px'" :tag-groups=tagGroup :tags-color=tagsColor :tags-font-size="'15px'"></Tags>
      </div>

    </div>
    <div class="line"></div>
  </div>
</template>

<style scoped>
.Main {
  display: flex;
  flex-direction: column;
  position: absolute;
  height: 100%;
  width: 100%;
  background: transparent;
  padding: 0;
  flex-shrink: 1;
  margin: 0;
  border: none;
  justify-content: flex-start;
  align-items: center;
  flex-wrap: wrap;
  min-height: 100%;
}

.userInfo {
  top: 75px;
  position: relative;
  display: flex;
  text-align: left;
  height: min-content;
  width: 75%;
  background-color: transparent;
  user-select: none;
  flex-wrap: wrap;
}

.info {
  display: flex;
  position: relative;
  flex-direction: column;
  padding-left: 45px;
  font-family: "Noto Sans SC", sans-serif;
  font-weight: normal;
  justify-content: space-around;
  height: auto;
}


.userName {
  font-size: 48px;
  transform: translateX(-3px);
}

.uid {
  margin: 5px 0 5px 0;
  font-size: 1.6ch;
  color: #383838;
}

#user_Introduction {
  display: flex;
  flex-direction: row;
}

#introduction_text {
  overflow-x: revert;
  min-width: max-content;
  padding-bottom: 4px;

  font-size: 18px;
  color: #383838;
  display: flex;
  flex-wrap: wrap;
  padding-top: 5px;
  align-items: flex-end;
}

#introductionEditIcon {
  height: 100%;
  display: flex;
  align-content: flex-end;
  bottom: -2px;
  cursor: pointer;
  color: #383838;
  transition: color 0.3s;
}

#introductionEditIcon:hover {
  color: #b63428;
}

#introductionEditView-header {
  font-size: 1.5vw;
  font-weight: bold;
  @media screen and (max-width: 600px) {
    font-size: 3vw;
  }
}

#introductionEditView-cancel {
  color: #323334;
  background-color: transparent;
  border: #96a2af solid 1px;
  box-shadow: 2px 2px 3px #888888;
  transition: all 0.3s;
}

#introductionEditView-cancel:hover {
  transform: translate3d(1px, 1px, 1px);
}

#introductionEditView-cancel:after:hover {
  height: 100%;
  width: 100%;
  background-color: #46a3e7;
}

#introductionEditView-confirm {
  color: #e7e7ec;
  background-color: #46a3e7;
  border: #96a2af solid 1px;
  box-shadow: 2px 2px 3px #888888;
  transition: all 0.3s;
}

#introductionEditView-confirm:hover {
  transform: translate3d(1px, 1px, 1px);
}


#introduction-input {
  font-size: large;
  width: max-content;
  border: 0;
  outline: 0;
  background: transparent;
  margin: 5px 0 5px 0;
}

.userAvatarBorder {
  display: flex;
  position: absolute;
  z-index: 0;
  left: 20px;
  top: 0;
  width: 190px;
  height: 180px;
  border-radius: 100px;
  background: rgba(235, 235, 240, 1);
  box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.1);
  overflow: hidden;
}

.userAvatar {
  display: flex;
  padding-left: 33px;
  padding-top: 33px;
  border-radius: 50px;
  max-width: 100%;
  max-height: 100%;
}

.userAvatarFill {
  position: relative;
  z-index: 0;
  left: 20px;
  top: 0;
  min-height: 180px;
  width: 190px;
  height: 180px;
  background-color: transparent;
}

#user-tag {
  margin: 10px 0 0 0;
  display: flex;
  position: relative;
}



.line {
  display: flex;
  position: relative;
  background-color: #808080;
  margin-top: 100px;
  height: 2px;
  right: -20px;
  min-width: 75%;
}
</style>

<script lang="ts" setup>

import {onMounted, ref} from "vue";
import Tags from "@/components/Tags.vue";
import {getSessionStorageItem, SessionStorageKeys} from "../store/sessionStorageManager";
import {EditPen} from "@element-plus/icons-vue";
//import isMenuLayout from "@/utils/isMenuLayout";


let tagGroup: string[][] = [["管理员"], ["红石", "储电"], ["红石1", "储电2"]];
let tagsColor: { key: string, color: string }[] =
    [
      {key: "红石", color: "#B02A2A"},
      {key: "储电", color: "#872323"},
      {key: "建筑", color: "#32e82c"},
      {key: "管理员", color: "#d2b72f"}
    ];

let introduction = ref("简介");
let setIntroductionTemp = ref("简介");
let setIntroductionView = ref(false);

function setIntroduction() {
  setIntroductionView.value = true;
  setIntroductionTemp.value = introduction.value;
}

function setIntroductionConfirm() {
  introduction.value = setIntroductionTemp.value;
  setIntroductionView.value = false;
}

onMounted(() => {

});

</script>

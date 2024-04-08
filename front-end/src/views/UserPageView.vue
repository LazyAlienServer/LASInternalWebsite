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
        <div>
          <div class="userName">UserName</div>
          <div class="uid">uid:123456</div>

          <div class="introduction">
            <button class="I_edit" @click="setIntroduction=true">
              <svg fill="none" height="19.54669189453125" viewBox="0 0 17.389892578125 19.54669189453125"
                   width="17.389892578125" xmlns="http://www.w3.org/2000/svg"
                   xmlns:xlink="http://www.w3.org/1999/xlink">
                <path id="edit"
                      d="M17.17 3.45999C17.39 3.66999 17.39 4.00999 17.17 4.21999L4.17 17.22C4.07 17.33 3.93 17.38 3.79 17.38L0.54 17.38C0.24 17.38 0 17.14 0 16.84L0 13.59C0 13.45 0.06 13.31 0.16 13.21L13.16 0.20999C13.37 -9.76771e-06 13.71 -9.76771e-06 13.92 0.20999L17.17 3.45999ZM16.0235 3.84079L13.5435 1.36079L1.0835 13.8208L1.0835 16.3008L3.5635 16.3008L16.0235 3.84079ZM11.93 19.0067C11.93 19.3067 11.69 19.5467 11.39 19.5467L0.54 19.5467C0.24 19.5467 0 19.3067 0 19.0067C0 18.7067 0.24 18.4667 0.54 18.4667L11.39 18.4667C11.69 18.4667 11.93 18.7067 11.93 19.0067Z"
                      fill="#000000"
                      fill-rule="evenodd"></path>
              </svg>
            </button>
            <!--              <el-dialog class="Introduction" v-model="setIntroduction" title="设置简介" align-center>-->
            <!--                    <div class="dialog-footer">-->
            <!--                      <el-button @click="setIntroduction=false">取消</el-button>-->
            <!--                      <el-button type="primary" @click="setIntroduction=false">-->
            <!--                        确认-->
            <!--                      </el-button>-->
            <!--                    </div>-->
            <!--              </el-dialog>-->
          </div>
          <div class="userTag">
            <div v-for="tags in tagGroup" class="tagGroups">
              <div v-for="tag in tags"
                   :style="{backgroundColor: getColor(tag),paddingRight:getTagStyle(tag).paddingRight+'px',left:getTagStyle(tag).left+'px'}"
                   class="tags">
                <span class="tag">{{ tag }}</span>
              </div>
            </div>
          </div>
        </div>
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
  font-family: "Noto Sans SC";
  font-weight: normal;
}


.userName {
  font-size: 48px;
}

.uid {
  font-size: 20px;
  color: #383838;
}

.introduction {
  font-size: 20px;
  color: #383838;
  display: flex;
  flex-direction: row;
  padding-top: 5px;
  align-items: flex-end;
}

.I_edit {
  background-color: transparent;
  border: none;
  margin-top: 10px;
  margin-left: 10px;
  user-select: all;
  cursor: pointer;
}

.I_edit:hover #edit {
  fill: #5b80b2;
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

.Introduction >>> .el-dialog {
  background-color: black;
}

.userTag {
  margin-top: 10px;
  display: flex;
  position: relative;
  flex-direction: row;
}

.tagGroups {
  display: flex;
  position: relative;
  flex-direction: row;
  height: min-content;
  margin-right: 10px;
}

.tags {
  display: flex;
  position: relative;
  flex-direction: row;
  height: min-content;
  border-radius: 21.5px;
  text-align: center;
  min-width: max-content;
  justify-content: center;
}

.tag {
  padding-left: 13px;
  padding-right: 13px;
  height: 27px;
  color: white;
  margin-top: 3px;
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
//import isMenuLayout from "@/utils/isMenuLayout";

const setIntroduction = ref(false);

let tagGroup: string[][] = [["管理员"], ["红石", "储电"]];
let tagsColor: { key: string, color: string }[] = [{key: "红石", color: "#B02A2A"}, {
  key: "储电",
  color: "#872323"
}, {key: "建筑", color: "#32e82c"}, {key: "管理员", color: "#d2b72f"}];
let Introduction = ref("");

function setIntroductionContent(value: boolean) {
  setIntroduction.value = value;

}

function getColor(tag: string) {
  let color = "rgba(86,173,252,1)";
  tagsColor.forEach(element => {
    if (element.key == tag) {
      color = element.color
    }
  });
  return color;
}

function getTagStyle(tag: string) {
  let paddingRight = 0;
  let left = 0;
  tagGroup.forEach(element => {
    if (element.includes(tag)) {
      element.indexOf(tag) == element.length - 1 ? paddingRight = 0 : paddingRight = 25;
      if (element.indexOf(tag) != 0) {
        for (let i = 0; i < element.indexOf(tag); i++) {
          left -= 30;
        }
      } else left = 0;
      console.log(tag + ":" + left);
    }
  })
  return {paddingRight, left};
}


function initTag() {

}

function init() {
  initTag();
}

onMounted(() => {
  init();
});
// isMenuLayout.addCheckIsMenuLayout("UserPageSetIntroduction");
//
// function introduction(value:boolean){
//   setIntroduction.value=value;
//   isMenuLayout.setCheckIsMenuLayout("UserPageSetIntroduction", value);
// }

</script>

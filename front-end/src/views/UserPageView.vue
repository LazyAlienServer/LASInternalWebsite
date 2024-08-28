<template>
  <div class="Main">
    <div class="userInfo">
      <el-skeleton
        :loading="false"
        :throttle="500"
        animated
        style="width: 240px"
      >
        <template #default>
          <el-avatar :size="200" :src="avatar" @error="() => true">
            <img
              src="https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png"
            />
          </el-avatar>
          <div class="info">
            <div class="userName">
              {{ getSessionStorageItem(SessionStorageKeys.userName) }}
            </div>
            <div class="uid">
              uid:{{ getSessionStorageItem(SessionStorageKeys.userId) }}
            </div>
            <el-tooltip effect="light" placement="bottom-start">
              <template #content>{{ introductionTemp }}</template>
              <input
                id="introduction-input"
                v-model="introduction"
                placeholder="简介"
                @blur="introductionOnBlur"
                @focus="introductionOnFocus"
                @keyup.enter="setIntroduction"
              />
            </el-tooltip>
            <Tags
              id="user-tag"
              :radius="'50px'"
              :tag-groups="tagGroup"
              :tags-color="tagsColor"
              :tags-font-size="'20px'"
            ></Tags>
          </div>
        </template>
      </el-skeleton>
    </div>
    <div class="line"></div>
    <div id="userContent">
      <div id="playerInfo" class="UserInfoPart">
        <div class="partHeader">
          <p>Minecraft玩家信息</p>
        </div>
        <div class="partContent">
          <Card>
            <template #header>
              <p>{{ getSessionStorageItem(SessionStorageKeys.userName) }}</p>
            </template>
            <template #default> 我不好 </template>
            <template #footer> </template>
          </Card>
        </div>
      </div>
      <div id="projectInfo">
        <div class="partHeader">
          <p>工程参与</p>
        </div>
        <div class="partContent"></div>
      </div>
      <div id="rewardInfo">
        <div class="partHeader">
          <p>奖惩记录</p>
        </div>
        <div class="partContent"></div>
      </div>
      <div id="researchInfo">
        <div class="partHeader">
          <p>奖惩记录</p>
        </div>
        <div class="partContent"></div>
      </div>
    </div>
  </div>
</template>

<style scoped>
@import "@/styles/light/UserPage/userInfo.css";
@import "@/styles/light/UserPage/userContent/userInfoCommon.css";
@import "@/styles/light/UserPage/userContent/playerInfo.css";
@import "@/styles/light/UserPage/userContent/projectInfo.css";
@import "@/styles/light/UserPage/userContent/rewardInfo.css";
@import "@/styles/light/UserPage/userContent/researchInfo.css";

.Main {
  display: flex;
  flex-direction: column;
  position: absolute;
  height: max-content;
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
</style>

<script lang="ts" setup>
import { onMounted, ref } from "vue";
import Tags from "@/components/Tags.vue";
import {
  getSessionStorageItem,
  SessionStorageKeys,
} from "@/store/sessionStorageManager";
import Card from "@/components/Card.vue";

let avatar = ref(getSessionStorageItem(SessionStorageKeys.avatar));

let tagGroup: string[][] = [["管理员"], ["红石", "储电"], ["红石1", "储电2"]];
let tagsColor: { key: string; color: string }[] = [
  { key: "红石", color: "#B02A2A" },
  { key: "储电", color: "#872323" },
  { key: "建筑", color: "#32e82c" },
  { key: "管理员", color: "#79DBA0" },
];

let introduction = ref("简介");
let introductionTemp = ref("简介");
let introductionIsFocus = false;

//input获取到焦点
function introductionOnFocus() {
  introductionIsFocus = true;
  introduction.value = introductionTemp.value;
}

//取消更改，或移除焦点
function introductionOnBlur() {
  introductionIsFocus = false;
  introduction.value = introductionTemp.value;
  introductionAnimation();
}

//确认修改简介
function setIntroduction() {
  let input = document.getElementById("introduction-input");
  if (input != null) {
    introductionTemp.value = introduction.value;
    input.blur();
  }
}

//限制字数动画 （大于十个字自动回退到10个字，十个字内不动）
function introductionAnimation() {
  //防止输入框获得焦点后，动画继续执行
  if (introductionIsFocus) {
    introduction.value = introductionTemp.value;
    return;
  }
  if (introduction.value.length > 10) {
    //动画延时
    setTimeout(() => {
      //简介长度大于25时，截取前25个字符，防止动画结束时间过长
      if (introduction.value.length > 25)
        introduction.value = introduction.value.substring(0, 25);
      introduction.value = introduction.value.substring(
        0,
        introduction.value.length - 1,
      );
      introductionAnimation();
    }, 90);
  } else if (introduction.value.length == 10) {
    //当简介长度为10时，添加一个点，然后延时100ms一直到三个点做为省略号
    introduction.value = introduction.value + ".";
    setTimeout(() => {
      introduction.value = introduction.value + ".";
      setTimeout(() => {
        //防止输入框获得焦点后，动画继续执行
        if (introductionIsFocus) {
          introduction.value = introductionTemp.value;
          return;
        }
        introduction.value = introduction.value + ".";
      }, 100);
    }, 100);
  }
}

onMounted(() => {});
</script>
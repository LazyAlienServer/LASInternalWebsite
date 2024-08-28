<template>
  <div class="Main">
    <div class="circle2"></div>
    <div class="circle1"></div>
    <div class="main-container">
      <el-aside class="logo-container">
        <img src="../resource/logo-linear-gradient.svg" alt="logo">
      </el-aside>
      <el-aside class="title-container">
        <div class="title"><p style="color: #A263CF">LAS</p><p>&nbsp;</p><p style="color: #8194F0">LOG</p></div>
        <div class="bar"></div>
        <div class="subtitle">Lazy Alien Server</div>
        <div class="prompt">在LAS LOG，探索Minecraft的终极魅力</div>
        <Tags class="tags" :radius="'50px'" :tag-groups=tagsGroup :tags-color=tagsColor
              :tags-font-size="'20px'" :group-gap="'2px 24px 2px 0px'" :item-gap="'0px 5px 0px 8px'" :letter-spacing="'3px'"></Tags>
      </el-aside>
    </div>
  </div>
</template>

<style scoped>

.title{
  -webkit-text-stroke: 1px #ffffff;
  width: max-content;
  text-shadow: -4px 4px 4px  rgba(0, 0, 0, 0.25);
  font-size: 160px;
  font-weight: 1000;
  display: flex;
}

.main-container{
  position: fixed;
  left: 10.89%;
  top: 11.85%;
  width: 80.67%;
  height: 782px;
  opacity: 0.9;
  border-radius: 60px;
  background: rgba(255, 255, 255, 0.3);
  border: 3px solid rgba(88, 125, 255, 0.35);
  backdrop-filter: blur(120px);
  display: flex;
}

.logo-container {
  width: calc(573px + 8%);
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}

.logo-container img {
  width: 29.84%;
  height: 46.66%;
}

.title-container {
  width: max-content;
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}

.bar {
  width: 680px;
  height: 2px;
  background-image: linear-gradient(to right, rgb(162,99,207) 0%,rgb(129,148,240) 100%);
}

.subtitle {
  width: max-content;
  font-size: 60px;
  letter-spacing: 11px;
  background-clip: text;
  color: transparent;
  background-image: linear-gradient(to right, rgb(162,99,207) 0%,rgb(129,148,240) 100%);
}

.prompt {
  margin-top: 69px;
  font-size: 24px;
  letter-spacing: 3px;
  color: #8D51B8;
}

.tags {
  margin-top: 25px;
}

.circle1{
  position: fixed;
  left: 950px;
  top: -265px;
  width: 1291px;
  height: 1291px;
  opacity: 1;
  background: rgba(224, 209, 235, 1);
  box-shadow: -5px 0 15px
  rgba(0, 0, 0, 0.25);
  border-radius: 50%;
}

.circle2{
  position: fixed;
  left: 629px;
  top: -265px;
  width: 1291px;
  height: 1291px;
  opacity: 1;
  background: rgba(215, 220, 245, 1);
  box-shadow: -5px 0 15px
  rgba(0, 0, 0, 0.25);
  border-radius: 50%;
}

.Main {
  display: flex;
  position: absolute;
  height: 100%;
  width: 100%;
  background-color: transparent;
  padding: 0;
  flex-shrink: 0;
  margin: 0;
}

el-col {
  display: flex;
  justify-content: center;
}
</style>

<script lang="ts" setup>
import Tags from "@/components/Tags.vue";

const tagsGroup : String[][] = [["交流"], ["分享"], ["资料"], ["归档"]];
const tagsColor: { key: string, color: string }[] =
    [
      {key: "交流", color: "#A263CF"},
      {key: "分享", color: "#9774DA"},
      {key: "资料", color: "#8C84E5"},
      {key: "归档", color: "#8194F0"}
    ];

</script>

<template>
  <div class="Main">
    <div class="m"></div>
    <div class="circle2"></div>
    <div class="circle1"></div>
    <el-container class="title-container">
      <el-col>
        <div class="title"><p style="color: #A263CF">LAS</p><p>&nbsp;</p><p style="color: #8194F0">LOG</p></div>
        <div class="bar"></div>
        <div class="subtitle">Lazy Alien Server</div>
      </el-col>
    </el-container>
  </div>
</template>

<style scoped>

.title{

  -webkit-text-stroke: 1px #ffffff;
  width: max-content;
  text-shadow: -4px 4px 4px  rgba(0, 0, 0, 0.25);
  font-size: 160px;
  font-weight: 900;
  display: flex;
}

.title-container{
  position: fixed;
  left: 10.89%;
  top: 11.85%;
  width: 80.67%;
  height: 580px;
  opacity: 0.9;
  border-radius: 60px;
  background: rgba(255, 255, 255, 0.3);

  border: 3px solid rgba(88, 125, 255, 0.35);

  backdrop-filter: blur(120px);
}

.subtitle {
  font-size: 60px;
  letter-spacing: 12px;
  color: red;
}

.circle1{
  position: fixed;
  left: 950px;
  top: -265px;
  width: 1291px;
  height: 1291px;
  opacity: 1;
  background: rgba(224, 209, 235, 1);
  box-shadow: -5px 0px 15px
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
  box-shadow: -5px 0px 15px
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

.m {
  display: flex;
  justify-content: center; /* 水平居中 */
  align-items: center; /* 垂直居中 */
  height: 100vh; /* 示例高度，假设你要让内容在整个视窗内垂直居中 */
  width: 100%; /* 或者指定一个宽度以适应其容器 */
  margin: 0;
  padding: 0;
  font-size: 20px;
  font-weight: bold;
  text-align: center;
  color: rgb(100, 100, 100);
}
</style>

<script lang="ts" setup>

</script>

<template>
  <div class="Main">
    <div class="m"></div>
    <div class="circle2"></div>
    <div class="circle1"></div>
    <div class="background"></div>
    <div class="LAS">LAS</div>
    <div class="LOG">LOG</div>
    <div class="Lazy_Alien_Server">Lazy Alien Server</div>
  </div>
</template>

<style scoped>
.Lazy_Alien_Server{
  position: fixed;
  left: 580px;
  top: 290px;
  width: 677px;
  height: 80px;
  opacity: 1;
  /** 文本1 */
  font-size: 45px;
  font-weight: 400;
  letter-spacing: 9px;
  line-height: 79.56px;
  color: rgba(166, 166, 166, 1);
  text-align: center;
  vertical-align: top;

}

.LOG{
  position: fixed;
  left: 720px;
  top: 150px;
  width: 687px;
  height: 188px;
  opacity: 1;

  //border: 1px solid rgba(224, 209, 235, 1);
  -webkit-text-stroke: 1px #ffffff;

  text-shadow: -4px 4px 4px  rgba(0, 0, 0, 0.25);
  font-size: 120px;
  font-weight: 900;
  letter-spacing: 0px;
  line-height: 187.52px;
  color: rgba(129, 148, 240, 1);
}

.LAS{
  position: fixed;
  left: 640px;
  top: 150px;
  opacity: 1;
  //border: 1px solid rgba(224, 209, 235, 1);
  -webkit-text-stroke: 1px #ffffff;

  text-shadow: -4px 4px 4px  rgba(0, 0, 0, 0.25);
  /** 文本1 */
  font-size: 120px;
  font-weight: 900;
  letter-spacing: 0px;
  line-height: 187.52px;
  color: rgba(162, 99, 207, 1);

}

.background{
  position: fixed;
  left: 200px;
  top: 70px;
  width: 1100px;
  height: 580px;
  opacity: 0.9;
  border-radius: 60px;
  background: rgba(255, 255, 255, 0.3);

  border: 3px solid rgba(88, 125, 255, 0.35);

  backdrop-filter: blur(120px);
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

<template>
  <div class="Main">
    <div class="m">Main</div>
  </div>
</template>

<style scoped>
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

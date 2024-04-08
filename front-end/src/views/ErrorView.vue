<template>
  <div v-cloak class="error">
    <div class="item">
      <div class="up">
        <svg class="bad" fill="none" height="109" viewBox="0 0 177 109" width="177" xmlns="http://www.w3.org/2000/svg">
          <g opacity="1" transform="translate(0 0)">
            <path id="拼合图形"
                  d="M32.9364 49.9027L20.8464 32.6627C20.2764 31.8527 20.2764 30.5327 20.8464 29.7227C21.4164 28.9127 22.3364 28.9127 22.9064 29.7227L34.9964 46.9627L47.0964 29.7227C47.6664 28.9127 48.5864 28.9127 49.1564 29.7227C49.7264 30.5327 49.7264 31.8527 49.1564 32.6627L37.0664 49.9027L49.1564 67.1527C49.7264 67.9627 49.7264 69.2827 49.1564 70.0927C48.5864 70.9027 47.6664 70.9027 47.0964 70.0927L34.9964 52.8527L22.9064 70.0927C22.3364 70.9027 21.4164 70.9027 20.8464 70.0927C20.2764 69.2827 20.2764 67.9627 20.8464 67.1527L32.9364 49.9027ZM139.936 50.9027L127.846 33.6627C127.276 32.8527 127.276 31.5327 127.846 30.7227C128.417 29.9127 129.337 29.9127 129.906 30.7227L141.996 47.9627L154.096 30.7227C154.666 29.9127 155.586 29.9127 156.156 30.7227C156.726 31.5327 156.726 32.8527 156.156 33.6627L144.066 50.9027L156.156 68.1527C156.726 68.9627 156.726 70.2827 156.156 71.0927C155.586 71.9027 154.666 71.9027 154.096 71.0927L141.996 53.8527L129.906 71.0927C129.337 71.9027 128.417 71.9027 127.846 71.0927C127.276 70.2827 127.276 68.9627 127.846 68.1527L139.936 50.9027ZM107 72.58C107 73.46 106.29 74.17 105.42 74.17L70.5799 74.17C69.7099 74.17 68.9999 73.46 68.9999 72.58C68.9999 71.71 69.7099 71 70.5799 71L105.42 71C106.29 71 107 71.71 107 72.58Z"
                  fill-rule="evenodd" opacity="1"
                  style="fill:#383838"></path>
          </g>
        </svg>
        <div class="code">
          {{ code }}
        </div>
      </div>
      <div class="line"></div>
      <div class="down">
        喜报！页面炸了！
      </div>
    </div>
  </div>
</template>

<style scoped>
.error {
  display: flex;
  position: absolute;
  height: 100%;
  width: 100%;
  background: linear-gradient(to right, rgba(228, 230, 242, 100%), rgba(234, 225, 240, 100%));
  padding: 0;
  flex-shrink: 0;
  margin: 0;
  border: none;
  justify-content: center;
  align-items: center;
}

.item {
  width: 50%;
}

.up {
  display: flex;
  justify-content: center;
  align-items: center;
}

.bad {
  position: relative;
  padding-left: 30px;
  padding-right: 30px;
  height: 100px;
}

.code {
  padding-left: 30px;
  padding-right: 30px;
  padding-bottom: 15px;
  position: relative;
  height: min-content;
  font-family: "Noto Sans SC";
  font-weight: lighter;
  font-size: 48px;
  color: #383838;
  user-select: none;
}

.line {
  height: 1px;
  background-color: #383838;
}

.down {
  user-select: none;
  padding-top: 20px;
  font-family: "Noto Sans SC";
  font-weight: lighter;
  font-size: 24px;
  color: #383838;
}
</style>

<script lang="ts" setup>
import {onMounted, Ref, ref} from "vue";
import {useRoute} from "vue-router";

let code: Ref<string | string[] | undefined> = ref("Error Code");

onMounted(() => {
  const route = useRoute();
  code.value = route.params.text == "" ? 'Error Code' : useRoute().params.text;
});
</script>

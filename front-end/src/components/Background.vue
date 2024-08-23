<script lang="ts" setup>
import {ref} from "vue";

const circles = ref([
  {
    width: "1291px",
    height: "1291px",
    top: "-9.72%",
    left: "-6.56%",
    transformOrigin: "640px 640px",
    backgroundColor: "#E0D1EB",
    opacity: 1,
    animationDuration: "7s"
  },
  {
    width: "582px",
    height: "582px",
    top: "-6.85%",
    left: "66.40%",
    transformOrigin: "270px 310px",
    backgroundColor: "#AAB7FA",
    opacity: 0.66
  },
  {
    width: "544px",
    height: "544px",
    top: "12.96%",
    left: "11.09%",
    transformOrigin: "250px 250px",
    backgroundColor: "#D1C5F0",
    opacity: 1,
    animationDuration: "6s"
  },
  {
    width: "1291px",
    height: "1291px",
    top: "-6.85%",
    left: "73.85%",
    transformOrigin: "670px 670px",
    backgroundColor: "#E0D1EB",
    opacity: 1,
    animationDuration: "5s"
  },
  {
    width: "1324px",
    height: "1324px",
    top: "55.37%",
    right: "46.45%",
    transformOrigin: "700px 650px",
    backgroundColor: "#D7DCF5",
    opacity: 1
  },
  {
    width: "804px",
    height: "804px",
    top: "18.14%",
    left: "44.32%",
    transformOrigin: "460px 460px",
    backgroundColor: "#CDBDF2",
    opacity: 0.82
  },
  {
    width: "894px",
    height: "894px",
    top: "-35.74%",
    left: "25.31%",
    transformOrigin: "480px 490px",
    backgroundColor: "#D7DCF5",
    opacity: 1
  },
  {
    width: "1067px",
    height: "1067px",
    top: "63.33%",
    left: "39.42%",
    transformOrigin: "500px 600px",
    backgroundColor: "#C4CCF5",
    opacity: 0.76,
    animationDuration: "5s"
  },
  {
    width: "658px",
    height: "658px",
    top: "-37.31%",
    left: "17.60%",
    transformOrigin: "300px 400px",
    backgroundColor: "#E0D1EB",
    opacity: 0.71,
    animationDuration: "3s"
  },
  {
    width: "722px",
    height: "722px",
    top: "-29.81%",
    left: "-6.56%",
    transformOrigin: "300px 300px",
    backgroundColor: "#C5CEFC",
    opacity: 0.55
  }
])
</script>

<template>
  <div id="background">
    <div v-for="circle in circles" :class="(circle.opacity < 1) ? 'circle' : 'circle2'" :style="circle"></div>
  </div>
</template>

<style scoped>
#background {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgb(231, 228, 241);

  filter: blur(160px);
  overflow: hidden;
}

.circle {
  position: fixed;
  border-radius: 50%;
  transform: translate(-50%, -50%); animation: rotate-around-center 3s linear infinite;
}

.circle2 {
  position: fixed;
  border-radius: 50%;
  transform: translate(-50%, -50%); animation: rotate-around-center2 3s linear infinite;
}

@keyframes rotate-around-center {
  from {
    transform: rotate(0deg);
  }
  50% {
    opacity: 0.5;
  }
  to {
    transform: rotate(360deg);
  }
}

@keyframes rotate-around-center2 {
  from {
    transform: rotate(0deg);
  }
  50% {
    opacity: 0.2;
  }
  to {
    transform: rotate(-360deg);
  }
}

</style>
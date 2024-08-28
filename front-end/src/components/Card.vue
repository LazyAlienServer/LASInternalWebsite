<script setup lang="ts">
import {reactive, ref} from "vue";

export interface CardProps {
  backgroundColor?: string;
  borderRadius?: string;
  boxShadow?: string;
  height?: string;
  width?: string;
  padding?: string;
  margin?: string;

  shouldDivideLine?: boolean;
  divideColor?: string;
  divideHeight?: string;
  divideWidth?: string;
  divideMargin?: string;

  shouldBadge?: boolean;
  badgeSVG?: string;

}

const props = withDefaults(defineProps<CardProps>(), {
  backgroundColor: 'rgba(255,255,255,0.74)',
  borderRadius: '10px',
  boxShadow: '0 0 10px rgba(0,0,0,0.1)',
  height: 'max-content',
  width: 'auto',
  padding: '10px',
  margin: '10px',

  shouldDivideLine: false,
  divideColor: '#000000',
  divideHeight: '2px',
  divideWidth: '100%',
  divideMargin: '0',

 shouldBadge: false,
})

const shouldBadge = ref(props.shouldBadge)

const cardStyle = reactive({
  backgroundColor: props.backgroundColor,
  borderRadius: props.borderRadius,
  boxShadow: props.boxShadow,
  height: props.height,
  width: props.width,
  padding: props.padding,
  margin: props.margin,
})

const dividerStyle = reactive({
  color: props.divideColor,
  width: props.divideWidth,
  borderTop: `${props.divideHeight} solid ${props.divideColor}`,
  margin: props.divideMargin
})

</script>

<template>
  <div class="card" :style="cardStyle">
    <div v-if="shouldBadge" id="Badge">
      <img :src="props.badgeSVG" alt="Badge" @error="()=>{
        shouldBadge = false
      }" />
    </div>
    <header v-if="$slots.header">
      <slot name="header"></slot>
    </header>
    <hr v-if="props.shouldDivideLine" id="Divide" :style="dividerStyle">
    <main v-if="$slots.default">
      <slot></slot>
    </main>
    <footer v-if="$slots.footer">
      <slot name="footer"></slot>
    </footer>
  </div>
</template>

<style scoped>
.card{
  display: flex;
  flex-direction: column;
  position: relative;
}

#Badge{
  position: absolute;
  transform: translate(-50%, -50%);
}

#Divide{
  position: relative;
}
</style>
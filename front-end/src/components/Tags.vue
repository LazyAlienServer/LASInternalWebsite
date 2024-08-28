<template>
  <div id="tags" class="tags">
    <div v-for="tagsGroup in tagGroups" :style="groupStyle" class="tags-group">
      <div v-for="(tag, index) in tagsGroup" :style="[tagGroupItemStyle,{backgroundColor:getColor(tag)},getPadding(index, tagsGroup.length)]"
           class="tags-group-item">
        <p :style="tagStyle" class="tag">{{ tag }}</p>
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {reactive} from "vue";

// 配置组件参数列表
export interface TagsProps {
  tagGroups?: string[][],
  tagsColor?: { key: string, color: string }[],
  radius?: string,

  groupGap?: string,
  itemGap?: string,

  tagsFontSize?: string,
  fontFamily?: string,
  fontWeight?: string,

  textColor?: string,
  letterSpacing?: string
}

//配置组件参数默认值
const props = withDefaults(defineProps<TagsProps>(), {
  tagGroups: () => [[]],
  tagsColor: () => [],
  tagsFontSize: '16px',
  fontFamily: 'Microsoft YaHei UI',
  fontWeight: 'normal',
  radius: '4px',
  groupGap: '2px 10px 2px 0',
  itemGap: '0 5px',
  textColor: 'white',
  letterSpacing: '0px'
})
// 标签样式
const tagStyle = reactive({})
// 标签组样式
const tagGroupItemStyle = reactive({
  fontSize: props.tagsFontSize,
  fontFamily: props.fontFamily,
  fontWeight: props.fontWeight,
  borderRadius: props.radius,
  padding: props.itemGap,
  letterSpacing: props.letterSpacing
});
// 标签组整体样式
const groupStyle = reactive({
  color: props.textColor,
  margin: props.groupGap,
});

function getColor(tag: string) {
  let color = '#709ba2';
  props.tagsColor.forEach(element => {
    if (element.key == tag) {
      color = element.color;
    }
  })
  return color;
}

function getPadding(index: number, totalLength: number) {
  const paddingRightPixel = 25;
  return index != totalLength-1 ? {paddingRight: paddingRightPixel+'px', marginRight: (-1-paddingRightPixel)+'px'} : {}
}`  `

function init() {
  // if (tags.value){
  //   let tagsRoot = tags.value as HTMLElement;
  //   tagsRoot.childNodes.forEach(element => {
  //     console.log(element)
  //   })
  // }else setTimeout(init, 100)
}

init()

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.tags {
  display: flex;
  flex-direction: row;
  flex-wrap: wrap;
}

.tags-group {
  display: flex;
  position: relative;
  flex-direction: row;
}

.tags-group-item {
  width: max-content;
  height: max-content;
}

.tag {
  text-align: center;
}

p {
  padding-top: 3px;
  padding-bottom: 3px;
  padding-left: 6px;
  padding-right: 6px;
}

</style>
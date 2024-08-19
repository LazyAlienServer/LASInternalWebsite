<template>
  <div>
    <el-radio-group v-model="radioGroup1" size="large">
      <el-radio-button label="All" value="all" @click="init0"/>
      <el-radio-button label="已审批的" value="approved"/>
      <el-radio-button label="未审批的" value="unapproved"/>
      <el-radio-button label="我审批的" value="myApproved" @click="whenClick"/>
      <el-radio-button label="未通过的" value="unsuccessful" @click="show"/>
      <el-radio-button label="通过的" value="successful" />
    </el-radio-group>
  </div>
  <div>
    <el-checkbox-group v-model="checkboxGroup1" size="large">
      <el-checkbox-button label="All" value="all"/>
      <el-checkbox-button label="急" value="urgent"/>
      <el-checkbox-button label="普" value="normal"/>
      <el-checkbox-button label="快" value="quick"/>
      <el-checkbox-button label="子" value="children"/>
    </el-checkbox-group>
  </div>
  <div>
    <el-radio-group v-model="radioGroup2" size="large">
      <el-radio-button label="All" value="all"/>
      <el-radio-button label="已审批的" value="approved" />
      <el-radio-button label="未审批的" value="unapproved"/>
      <el-radio-button label="未通过的" value="unsuccessful" />
      <el-radio-button label="通过的" value="successful" />
    </el-radio-group>
  </div>
  <el-table
      :data="project"
      style="width: 100%"
  >
    <el-table-column prop="name" label="name" sortable width="180" />
    <el-table-column prop="note" label="Name" width="180" />
    <el-table-column prop="pass" label="Address"  />
  </el-table>
</template>

<script lang="ts" setup>

import {TableColumnCtx} from 'element-plus'
import {onMounted, ref} from "vue";
import {request} from '@/main'
import {getCookie} from "typescript-cookie";

interface User {
  date: string
  name: string
  address: string
}

interface Project {
  //项目名,唯一
  name: string
  //进度
  progress: number
  //负责人,作者
  authors: [string]
  //预计开始时间
  start_time: string
  //地点
  place: Place
  //原创?
  innovation: boolean
  //是否通过(default false)
  pass: boolean
  //审核人(auditor != null && pass == false 才为未通过状态)
  auditor: string
  //介绍
  note: string
  //图片
  picture: [string]
  //类型
  type: [number]
  //子项目
  children: [number]
}

interface Place{
  //纬度
  dimension: string
  x: number
  y: number
  z: number
}


const radioGroup1 = ref('');
const checkboxGroup1 = ref([]);
const radioGroup2 = ref('');



//All
let project:Project[] = [];
//未审批的
let unapproved:Project[] = [];
//已审批的
let approved:Project[] = [];
//我审批的
let myApproved:Project[] = [];
//未通过的
let unsuccessful:Project[] = [];
//通过的
let successful:Project[] = [];

//急      0
let urgent:Project[] = [];
//快      1
let quick:Project[] = [];
//普      2
let normal:Project[] = [];
//子     -1
let children:Project[] = [];


//筛选组
let screeningGroup1:Project[] = [];
let screeningGroup2:Project[] = [];
let screeningGroup3:Project[] = [];

const formatter = (row: User, column: TableColumnCtx<User>) => {
  return row.address
}

interface ProjectHash{
  [key:string]:Project
}


function getUserNameByToken(token:String):String{
  request.post('usersignin/crud/getUserNameByToken/?token='+token, {}).then(r => {
    return String(r.data);
  });
  return "";
}

function init0(){
  //获取所有项目
  request.get('project-managerial-audits/api/getProjectList', {}).then(r => {
    //转interface
    const projectObj = JSON.parse(r.data.data)
    project = projectObj as [Project]

    for (let pro of project) {
      if (!pro.pass && pro.auditor == "") {
        unapproved.push(pro);
      } else {
        if (pro.auditor != "") {
          approved.push(pro)
        } else {
          if (pro.auditor == getUserNameByToken(String(getCookie("token")))) {
            myApproved.push(pro)
          } else {
            if (!pro.pass && pro.auditor != "") {
              unsuccessful.push(pro)
            } else {
              if (pro.pass && pro.auditor != "") {
                successful.push(pro)
              }
            }
          }
        }
      }
    }
  });


  //获取类型为急的项目
  request.post('project-managerial-audits/api/getProjectByType/?type=0', {}).then(r => {
    const projectObj = JSON.parse(r.data.data)
    urgent = projectObj as [Project]
  });

  //获取类型为快的项目
  request.post('project-managerial-audits/api/getProjectByType/?type=1', {}).then(r => {
    const projectObj = JSON.parse(r.data.data)
    quick = projectObj as [Project]
  });

  //获取类型为普的项目
  request.post('project-managerial-audits/api/getProjectByType/?type=2', {}).then(r => {
    const projectObj = JSON.parse(r.data.data)
    normal = projectObj as [Project]
  });

  //获取类型为子的项目
  request.post('project-managerial-audits/api/getProjectByType/?type=-1', {}).then(r => {
    const projectObj = JSON.parse(r.data.data)
    children = projectObj as [Project]
  });

}


function whenClick() {

  //设定被筛选对象1
  let s1 = String(radioGroup1.value);
  switch (s1) {
    case "all":
      screeningGroup1 = project;
      break;
    case "approved":
      screeningGroup1 = approved;
      break;
    case "myApproved":
      screeningGroup1 = myApproved;
      break;
    case "unsuccessful":
      screeningGroup1 = unsuccessful;
      break;
    case "successful":
      screeningGroup1 = successful;
      break;
    case "unapproved":
      screeningGroup1 = unapproved;
      break;
  }


  //设定被筛选对象2
  for (let s2 of checkboxGroup1.value) {
    let s = String(s2);
    switch (s) {
      case "all":
        screeningGroup2 = project;
        break;
      case "urgent":
        screeningGroup2 = urgent;
        break;
      case "normal":
        screeningGroup2 = normal;
        break;
      case "quick":
        screeningGroup2 = quick;
        break;
      case "children":
        screeningGroup2 = children;
        break;
    }
  }


  if (radioGroup1.value != "myApproved"){

    let result:ProjectHash = {}
    for (let project of screeningGroup1) {
      result[project.name]=project;
    }
    for (let project of screeningGroup2) {
      result[project.name]=project;
    }


    for (let resultKey in result) {
      console.log(result[resultKey])
    }
  }else {

    //设定被筛选对象3
    switch (radioGroup2.value) {
      case "approved":
        screeningGroup3 = approved;
        break;
      case "unsuccessful":
        screeningGroup3 = unsuccessful;
        break;
      case "successful":
        screeningGroup3 = successful;
        break;
      case "unapproved":
        screeningGroup3 = unapproved
        break;
    }

    let result:ProjectHash = {}
    for (let project of screeningGroup1) {
      result[project.name]=project;
    }
    for (let project of screeningGroup2) {
      result[project.name]=project;
    }
    for (let project of screeningGroup3) {
      result[project.name]=project;
    }

    for (let resultKey in result) {
      console.log(result[resultKey])
    }
  }
}

function show(){
  console.log(project)
  console.log("---------start----------")
  console.log("---------radioGroup1----------")
  console.log(radioGroup1.value)
  console.log("---------checkboxGroup1----------")
  console.log(checkboxGroup1.value)
  console.log("---------radioGroup2----------")
  console.log(radioGroup2.value)
  console.log("---------project list----------")
  for (let project1 of unapproved) {
    console.log(project1.name)
  }
  console.log("--------urgent(0/急)-----------")
  for (let project1 of urgent) {
    console.log(project1.name)
  }
  console.log("----------children(-1/子)---------")
  for (let project1 of children) {
    console.log(project1.name)
  }
  console.log("---------scLIST----------")
  console.log(screeningGroup1)
  console.log(screeningGroup2)
  console.log(screeningGroup3)
  console.log("---------EEEENNNNDDDD----------")
}





const tableData: User[] = [
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },

]


const table: User[] = []
onMounted(() => {
  init0()
});

</script>
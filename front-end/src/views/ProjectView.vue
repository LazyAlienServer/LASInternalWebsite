<template>
    <div>
        <el-radio-group v-model="radioGroup1" size="large">
            <el-radio-button
                label="All"
                value="all"
                @click="initializeProjects"
            />
            <el-radio-button label="已审批的" value="approved" />
            <el-radio-button label="未审批的" value="unapproved" />
            <el-radio-button
                label="我审批的"
                value="myApproved"
                @click="handleRadioClick"
            />
            <el-radio-button
                label="未通过的"
                value="unsuccessful"
                @click="displayProjectDetails"
            />
            <el-radio-button label="通过的" value="successful" />
        </el-radio-group>
    </div>
    <div>
        <el-checkbox-group v-model="checkboxGroup1" size="large">
            <el-checkbox-button label="All" value="all" />
            <el-checkbox-button label="急" value="urgent" />
            <el-checkbox-button label="普" value="normal" />
            <el-checkbox-button label="快" value="quick" />
            <el-checkbox-button label="子" value="children" />
        </el-checkbox-group>
    </div>
    <div>
        <el-radio-group v-model="radioGroup2" size="large">
            <el-radio-button label="All" value="all" />
            <el-radio-button label="已审批的" value="approved" />
            <el-radio-button label="未审批的" value="unapproved" />
            <el-radio-button label="未通过的" value="unsuccessful" />
            <el-radio-button label="通过的" value="successful" />
        </el-radio-group>
    </div>
    <el-table :data="allProjects" style="width: 100%">
        <el-table-column prop="name" label="name" sortable width="180" />
        <el-table-column prop="note" label="Name" width="180" />
        <el-table-column prop="pass" label="Address" />
    </el-table>
</template>

<script lang="ts" setup>
import { TableColumnCtx } from "element-plus";
import { onMounted, ref } from "vue";
import { request } from "@/main";
import { getCookie } from "typescript-cookie";

interface User {
    date: string;
    name: string;
    address: string;
}

interface Project {
    name: string;
    progress: number;
    authors: [string];
    start_time: string;
    place: Place;
    innovation: boolean;
    pass: boolean;
    auditor: string;
    note: string;
    type: [number];
    children: [number];
}

interface Place {
    dimension: string;
    x: number;
    y: number;
    z: number;
}

const radioGroup1 = ref("");
const checkboxGroup1 = ref([]);
const radioGroup2 = ref("");

// 项目列表
let allProjects: Project[] = [];
let unapprovedProjects: Project[] = [];
let approvedProjects: Project[] = [];
let myApprovedProjects: Project[] = [];
let unsuccessfulProjects: Project[] = [];
let successfulProjects: Project[] = [];
let urgentProjects: Project[] = [];
let quickProjects: Project[] = [];
let normalProjects: Project[] = [];
let childrenProjects: Project[] = [];

// 筛选组
let filteredProjects1: Project[] = [];
let filteredProjects2: Project[] = [];
let filteredProjects3: Project[] = [];

const formatter = (row: User, column: TableColumnCtx<User>) => {
    return row.address;
};

interface ProjectHash {
    [key: string]: Project;
}

function getUserNameByToken(token: string): string {
  request
      .post("usersignin/crud/getUserNameByToken/?token=" + token, {})
      .then((r) => {
        return String(r.data);
      });
  return "";
}

function fetchProjects(url: string, type: string, targetArray: Project[]) {
    request.post(url, {}).then((r) => {
        const projectObj = JSON.parse(r.data.data);
        targetArray.push(...(projectObj as [Project]));
    });
}

function initializeProjects() {
    // 获取所有项目
    request
        .get("project-managerial-audits/api/getProjectList", {})
        .then((r) => {
            const projectObj = JSON.parse(r.data.data);
            allProjects = projectObj as [Project];

        for (let pro of allProjects) {
          if (!pro.pass && pro.auditor == "") {
            unapprovedProjects.push(pro);
          } else if (pro.auditor != "") {
            approvedProjects.push(pro);
          } else if (
              pro.auditor ==
              getUserNameByToken(String(getCookie("token")))
          ) {
            myApprovedProjects.push(pro);
          } else if (!pro.pass && pro.auditor != "") {
            unsuccessfulProjects.push(pro);
          } else if (pro.pass && pro.auditor != "") {
            successfulProjects.push(pro);
          }
        }
      });

    // 获取不同类型的项目
    fetchProjects(
        "project-managerial-audits/api/getProjectByType/?type=0",
        "urgent",
        urgentProjects
    );
    fetchProjects(
        "project-managerial-audits/api/getProjectByType/?type=1",
        "quick",
        quickProjects
    );
    fetchProjects(
        "project-managerial-audits/api/getProjectByType/?type=2",
        "normal",
        normalProjects
    );
    fetchProjects(
        "project-managerial-audits/api/getProjectByType/?type=-1",
        "children",
        childrenProjects
    );
}

function handleRadioClick() {
    // 设定被筛选对象1
    let s1 = String(radioGroup1.value);
    switch (s1) {
        case "all":
            filteredProjects1 = allProjects;
            break;
        case "approved":
            filteredProjects1 = approvedProjects;
            break;
        case "myApproved":
            filteredProjects1 = myApprovedProjects;
            break;
        case "unsuccessful":
            filteredProjects1 = unsuccessfulProjects;
            break;
        case "successful":
            filteredProjects1 = successfulProjects;
            break;
        case "unapproved":
            filteredProjects1 = unapprovedProjects;
            break;
    }

    // 设定被筛选对象2
    for (let s2 of checkboxGroup1.value) {
        let s = String(s2);
        switch (s) {
            case "all":
                filteredProjects2 = allProjects;
                break;
            case "urgent":
                filteredProjects2 = urgentProjects;
                break;
            case "normal":
                filteredProjects2 = normalProjects;
                break;
            case "quick":
                filteredProjects2 = quickProjects;
                break;
            case "children":
                filteredProjects2 = childrenProjects;
                break;
        }
    }

    if (radioGroup1.value != "myApproved") {
        let result: ProjectHash = {};
        for (let project of filteredProjects1) {
            result[project.name] = project;
        }
        for (let project of filteredProjects2) {
            result[project.name] = project;
        }

        for (let resultKey in result) {
            console.log(result[resultKey]);
        }
    } else {
        // 设定被筛选对象3
        switch (radioGroup2.value) {
            case "approved":
                filteredProjects3 = approvedProjects;
                break;
            case "unsuccessful":
                filteredProjects3 = unsuccessfulProjects;
                break;
            case "successful":
                filteredProjects3 = successfulProjects;
                break;
            case "unapproved":
                filteredProjects3 = unapprovedProjects;
                break;
        }

        let result: ProjectHash = {};
        for (let project of filteredProjects1) {
            result[project.name] = project;
        }
        for (let project of filteredProjects2) {
            result[project.name] = project;
        }
        for (let project of filteredProjects3) {
            result[project.name] = project;
        }

        for (let resultKey in result) {
            console.log(result[resultKey]);
        }
    }
}

function displayProjectDetails() {
    console.log(allProjects);
    console.log("---------start----------");
    console.log("---------radioGroup1----------");
    console.log(radioGroup1.value);
    console.log("---------checkboxGroup1----------");
    console.log(checkboxGroup1.value);
    console.log("---------radioGroup2----------");
    console.log(radioGroup2.value);
    console.log("---------project list----------");
    for (let project1 of unapprovedProjects) {
        console.log(project1.name);
    }
    console.log("--------urgent(0/急)-----------");
    for (let project1 of urgentProjects) {
        console.log(project1.name);
    }
    console.log("----------children(-1/子)---------");
    for (let project1 of childrenProjects) {
        console.log(project1.name);
    }
    console.log("---------scLIST----------");
    console.log(filteredProjects1);
    console.log(filteredProjects2);
    console.log(filteredProjects3);
    console.log("---------EEEENNNNDDDD----------");
}

const tableData: User[] = [
    {
        date: "2016-05-03",
        name: "Tom",
        address: "No. 189, Grove St, Los Angeles",
    },
    {
        date: "2016-05-02",
        name: "Tom",
        address: "No. 189, Grove St, Los Angeles",
    },
    {
        date: "2016-05-04",
        name: "Tom",
        address: "No. 189, Grove St, Los Angeles",
    },
    {
        date: "2016-05-01",
        name: "Tom",
        address: "No. 189, Grove St, Los Angeles",
    },
];

const table: User[] = [];
onMounted(() => {
    initializeProjects();
});
</script>

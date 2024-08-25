import './plugins/axios'
import {createApp} from 'vue'
import App from '@/App.vue'
import 'element-plus/dist/index.css'
import router from './router'
import ElementPlus from 'element-plus'
import {zhCn} from "element-plus/es/locale/index";
import {request} from "@/plugins/axios";
import 'element-plus/theme-chalk/dark/css-vars.css'
import './styles/dark/css-vars.css'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

const containerSelector = '#LASLog';
const mountPoint = document.querySelector(containerSelector);

//@ts-ignore
if (mountPoint && mountPoint.__vue_app__ == undefined){
    const app = createApp(App);

    app.use(router)
        .use(ElementPlus, {locale: zhCn,})

    for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
        app.component(key, component)
    }

  app.mount('#LASLog')
}


// const request = new Request({});
export {request};
import './plugins/axios'
import {createApp} from 'vue'
import App from './App.vue'
import 'element-plus/dist/index.css'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import {zhCn} from "element-plus/es/locale/index";
import {Request} from "@/plugins/axios";
import 'element-plus/theme-chalk/dark/css-vars.css'
import './styles/dark/css-vars.css'


const app = createApp(App);
app.use(store).use(router).use(ElementPlus, {locale: zhCn,});
app.mount('#app');
const request = new Request({});
export {request};
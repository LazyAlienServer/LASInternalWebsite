import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { fileURLToPath, URL } from 'node:url'
import VitePluginVueDevTools from 'vite-plugin-vue-devtools'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    VitePluginVueDevTools(),
  ],
  resolve:{
    alias:[
      {find:'@',replacement:fileURLToPath(new URL('./src', import.meta.url))}
    ]
  },
  server:{
    port:8080
  },
  appType:'spa'
})

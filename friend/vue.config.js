const { defineConfig } = require('@vue/cli-service')
const apiProxyTarget = process.env.VUE_APP_API_PROXY_TARGET || 'http://localhost:9090'

module.exports = defineConfig({
  publicPath: './',
  lintOnSave: false, // lintOnSave:false用来关闭烦人的报错信息·
  transpileDependencies: true,
  devServer: {// devServer用来解决 ==> WebSocket connection to ‘ws://x.x.x.x:8080/ws‘ failed:报错
    port: 8080,
    client: {
      overlay: false
    },
    proxy: {
      '/api': {
        target: apiProxyTarget,
        secure: true,
        ws: true,
        changeOrigin: true,
        pathRewrite: {
          '^/api': ''
        }
      }
    }
  }
})

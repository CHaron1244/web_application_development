const {defineConfig} = require('@vue/cli-service')
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
        target: 'http://114.132.219.217:9090',
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

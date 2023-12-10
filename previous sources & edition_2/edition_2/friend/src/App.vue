<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>
<script>
  import VueRouter from 'vue-router'
  export default {
    name: 'App',
  }
</script>

<style >
  #app {
    /* flex:1; */
    font-family: Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin: 60px;
  }

  img {
    height: 200px
  }
</style>

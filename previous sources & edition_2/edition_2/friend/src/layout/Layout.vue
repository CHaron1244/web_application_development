<template>
  <div>
    <Header/>
    <div style="display: flex"><!--主体  display:flex用于创建一个弹性（flex）容器。它将容器中的子元素排列成一行或一列，并自动调整它们的大小和位置-->
      <CustomerAside v-if="this.user.userRole === 0" />
      <AdminAside v-if="this.user.userRole === 1" />
      <router-view style="flex: 1"/> <!--内容-->
      <!-- style="flex: 1;" 是将 flex 属性直接应用于 HTML 元素的内联样式。这个属性用于控制弹性项目（flex item）在弹性容器中的分配比例。
      在这种情况下，flex: 1; 将分配相同的比例给弹性项目，使它们平均占据可用空间 -->
    </div>
  </div>
</template>

<script>
import Header from '@/components/Header'
import CustomerAside from '@/components/CustomerAside'
import AdminAside from '@/components/AdminAside'

export default {
  // eslint-disable-next-line vue/multi-word-component-names
  name: 'Layout',
  components: {
    Header,
    CustomerAside,
    AdminAside
  },
  data () {
    return {
      user: {}
    }
  },
  created () {
    const str = sessionStorage.getItem('user') || '{}'
    this.user = JSON.parse(str)
  }
}
</script>

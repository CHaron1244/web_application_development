import Vue from 'vue'// 引入Vue核心库
import Vuex from 'vuex'

Vue.use(Vuex)// 应用Vuex插件

export default new Vuex.Store({
  state: {
    user: {}
  },
  mutations: {
    SET_USER(state, user) {
      state.user = user
    }
  },
  actions: {
    setUser({commit}, user) {
      commit('SET_USER', user)
    }
  },
  getters: {
    getUser: (state) => state.user
  }
})

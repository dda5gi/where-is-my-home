import Vue from "vue";
import Vuex from "vuex";
import createPersistedState from "vuex-persistedstate";

import memberStore from "@/store/modules/memberStore";
import qnaStore from "@/store/modules/qnaStore";
import houseStore from "@/store/modules/houseStore";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: { memberStore, qnaStore, houseStore },
  plugins: [
    createPersistedState({
      // 브라우저 종료시 제거하기 위해 localStorage가 아닌 sessionStorage로 변경. (default: localStorage)
      storage: sessionStorage,
    }),
  ],
});

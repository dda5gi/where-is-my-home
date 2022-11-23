import { listNotice } from "@/api/notice";

const noticeStore = {
  namespaced: true,

  state: {
    isModify: false,
    itemsPerPage: 5,
    page: 1,
    list: [],
    noticeNo: 0,
    notice: {},
  },

  mutations: {
    SET_PAGE: (state, page) => {
      state.page = page;
    },
    SET_ITEMS_PER_PAGE: (state, itemsPerPage) => {
      state.itemsPerPage = itemsPerPage;
    },
    SET_LIST: (state, list) => {
      list.forEach((notice, index) => {
        notice.no = index + 1;
      });
      state.list = list;
    },
    SET_NOTICE_NO: (state, noticeNo) => {
      state.noticeNo = noticeNo;
    },
    SET_IS_MODIFY: (state, flag) => {
      state.isModify = flag;
    },
  },

  actions: {
    async getList({ commit }) {
      await listNotice(({ data }) => {
        if (data.msg === "success") {
          commit("SET_LIST", data.payload.list);
        }
      });
    },
  },
};

export default noticeStore;

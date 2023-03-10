import jwtDecode from "jwt-decode";
import router from "@/router";
import {
  login,
  findById,
  logout,
  tokenRegeneration,
  regist,
  deleteMember,
  modifyMember,
} from "@/api/member";

const memberStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    memberInfo: null,
    isValidToken: false,
    isCorrectPassword: false,
    showMode: "info",
  },
  getters: {},
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_IS_VALID_TOKEN: (state, isValidToken) => {
      state.isValidToken = isValidToken;
    },
    SET_MEMBER_INFO: (state, memberInfo) => {
      state.memberInfo = memberInfo;
    },
    SET_SHOW_MODE: (state, mode) => {
      state.showMode = mode;
    },
    SET_IS_CORRECT_PASSWORD: (state, flag) => {
      state.isCorrectPassword = flag;
    },
  },
  actions: {
    async memberConfirm({ commit }, member) {
      await login(
        member,
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            let refreshToken = data["refresh-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            commit("SET_IS_VALID_TOKEN", true);
            sessionStorage.setItem("access-token", accessToken);
            sessionStorage.setItem("refresh-token", refreshToken);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
            commit("SET_IS_VALID_TOKEN", false);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    async getMemberInfo({ commit, dispatch }, token) {
      let decodeToken = jwtDecode(token);
      await findById(
        decodeToken.id,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_MEMBER_INFO", data.userInfo);
          } else {
            console.log("?????? ?????? ??????!!!!");
          }
        },
        async (error) => {
          console.log(
            "getMemberInfo() error code [?????? ???????????? ?????? ?????????.] ::: ",
            error.response.status
          );
          commit("SET_IS_VALID_TOKEN", false);
          await dispatch("tokenRegeneration");
        }
      );
    },

    async tokenRegeneration({ commit, state }) {
      await tokenRegeneration(
        JSON.stringify(state.memberInfo),
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            sessionStorage.setItem("access-token", accessToken);
            commit("SET_IS_VALID_TOKEN", true);
          }
        },
        async (error) => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken ?????? ?????? >> ?????? ?????????!!!!
          if (error.response.status === 401) {
            // ?????? ????????? ??? DB??? ????????? RefreshToken ??????.
            await logout(
              state.memberInfo.id,
              ({ data }) => {
                if (data.message === "success") {
                  console.log("???????????? ?????? ?????? ??????");
                } else {
                  console.log("???????????? ?????? ?????? ??????");
                }
                alert("RefreshToken ?????? ??????!!! ?????? ???????????? ?????????.");
                commit("SET_IS_LOGIN", false);
                commit("SET_MEMBER_INFO", null);
                commit("SET_IS_VALID_TOKEN", false);
                router.push({ name: "login" });
              },
              (error) => {
                console.log(error);
                commit("SET_IS_LOGIN", false);
                commit("SET_MEMBER_INFO", null);
              }
            );
          }
        }
      );
    },

    async memberLogout({ state, commit }) {
      await logout(
        state.memberInfo.id,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_IS_LOGIN", false);
            commit("SET_MEMBER_INFO", null);
            commit("SET_IS_VALID_TOKEN", false);
          } else {
            console.log("?????? ?????? ??????!!!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    async memberRegist(store, member) {
      console.log(store);
      console.log(member);
      await regist(
        member,
        ({ data }) => {
          if (data.message === "success") {
            console.log("?????? ?????? ??????!!!");
          } else {
            console.log("?????? ?????? ??????!!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    async memberModify({ commit, dispatch }, member) {
      await modifyMember(
        member,
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = sessionStorage.getItem("access-token");
            dispatch("getMemberInfo", accessToken);
          }
        },
        (error) => {
          console.log(error);
        }
      ).then(commit("SET_SHOW_MODE", "info"));
    },

    async memberDelete({ dispatch }, member) {
      await deleteMember(
        member,
        ({ data }) => {
          if (data.message === "success") {
            dispatch("memberLogout");
          } else {
            console.log("?????? ??????");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    async checkPassword({ state, commit }, password) {
      await login(
        { id: state.memberInfo.id, pwd: password },
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            let refreshToken = data["refresh-token"];
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            commit("SET_IS_VALID_TOKEN", true);
            commit("SET_IS_CORRECT_PASSWORD", true);
            sessionStorage.setItem("access-token", accessToken);
            sessionStorage.setItem("refresh-token", refreshToken);
          } else {
            console.log(data.message);
            commit("SET_IS_CORRECT_PASSWORD", false);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
  modules: {},
};

export default memberStore;

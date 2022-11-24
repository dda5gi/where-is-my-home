import jwtDecode from "jwt-decode";
import router from "@/router";
import { login, findById, logout, tokenRegeneration, regist, deleteMember } from "@/api/member";

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
            console.log("유저 정보 없음!!!!");
          }
        },
        async (error) => {
          console.log("getMemberInfo() error code [토큰 만료되어 사용 불가능.] ::: ", error.response.status);
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
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
          if (error.response.status === 401) {
            // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
            await logout(
              state.memberInfo.id,
              ({ data }) => {
                if (data.message === "success") {
                  console.log("리프레시 토큰 제거 성공");
                } else {
                  console.log("리프레시 토큰 제거 실패");
                }
                alert("RefreshToken 기간 만료!!! 다시 로그인해 주세요.");
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

    async getMemberInfoWithOutLogIn({ commit, dispatch }, token) {
      let decodeToken = jwtDecode(token);
      await findById(
        decodeToken.id,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_MEMBER_INFO", data.userInfo);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        async (error) => {
          if (error.response.status === 401) {
            console.log("getMemberInfo() error code [토큰 만료되어 사용 불가능.] ::: ", error.response.status);
            commit("SET_IS_VALID_TOKEN", false);
            await dispatch("tokenRegenerationWithOutLogIn");
          } else {
            console.log("정보 조회 실패");
          }
        }
      );
    },

    async tokenRegenerationWithOutLogIn({ commit, state }) {
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
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
          if (error.response.status === 401) {
            // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
            await logout(
              state.memberInfo.id,
              ({ data }) => {
                if (data.message === "success") {
                  console.log("리프레시 토큰 제거 성공");
                } else {
                  console.log("리프레시 토큰 제거 실패");
                }
                commit("SET_IS_VALID_TOKEN", false);
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
            console.log("유저 정보 없음!!!!");
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
            console.log("회원 가입 성공!!!");
          } else {
            console.log("회원 가입 실패!!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    // async memberModify({ commit }, member) {},

    async deleteMember({ dispatch }, member) {
      await deleteMember(
        member,
        ({ data }) => {
          if (data.message === "success") {
            dispatch("memberLogout");
          } else {
            console.log("탈퇴 실패");
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

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
    isModify: false,
  },
  getters: {},
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      // console.log("before isLogin : ", state.isLogin);
      // console.log("change to : ", isLogin);
      state.isLogin = isLogin;
      // console.log("after isLogin : ", state);
      // console.log("after isLogin : ", state.isLogin);
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_IS_VALID_TOKEN: (state, isValidToken) => {
      state.isValidToken = isValidToken;
    },
    SET_MEMBER_INFO: (state, memberInfo) => {
      state.isLogin = true;
      state.memberInfo = memberInfo;
    },
    SET_MODIFY_MODE: (state, mode) => {
      state.isModify = mode;
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
            // console.log("login success token created!!!! >> ", accessToken, refreshToken);
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
      // console.log("2. getMemberInfo() decodeToken :: ", decodeToken);
      await findById(
        decodeToken.id,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_MEMBER_INFO", data.userInfo);
            // console.log("3. getMemberInfo data >> ", data.userInfo);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        async (error) => {
          console.log(
            "getMemberInfo() error code [토큰 만료되어 사용 불가능.] ::: ",
            error.response.status
          );
          commit("SET_IS_VALID_TOKEN", false);
          await dispatch("tokenRegeneration");
        }
      );
    },

    async tokenRegeneration({ commit, state }) {
      // console.log("토큰 재발급 >> 기존 토큰 정보 : {}", sessionStorage.getItem("access-token"));
      await tokenRegeneration(
        JSON.stringify(state.memberInfo),
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            // console.log("재발급 완료 >> 새로운 토큰 : {}", accessToken);
            sessionStorage.setItem("access-token", accessToken);
            commit("SET_IS_VALID_TOKEN", true);
          }
        },
        async (error) => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
          if (error.response.status === 401) {
            // console.log("갱신 실패");
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

    async memberLogout({ commit, state }) {
      // console.log("memberLogout memberInfo >> ", state.memberInfo);
      await logout(
        state.memberInfo.id,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_IS_LOGIN", false);
            commit("SET_MEMBER_INFO", null);
            commit("SET_IS_VALID_TOKEN", false);
            // console.log("isLogin in memberLogout >> ", state.isLogin);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
      console.log("isLogin in memberLogout22 >> ", state);
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

    async deleteMember({ commit }, member) {
      console.log(commit);
      await deleteMember(
        member,
        ({ data }) => {
          if (data.message === "success") {
            this.memberLogout();
          } else {
            console.log("탈퇴 실패");
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

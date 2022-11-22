<template>
  <div>
    <v-app-bar id="header" absolute app dark>
      <v-toolbar-title class="text-uppercase white--text mx-4">
        {{ appTitle }}
      </v-toolbar-title>
      <v-toolbar-items class="hidden-xs-only">
        <v-btn text v-for="item in menuItems" :key="item.title" :to="item.path">
          <v-icon left dark>{{ item.icon }}</v-icon>
          {{ item.title }}
        </v-btn>
      </v-toolbar-items>
      <v-spacer></v-spacer>
      <v-toolbar-items class="hidden-xs-only">
        <v-btn text to="/member/login" v-show="!isLogin">
          <v-icon left dark>login</v-icon>
          로그인
        </v-btn>
        <v-btn text to="/member/register" v-show="!isLogin">
          <v-icon left dark>person_add_alt</v-icon>
          회원가입
        </v-btn>
        <v-btn text @click.prevent="logout" v-show="isLogin">
          <v-icon left dark>logout</v-icon>
          로그아웃
        </v-btn>
        <v-btn text to="/member/info" v-show="isLogin">
          <v-icon left dark>account_circle</v-icon>
          회원정보
        </v-btn>
      </v-toolbar-items>
    </v-app-bar>
    <v-main> </v-main>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "TheHeaderNavbar",

  data() {
    return {
      appTitle: "Where Is My Home",
      sidebar: true,
      menuItems: [
        { title: "홈", path: "/", icon: "home" },
        { title: "공지사항", path: "/notice", icon: "campaign" },
        { title: "QnA", path: "/qna", icon: "question_answer" },
      ],
    };
  },

  computed: {
    ...mapState(memberStore, ["isLogin", "memberInfo"]),
  },

  methods: {
    ...mapActions(memberStore, ["memberLogout"]),

    async logout() {
      //vuex actions에서 memberLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ state에 isLogin, memberInfo 정보 변경)
      await this.memberLogout(this.memberInfo.id);
      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "main" });
    },
  },
};
</script>

<style>
#header {
  background-color: #6667ab;
}
a {
  text-decoration: none;
}
</style>

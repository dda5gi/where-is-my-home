<template>
  <div>
    <v-app-bar absolute app>
      <v-toolbar-title class="text-uppercase grey--text">
        {{ appTitle }}
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-toolbar-items class="hidden-xs-only">
        <v-btn text v-for="item in menuItems" :key="item.title" :to="item.path">
          <v-icon left dark>{{ item.icon }}</v-icon>
          {{ item.title }}
        </v-btn>
      </v-toolbar-items>
    </v-app-bar>
    <v-main> </v-main>
  </div>
</template>

<script>
export default {
  name: "TheHeaderNavbar",

  data() {
    return {
      appTitle: "Where Is My Home",
      sidebar: true,
      menuItems: [
        { title: "Home", path: "/", icon: "home" },
        { title: "Notice", path: "/notice", icon: "campaign" },
        { title: "QnA", path: "/qna", icon: "question_answer" },
        { title: "Login", path: "/user/login", icon: "login" },
        { title: "Info", path: "/user/info", icon: "account_circle" },
      ],
    };
  },
};
</script>

<style>
a {
  text-decoration: none;
}
</style>

<template>
  <v-app id="app">
    <the-header-navbar></the-header-navbar>
    <router-view></router-view>
  </v-app>
</template>

<script>
import TheHeaderNavbar from "@/components/TheHeaderNavbar";

export default {
  name: "App",
  components: { TheHeaderNavbar },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #6768ab;
  background: #f1f0ec;
}

a.router-link-exact-active {
  color: #89bfef;
}

a.router-link-exact-active:hover {
  text-decoration: none;
}
</style>

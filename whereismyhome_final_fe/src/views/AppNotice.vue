<template>
  <v-main>
    <router-view class="container"></router-view>
  </v-main>
</template>

<script>
export default {
  name: "AppNotice",
};
</script>

<style scoped>
.container {
  width: 80%;
  display: flex;
  justify-content: center;
  align-items: center;
}
</style>

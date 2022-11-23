<template>
  <div>
    <v-parallax :src="require(`@/assets/home_banner.jpg`)">
      <h1 class="text-h4 font-weight-thin mb-4" v-if="!isLogin">Welcome, Guest</h1>
      <h1 class="text-h4 font-weight-thin mb-4" v-else>
        Welcome, <b>{{ this.memberInfo.name }}[{{ this.memberInfo.id }}]</b>
      </h1>
    </v-parallax>
  </div>
</template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "AppMain",

  computed: {
    ...mapState(memberStore, ["isLogin", "memberInfo"]),
  },
};
</script>

<style></style>

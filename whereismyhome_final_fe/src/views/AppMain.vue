<template>
  <div class="text-on-img">
    <div class="background-wrap">
      <div class="content">
        <h1 class="text-h4 font-weight-thin mb-4" v-if="!isLogin">Welcome, Guest</h1>
        <h1 class="text-h4 font-weight-thin mb-4" v-else>
          <b>Welcome, {{ this.memberInfo.name }}[{{ this.memberInfo.id }}]</b>
        </h1>
        <!-- <span>Title</span>
        <span>Content</span> -->
      </div>
    </div>
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

<style>
.background-wrap {
  background-image: url("@/assets/home_banner.jpg");
  /* background-size: 272px 196px; */
  /* width: 272px; */
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.content {
  display: flex;
  flex-direction: column;
}

.content h1 {
  color: white;
}

.content span:nth-child(1) {
  font-size: 25px;
  font-weight: bold;
}

.content span:nth-child(2) {
  font-size: 20px;
}
</style>

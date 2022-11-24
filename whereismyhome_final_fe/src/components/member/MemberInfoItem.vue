<template>
  <v-card class="mx-auto">
    <div class="d-flex">
      <v-card-text class="text--primary text-left mx-3">
        <h3 class="my-2 grey--text" v-if="this.memberInfo.type === 'admin'">[관리자]</h3>
        <div class="mt-3"><b>이름:</b> {{ this.memberInfo.name }}</div>
        <div class="mt-1"><b>아이디:</b> {{ this.memberInfo.id }}</div>
      </v-card-text>
      <div id="button-container" class="d-flex">
        <v-btn class="font-weight-bold" text color="#6667AB" @click.prevent="showModify">수정</v-btn>
        <v-btn class="font-weight-bold" text color="#686369">탈퇴</v-btn>
      </div>
    </div>
    <v-divider class="mx-4"></v-divider>
    <v-card-subtitle>관심 지역 목록(카드형)</v-card-subtitle>
  </v-card>
</template>

<script>
import { mapMutations, mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberInfoItem",

  computed: {
    ...mapState(memberStore, ["memberInfo"]),
  },

  methods: {
    ...mapMutations(memberStore, ["SET_SHOW_MODE"]),

    showModify() {
      this.SET_SHOW_MODE("modify");
    },
  },
};
</script>

<style scoped>
#button-container {
  padding-top: 80px;
  padding-right: 15px;
}
</style>

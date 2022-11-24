<template>
  <v-card class="mx-auto">
    <v-card-text class="text--primary text-left mx-3">
      <h3 class="my-2 grey--text" v-if="this.memberInfo.type === 'admin'">[관리자]</h3>
      <div class="mt-3"><b>이름:</b> {{ this.memberInfo.name }}</div>
      <div class="mt-1"><b>아이디:</b> {{ this.memberInfo.id }}</div>
    </v-card-text>
    <v-divider class="mx-4"></v-divider>
    <v-card-subtitle>관심 지역 목록(카드형)</v-card-subtitle>
  </v-card>
</template>

<script>
import { mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberInfoItem",

  computed: {
    ...mapState(memberStore, ["memberInfo"]),
  },
};
</script>

<style></style>

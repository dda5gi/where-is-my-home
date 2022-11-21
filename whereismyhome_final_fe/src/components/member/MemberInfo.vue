<template>
  <v-main>
    <v-layout row wrap>
      <v-flex xs12 class="text-xs-center" mt-5>
        <h1>회원 정보</h1>
      </v-flex>
      <v-flex xs12 sm6 offset-sm3 mt-3>
        <member-info-item v-if="isModify"></member-info-item>
        <member-info-modify v-else></member-info-modify>
      </v-flex>
    </v-layout>
  </v-main>
</template>

<script>
import MemberInfoItem from "@/components/member/MemberInfoItem.vue";
import MemberInfoModify from "@/components/member/MemberInfoModify.vue";

// import { mapMutations, mapState } from "vuex";

// const memberStore = "memberStore";

export default {
  name: "MemberInfo",

  components: { MemberInfoItem, MemberInfoModify },
};
</script>

<style></style>

<template>
  <v-main>
    <v-layout row wrap>
      <v-flex xs12 class="text-xs-center" mt-5>
        <!-- <v-flex xs12 mt-5> -->
        <h1>회원 정보</h1>
      </v-flex>
      <v-flex xs12 sm6 offset-sm3 mt-3>
        <v-card class="mx-auto">
          <v-img
            class="white--text align-end"
            height="250px"
            src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
          >
            <v-card-title class="mx-4">사용자 정보</v-card-title>
          </v-img>
          <v-card-text class="text--primary text-left mx-3" v-show="this.isModify === false">
            <!-- <h3 class="my-2 grey--text" v-if="member.type === 'admin'">[관리자]</h3> -->
            <h3 class="my-2 grey--text">[관리자]</h3>
            <div><b>이름:</b> {{ member.name }}</div>
            <div><b>아이디:</b> {{ member.id }}</div>
          </v-card-text>
          <v-card-text class="text--primary text-left mx-3" v-show="this.isModify === true">
            <!-- <h3 class="my-2 grey--text" v-show="member.type === 'admin'">[관리자22]</h3> -->
            <h3 class="my-2 grey--text">[관리자22]</h3>
            <!-- <div><b>이름:</b> {{ member.name }}</div> -->
            <v-text-field class="pe-6" label="이름" v-model="member.name"></v-text-field>
            <!-- <div><b>아이디:</b> {{ member.id }}</div> -->
            <v-text-field class="pe-6" label="아이디" v-model="member.id"></v-text-field>
          </v-card-text>
          <v-card-actions class="mx-3 mb-2">
            <v-container v-show="this.isModify === false">
              <v-btn color="orange" text @click="showModify"> 수정 </v-btn>
              <v-btn color="red" text @click="deleteMember"> 탈퇴 </v-btn>
            </v-container>
            <v-container v-show="this.isModify === true">
              <v-btn color="orange" text @click="modifyMember"> 수정 </v-btn>
              <v-btn color="grey" text @click="closeModify"> 취소 </v-btn>
            </v-container>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </v-main>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberInfo",

  data() {
    return {
      member: {},
    };
  },

  created() {
    // this.$store.commit("memberStore/SET_MODIFY_MODE", false);
    this.SET_MODIFY_MODE(false);
    this.member = { ...this.memberInfo };
  },

  computed: {
    ...mapState(memberStore, ["isModify", "memberInfo"]),
  },

  methods: {
    ...mapMutations(memberStore, ["SET_MODIFY_MODE"]),
    ...mapActions(memberStore, ["getMemberInfo"]),

    showModify() {
      this.SET_MODIFY_MODE(true);
    },

    closeModify() {
      this.member = { ...this.memberInfo };
      this.SET_MODIFY_MODE(false);
    },

    async modifyMember() {
      console.log("modify Member");
      let accessToken = sessionStorage.getItem("access-token");
      await this.getMemberInfo(accessToken);
    },

    async deleteMember() {
      console.log("delete Member");
      let accessToken = sessionStorage.getItem("access-token");
      await this.deleteMember(accessToken);
    },
  },
};
</script>

<style></style>

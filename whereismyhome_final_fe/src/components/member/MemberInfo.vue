<template>
  <div>
    <v-layout row wrap>
      <v-flex xs12 class="text-xs-center" mt-5>
        <h1>회원 정보</h1>
      </v-flex>
      <v-flex xs12 sm6 offset-sm3 mt-3>
        <v-card class="mx-auto">
          <v-img
            class="white--text align-end"
            height="250px"
            src="https://cdn.vuetifyjs.com/images/cards/docks.jpg"
          >
          </v-img>
          <div class="mx-4 mt-2" v-show="isDelete === false">
            <v-card-text class="text--primary text-left mx-3" v-show="this.isModify === false">
              <h3 class="my-2 grey--text" v-if="member.type === 'admin'">[관리자]</h3>
              <div class="mt-3"><b>이름:</b> {{ member.name }}</div>
              <div class="mt-1"><b>아이디:</b> {{ member.id }}</div>
            </v-card-text>
            <v-card-text class="text--primary text-left mx-3" v-show="this.isModify === true">
              <h3 class="my-2 grey--text" v-show="member.type === 'admin'">[관리자]</h3>
              <v-text-field class="pe-6" label="이름" v-model="member.name"></v-text-field>
              <v-text-field class="pe-6" label="아이디" v-model="member.id"></v-text-field>
              <v-text-field
                class="pe-6"
                label="현재 비밀번호"
                type="password"
                v-model="member.pwd"
              ></v-text-field>
              <v-text-field
                class="pe-6"
                label="새로운 비밀번호"
                type="password"
                v-model="new_pwd"
              ></v-text-field>
              <v-text-field
                class="pe-6"
                label="새로운 비밀번호 확인"
                type="password"
                v-model="new_pwd_check"
              ></v-text-field>
            </v-card-text>
          </div>
          <div class="mx-4 mt-2" v-show="isDelete === true">
            <v-card-text class="text--primary text-left mx-3">
              <h3 class="my-2 grey--text" v-show="member.type === 'admin'">[관리자]</h3>
              <div><b>아이디:</b> {{ member.id }}</div>
              <v-text-field
                class="pe-6"
                label="비밀번호"
                type="password"
                v-model="member.pwd"
              ></v-text-field>
            </v-card-text>
          </div>
          <v-card-actions class="mx-3 mb-2">
            <div v-show="isDelete === false">
              <v-container v-show="this.isModify === false">
                <v-btn color="orange" text @click="showModify"> 수정 </v-btn>
                <v-btn color="red" text @click="showDelete"> 탈퇴 </v-btn>
              </v-container>
              <v-container v-show="this.isModify === true">
                <v-btn color="orange" text @click.prevent="modifyMember"> 수정 </v-btn>
                <v-btn color="grey" text @click="closeModify"> 취소 </v-btn>
              </v-container>
            </div>
            <div v-show="isDelete === true">
              <v-container>
                <v-btn color="orange" text @click.prevent="doDeleteMember"> 탈퇴 </v-btn>
                <v-btn color="grey" text @click="closeDelete"> 취소 </v-btn>
              </v-container>
            </div>
          </v-card-actions>
        </v-card>
      </v-flex>
    </v-layout>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberInfo",

  data() {
    return {
      member: {},
      new_pwd: "",
      new_pwd_check: "",
      isDelete: false,
    };
  },

  created() {
    this.SET_MODIFY_MODE(false);
    this.member = { ...this.memberInfo };
  },

  computed: {
    ...mapState(memberStore, ["isModify", "memberInfo"]),
  },

  methods: {
    ...mapMutations(memberStore, ["SET_MODIFY_MODE"]),
    ...mapActions(memberStore, ["getMemberInfo", "deleteMember"]),

    showModify() {
      this.SET_MODIFY_MODE(true);
    },

    closeModify() {
      this.member = { ...this.memberInfo };
      this.new_pwd = "";
      this.SET_MODIFY_MODE(false);
    },

    showDelete() {
      this.isDelete = true;
    },

    closeDelete() {
      this.member = { ...this.memberInfo };
      this.isDelete = false;
    },

    async modifyMember() {
      console.log("modify Member");
      let accessToken = sessionStorage.getItem("access-token");
      await this.getMemberInfo(accessToken);
    },

    async doDeleteMember() {
      console.log("delete Member");
      await this.deleteMember(this.member);
      this.$router.push({ name: "main" });
    },
  },
};
</script>

<style></style>

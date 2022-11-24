<template>
  <v-card class="mx-auto">
    <v-form ref="form" lazy-validation>
      <v-card-text class="text--primary text-left mx-3">
        <div class="mt-4 pe-6"><b>아이디:</b> {{ this.memberInfo.id }}</div>
        <v-text-field
          class="pe-6 mt-5"
          label="이름"
          v-model="member.name"
          :rules="user_nm_rule"
          required
        ></v-text-field>
        <v-text-field
          class="pe-6"
          v-model="now_pwd"
          label="현재 비밀번호"
          type="password"
          :rules="user_now_pw_rule"
          :error-messages="errorMessage"
          required
        ></v-text-field>
        <!-- @input="checkNowPassword($event.target.value)" -->
        <v-text-field
          class="pe-6"
          label="새로운 비밀번호"
          type="password"
          v-model="member.pwd"
          :rules="user_new_pw_rule"
          required
        ></v-text-field>
        <v-text-field
          class="pe-6"
          label="새로운 비밀번호 확인"
          type="password"
          v-model="new_pwd_check"
          :rules="user_new_pw_check_rule"
          required
        ></v-text-field>
      </v-card-text>
      <v-card-actions class="pb-3">
        <v-btn class="font-weight-bold ml-3" color="#6667AB" text @click.prevent="modifyMember">
          수정
        </v-btn>
        <v-btn class="font-weight-bold" color="#686369" text @click.prevent="closeModify">
          취소
        </v-btn>
      </v-card-actions>
    </v-form>
  </v-card>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberInfoModify",

  data() {
    return {
      member: { name: "", id: "", pwd: "" },
      now_pwd: "",
      new_pwd_check: "",
      user_nm_rule: [(v) => !!v || "필수 입력사항입니다."],
      user_now_pw_rule: [(v) => !!v || "필수 입력사항입니다."],
      user_new_pw_rule: [(v) => !!v || "필수 입력사항입니다."],
      user_new_pw_check_rule: [
        (v) => !!v || "필수 입력사항입니다.",
        (v) => v === this.new_pwd_check || "비밀번호가 일치하지 않습니다.",
      ],
      errorMessage: "",
    };
  },

  computed: {
    ...mapState(memberStore, ["memberInfo", "isCorrectPassword"]),
  },

  watch: {
    memberInfo() {
      this.member = { ...this.memberInfo };
      this.member.pwd = null;
      this.now_pwd = null;
      this.new_pwd_check = null;
    },
  },

  created() {
    this.member = { ...this.memberInfo };
    this.member.pwd = "";
    console.log(this.member);
  },

  methods: {
    ...mapMutations(memberStore, ["SET_SHOW_MODE"]),
    ...mapActions(memberStore, ["memberModify", "checkPassword"]),

    async modifyMember() {
      if (this.$refs.form.validate()) {
        await this.checkPassword(this.now_pwd).then(() => {
          if (this.isCorrectPassword) {
            this.errorMessage = "";
            this.memberModify(this.member);
          } else this.errorMessage = "올바르지 않은 비밀번호입니다.";
        });
      }
    },

    closeModify() {
      this.member = { ...this.memberInfo };
      this.member.pwd = "";
      this.SET_SHOW_MODE("info");
    },
  },
};
</script>

<style></style>

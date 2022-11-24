<template>
  <v-main>
    <v-layout row wrap>
      <v-flex xs12 class="text-xs-center" mt-5>
        <h1>회원 정보</h1>
      </v-flex>
      <v-flex xs12 sm6 offset-sm3 mt-3>
        <div v-if="!isPasswordChecked">
          <v-card class="mx-auto" max-width="35rem">
            <v-card-text>
              개인 정보 조회를 위해서는 인증이 필요합니다.<br />
              비밀번호 입력 후 확인 버튼을 클릭해 주세요
              <v-text-field
                class="mt-6"
                color="#6768ab"
                v-model="now_pwd"
                label="현재 비밀번호"
                outlined
                hide-details
              ></v-text-field>
            </v-card-text>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn class="mb-3" color="#6768ab" dark @click.prevent="setIsPasswordChecked">확인</v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </div>
        <div v-else>
          <v-card class="mx-auto">
            <v-img class="white--text align-end" height="250px" src="https://cdn.vuetifyjs.com/images/cards/docks.jpg">
            </v-img>
            <div class="mx-4 mt-2" v-show="isDelete === false">
              <v-card-text class="text--primary text-left mx-3" v-show="this.isModify === false">
                <h3 class="my-2 grey--text" v-if="member.type === 'admin'">[관리자]</h3>
                <div class="mt-3"><b>이름:</b> {{ member.name }}</div>
                <div class="mt-1"><b>아이디:</b> {{ member.id }}</div>
              </v-card-text>
              <v-card-text class="text--primary text-left mx-3" ref="form" v-show="this.isModify === true">
                <h3 class="my-2 grey--text" v-show="member.type === 'admin'">[관리자]</h3>
                <v-text-field class="pe-6" label="이름" v-model="member.name"></v-text-field>
                <v-text-field class="pe-6" label="아이디" v-model="member.id"></v-text-field>
                <v-text-field
                  class="pe-6"
                  v-model="now_pwd"
                  label="현재 비밀번호"
                  type="password"
                  :rules="[rules.required]"
                  :error-messages="nowPasswordErrors"
                ></v-text-field>
                <!-- @input="checkNowPassword($event.target.value)" -->
                <v-text-field
                  class="pe-6"
                  label="새로운 비밀번호"
                  type="password"
                  v-model="new_pwd"
                  :rules="[rules.required]"
                ></v-text-field>
                <v-text-field
                  class="pe-6"
                  label="새로운 비밀번호 확인"
                  type="password"
                  v-model="new_pwd_check"
                  :rules="[rules.required]"
                ></v-text-field>
              </v-card-text>
            </div>
            <div class="mx-4 mt-2" v-show="isDelete === true">
              <v-card-text class="text--primary text-left mx-3">
                <h3 class="my-2 grey--text" v-show="member.type === 'admin'">[관리자]</h3>
                <div><b>아이디:</b> {{ member.id }}</div>
                <v-text-field class="pe-6" label="비밀번호" type="password" v-model="member.pwd"></v-text-field>
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
        </div>
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
      now_pwd: "",
      new_pwd: "",
      new_pwd_check: "",
      isDelete: false,
      isPasswordChecked: false,
      rules: {
        required: (value) => !!value || "Required.",
        new: {
          required: (value) => !!value || "Required.",
          same: (value) => !!value == this.new_pwd || "비밀번호가 일치하지 않습니다.",
        },
      },
      nowPasswordErrors: [],
    };
  },

  created() {
    this.SET_MODIFY_MODE(false);
    this.member = { ...this.memberInfo };
  },

  computed: {
    ...mapState(memberStore, ["isModify", "memberInfo", "isCorrectPassword"]),
  },

  watch: {
    now_pwd(value) {
      if (value.length != 0)
        this.checkPassword(value).then(() => {
          this.nowPasswordErrors = this.isCorrectPassword ? [] : ["비밀번호가 맞지 않습니다."];
        });
    },
  },

  methods: {
    ...mapMutations(memberStore, ["SET_MODIFY_MODE"]),
    ...mapActions(memberStore, ["getMemberInfo", "deleteMember", "checkPassword"]),

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

    modifyMember() {
      console.log("modify Member");
    },

    async doDeleteMember() {
      console.log("delete Member");
      await this.deleteMember(this.member);
      this.$router.push({ name: "main" });
    },

    async setIsPasswordChecked() {
      await this.checkPassword(this.now_pwd);
      console.log(this.isCorrectPassword);

      this.isPasswordChecked = this.isCorrectPassword;
      this.now_pwd = "";
    },

    async checkNowPassword(now_pwd) {
      await this.checkPassword(now_pwd);
    },
  },
};
</script>

<style></style>

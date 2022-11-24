<template>
  <v-main>
    <v-layout row wrap>
      <v-flex xs12 class="text-xs-center" mt-5>
        <h1>회원 정보</h1>
      </v-flex>
      <v-flex xs12 sm6 offset-sm3 mt-3>
        <div v-if="!isPasswordChecked">
          <v-card class="mx-auto" max-width="35rem" ref="form">
            <v-form ref="entryForm" @submit.prevent="save">
              <v-card-text>
                개인 정보 조회를 위해서는 인증이 필요합니다.<br />
                비밀번호 입력 후 확인 버튼을 클릭해 주세요
                <v-text-field
                  class="mt-6"
                  color="#6768ab"
                  v-model="password"
                  type="password"
                  label="비밀번호"
                  outlined
                  required
                  :rules="rules"
                  :error-messages="errorMessages"
                ></v-text-field>
              </v-card-text>
              <v-card-actions>
                <v-spacer></v-spacer>
                <v-btn class="mb-3" color="#6768ab" dark type="submit">확인</v-btn>
                <v-spacer></v-spacer>
              </v-card-actions>
            </v-form>
          </v-card>
        </div>
        <div v-else>
          <member-info-item v-show="this.showMode === 'info'"></member-info-item>
          <member-info-modify v-show="this.showMode === 'modify'"></member-info-modify>
          <member-info-delete v-show="this.showMode === 'delete'"></member-info-delete>
        </div>
      </v-flex>
    </v-layout>
  </v-main>
</template>

<script>
import { mapActions, mapState } from "vuex";
import MemberInfoModify from "@/components/member/MemberInfoModify.vue";
import MemberInfoItem from "@/components/member/MemberInfoItem.vue";
import MemberInfoDelete from "@/components/member/MemberInfoDelete.vue";

const memberStore = "memberStore";

export default {
  name: "MemberInfo",

  components: { MemberInfoModify, MemberInfoItem, MemberInfoDelete },

  data() {
    return {
      password: "",
      rules: [(v) => !!v || "비밀번호를 입력해주세요."],
      errorMessages: "",
      isPasswordChecked: false,
    };
  },

  computed: {
    ...mapState(memberStore, ["isCorrectPassword", "showMode"]),
  },

  methods: {
    ...mapActions(memberStore, ["checkPassword"]),

    async save() {
      if (this.$refs.entryForm.validate()) {
        console.log("password", this.password);
        await this.checkPassword(this.password);

        if (!this.isCorrectPassword) {
          this.errorMessages = "비밀번호가 틀렸습니다.";
        }
        this.isPasswordChecked = this.isCorrectPassword;
      }
    },
  },
};
</script>

<style></style>

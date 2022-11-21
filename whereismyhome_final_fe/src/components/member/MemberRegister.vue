<template>
  <v-main>
    <v-layout row wrap>
      <v-flex xs12 class="text-xs-center" mt-5>
        <h1>회원 가입</h1>
      </v-flex>
      <v-flex xs12 sm6 offset-sm3 mt-3>
        <v-form ref="form" @submit="onSubmit">
          <v-layout column>
            <v-flex>
              <!-- required 적용 안 됨. -->
              <v-text-field v-model="member.name" label="이름" id="memberName" type="text" required></v-text-field>
            </v-flex>
            <v-flex>
              <!-- required 적용 안 됨. -->
              <v-text-field v-model="member.id" label="아이디" id="memberId" required></v-text-field>
            </v-flex>
            <v-flex>
              <!-- required 적용 안 됨. -->
              <v-text-field
                v-model="member.pwd"
                label="비밀번호"
                id="memberPassword"
                type="password"
                required
              ></v-text-field>
            </v-flex>
            <v-flex class="text-xs-center" mt-5>
              <v-btn color="primary" type="submit" class="mx-2"> 가입하기 </v-btn>
              <v-btn type="button" @click="mvLogIn" class="mx-2"> 로그인 </v-btn>
            </v-flex>
          </v-layout>
        </v-form>
      </v-flex>
    </v-layout>
  </v-main>
</template>

<script>
import { mapActions } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberRegister",

  data() {
    return {
      member: { name: "", id: "", pwd: "" },
    };
  },

  methods: {
    ...mapActions(memberStore, ["memberRegist"]),

    async onSubmit(event) {
      event.preventDefault();
      console.log("회원가입");
      await this.memberRegist(this.member);
      this.$router.push({ name: "main" });
    },

    mvLogIn() {
      this.$router.push({ name: "login" });
    },
  },
};
</script>

<style></style>

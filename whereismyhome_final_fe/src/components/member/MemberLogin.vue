<template>
  <v-main>
    <v-layout row wrap>
      <v-flex xs12 class="text-xs-center" mt-5>
        <h1>로그인</h1>
      </v-flex>
      <v-flex xs12 sm6 offset-sm3 mt-3>
        <v-form @submit="onSubmit">
          <v-layout column>
            <v-flex>
              <!-- required 적용 안 됨. -->
              <v-text-field
                v-model="member.id"
                label="아이디"
                id="memberId"
                required
              ></v-text-field>
            </v-flex>
            <v-flex>
              <!-- required 적용 안 됨. -->
              <v-text-field
                v-model="member.password"
                label="비밀번호"
                id="memberPassword"
                type="password"
                required
              ></v-text-field>
            </v-flex>
            <v-flex class="text-xs-center" mt-5>
              <v-btn color="primary" type="submit" class="mx-2"> 로그인 </v-btn>
              <v-btn color="primary-outline" type="button" @click="mvRegist" class="mx-2">
                회원가입
              </v-btn>
            </v-flex>
          </v-layout>
        </v-form>
      </v-flex>
    </v-layout>
  </v-main>
</template>

<script>
// import { mapState, mapActions } from "vuex";

// const memberStore = "memberStore";

export default {
  name: "UserLogin",

  data() {
    return {
      member: {
        id: "",
        password: "",
      },
    };
  },

  computed: {
    // ...mapState(memberStore, []),
  },

  methods: {
    // ...mapActions(memberStore, []),
    onSubmit(event) {
      event.preventDefault();
      console.log("Log in");
      console.log(this.member);
    },
    mvRegist() {
      console.log("Regist");
      this.$router.push({ name: "register" });
    },
  },
};
</script>

<style></style>

<template>
  <v-main>
    <v-layout row wrap>
      <v-flex xs12 class="text-xs-center" mt-5>
        <h1>회원 정보</h1>
      </v-flex>
      <v-flex xs12 sm6 offset-sm3 mt-3>
        <v-layout column v-show="!isModify">
          <v-flex mt-3>
            <v-row>
              <v-col cols="2"></v-col>
              <v-col cols="2" align-self="end">이름</v-col>
              <v-col cols="4" align-self="start" v-if="this.member.admin"
                >{{ member.name }} (관리자)</v-col
              >
              <v-col cols="4" align-self="start" v-else>{{ member.name }}</v-col>
              <v-col cols="2"></v-col>
            </v-row>
          </v-flex>
          <hr class="my-4" />
          <v-flex>
            <v-row>
              <v-col cols="2"></v-col>
              <v-col cols="2" align-self="end">아이디</v-col>
              <v-col cols="4" align-self="start">{{ member.id }}</v-col>
              <v-col cols="2"></v-col>
            </v-row>
          </v-flex>
          <hr class="my-4" />
          <v-flex mt-3>
            <v-row>
              <v-col cols="2"></v-col>
              <v-col cols="2" align-self="end">비밀번호</v-col>
              <v-col cols="4" align-self="start">{{ member.password }}</v-col>
              <v-col cols="2"></v-col>
            </v-row>
          </v-flex>
          <hr class="my-4" />
          <v-flex mt-5 v-show="!isModify">
            <v-btn color="primary" class="mx-2" @click="openModify">수정</v-btn>
            <v-btn color="error" class="mx-2">회원 탈퇴</v-btn>
          </v-flex>
        </v-layout>
        <v-layout column v-show="isModify">
          <v-flex mt-3>
            <v-row>
              <v-col cols="2"></v-col>
              <v-col cols="2" align-self="center">이름</v-col>
              <v-col cols="4" align-self="start" v-if="this.member.admin">
                <v-text-field
                  v-model="member.name"
                  id="memberName"
                  type="text"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="4" align-self="start" v-else>{{ member.name }}</v-col>
              <v-col cols="2"></v-col>
            </v-row>
          </v-flex>
          <v-flex>
            <v-row>
              <v-col cols="2"></v-col>
              <v-col cols="2" align-self="center">아이디</v-col>
              <v-col cols="4" align-self="start">
                <v-text-field v-model="member.id" id="memberId" type="text" required>
                </v-text-field>
              </v-col>
              <v-col cols="2"></v-col>
            </v-row>
          </v-flex>
          <v-flex mt-3>
            <v-row>
              <v-col cols="2"></v-col>
              <v-col cols="2" align-self="center">비밀번호</v-col>
              <v-col cols="4" align-self="start">
                <v-text-field
                  v-model="member.password"
                  id="memberPassword"
                  type="text"
                  required
                ></v-text-field>
              </v-col>
              <v-col cols="2"></v-col>
            </v-row>
          </v-flex>
          <v-flex mt-5 v-show="isModify">
            <v-btn color="primary" class="mx-2">수정</v-btn>
            <v-btn class="mx-2" @click="closeModify">취소</v-btn>
          </v-flex>
        </v-layout>
      </v-flex>
    </v-layout>
  </v-main>
</template>

<script>
export default {
  name: "MemberInfo",

  data() {
    return {
      isModify: false,
      //   member: { name: "ssafy", id: "ssafy", password: "1234", admin: false },
      member: { name: "admin", id: "admin", password: "1234", admin: true },
      memberOrigin: { ...this.member },
    };
  },

  methods: {
    openModify() {
      this.isModify = true;
      this.memberOrigin = { ...this.member };
    },

    closeModify() {
      this.isModify = false;
      this.member = { ...this.memberOrigin };
    },
  },
};
</script>

<style></style>

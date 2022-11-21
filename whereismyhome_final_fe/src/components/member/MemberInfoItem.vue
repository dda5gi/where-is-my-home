<template>
  <v-layout column>
    정보
    <!-- <v-flex mt-3>
      <v-row>
        <v-col cols="2"></v-col>
        <v-col cols="2" align-self="end">이름</v-col>
        <v-col cols="4" align-self="start" v-if="this.member.admin">{{ member.name }} (관리자)</v-col>
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
    </v-flex> -->
  </v-layout>
</template>

<script>
export default {
  name: "MemberInfoItem",
};
</script>

<style></style>

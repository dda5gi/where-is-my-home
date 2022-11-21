<template>
  <v-layout column>
    수정
    <!-- <v-flex mt-3>
      <v-row>
        <v-col cols="2"></v-col>
        <v-col cols="2" align-self="center">이름{{ memberInfo.id }}</v-col>
        <v-col cols="4" align-self="start" v-if="this.member.admin">
          <v-text-field v-model="member.name" id="memberName" type="text" required></v-text-field>
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
          <v-text-field v-model="member.id" id="memberId" type="text" required> </v-text-field>
        </v-col>
        <v-col cols="2"></v-col>
      </v-row>
    </v-flex>
    <v-flex mt-3>
      <v-row>
        <v-col cols="2"></v-col>
        <v-col cols="2" align-self="center">비밀번호</v-col>
        <v-col cols="4" align-self="start">
          <v-text-field v-model="member.password" id="memberPassword" type="text" required></v-text-field>
        </v-col>
        <v-col cols="2"></v-col>
      </v-row>
    </v-flex>
    <v-flex mt-5>
      <v-btn class="mx-2" @click="closeModify()">취소</v-btn>
    </v-flex> -->
  </v-layout>
  <!-- <v-btn color="primary" class="mx-2" @click="memberModify()">수정</v-btn> -->
</template>

<script>
export default {
  name: "MemberInfoModify",
};
</script>

<style></style>

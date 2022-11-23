<template>
  <div>
    <v-card min-height="500">
      <v-card-title>{{ this.question.title }}</v-card-title>
      <v-card-text class="text-end">
        <div class="d-flex float-right">
          <div class="mx-3">작성자 : {{ this.question.memberId }}</div>
          <div>조회수 : {{ this.question.hit }}</div>
        </div>
        <br />
        작성시각 : {{ this.question.registerTime }}
      </v-card-text>
      <v-divider></v-divider>
      <v-card-text class="text-left" v-html="message"></v-card-text>
    </v-card>
    <div class="float-right mt-5">
      <v-btn
        class="mx-4"
        color="#6667AB"
        dark
        v-show="this.isLogin"
        @click.prevent="moveWriteAnswer"
        >답변 작성</v-btn
      >
      <v-btn
        class="me-4"
        color="#6667AB"
        dark
        v-show="this.isLogin && this.memberInfo.id === this.question.memberId"
        @click.prevent="showQnaQuestionModify"
        >글 수정</v-btn
      >
      <v-btn
        class="me-4"
        color="#686369"
        dark
        v-show="this.isLogin && this.memberInfo.id === this.question.memberId"
        @click.prevent="qnaQuestionDelete"
        >글 삭제</v-btn
      >
      <v-btn color="#dbd6d2" @click.prevent="moveQnaList">목록으로</v-btn>
    </div>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";

const memberStore = "memberStore";
const qnaStore = "qnaStore";

export default {
  name: "QnaViewQuestion",

  computed: {
    ...mapState(memberStore, ["isLogin", "memberInfo"]),
    ...mapState(qnaStore, ["question"]),

    message() {
      if (this.question.content) return this.question.content.split("\n").join("<br>");
      return "";
    },
  },

  methods: {
    ...mapMutations(qnaStore, ["SET_IS_MODIFY"]),
    ...mapActions(qnaStore, ["getArticleQuestion", "deleteArticleQuestion"]),

    moveWriteAnswer() {
      this.$router.push({ name: "qnawriteanswer" });
    },

    moveQnaList() {
      this.$router.push({ name: "qnalist" });
    },

    showQnaQuestionModify() {
      this.SET_IS_MODIFY(true);
    },

    async qnaQuestionDelete() {
      await this.deleteArticleQuestion();
      this.$router.push({ name: "qnalist" });
    },
  },
};
</script>

<style></style>

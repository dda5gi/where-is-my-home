<template>
  <div>
    <v-card min-height="500">
      <v-card-title>{{ this.answer.title }}</v-card-title>
      <v-card-text class="text-end">
        <div class="d-flex float-right">
          <div class="mx-3">작성자 : {{ this.answer.memberId }}</div>
          <div>조회수 : {{ this.answer.hit }}</div>
        </div>
        <br />
        작성시각 : {{ this.answer.registerTime }}
      </v-card-text>
      <v-divider></v-divider>
      <v-card-text class="text-left" v-html="message"></v-card-text>
    </v-card>
    <div class="float-right mt-5">
      <v-btn
        class="mx-4"
        color="#6667AB"
        dark
        v-show="this.isLogin && this.memberInfo.id === this.answer.memberId"
        @click.prevent="showQnaAnswerModify"
        >글 수정</v-btn
      >
      <v-btn
        class="me-4"
        color="#686369"
        dark
        v-show="this.isLogin && this.memberInfo.id === this.answer.memberId"
        @click.prevent="qnaAnswerDelete"
        >글 삭제</v-btn
      >
      <v-btn color="#dbd6d2" @click.prevent="moveQnaList">목록으로</v-btn>
    </div>
  </div>
</template>

<script>
import { mapActions, mapState, mapMutations } from "vuex";

const memberStore = "memberStore";
const qnaStore = "qnaStore";

export default {
  name: "QnaViewAnswer",

  computed: {
    ...mapState(memberStore, ["isLogin", "memberInfo"]),
    ...mapState(qnaStore, ["answer"]),

    message() {
      if (this.answer.content) return this.answer.content.split("\n").join("<br>");
      return "";
    },
  },

  methods: {
    ...mapMutations(qnaStore, ["SET_IS_MODIFY"]),
    ...mapActions(qnaStore, ["getArticleAnswer", "deleteArticleAnswer"]),

    moveQnaList() {
      this.$router.push({ name: "qnalist" });
    },

    showQnaAnswerModify() {
      this.SET_IS_MODIFY(true);
    },

    async qnaAnswerDelete() {
      await this.deleteArticleAnswer();
      this.$router.push({ name: "qnalist" });
    },
  },
};
</script>

<style scoped></style>

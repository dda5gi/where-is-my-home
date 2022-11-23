<template>
  <div>
    <v-card min-height="500">
      <v-text-field
        class="mx-4 pt-5"
        v-model="article.title"
        placeholder="제목"
        rows="1"
        row-height="10"
        outlined
      ></v-text-field>
      <v-textarea
        class="mx-4"
        v-model="article.content"
        placeholder="내용"
        rows="4"
        row-height="30"
        outlined
        auto-grow
      ></v-textarea>
    </v-card>
    <div class="float-right mt-5">
      <v-btn class="mx-4" color="#6667AB" dark @click.prevent="qnaAnswerModify">수정</v-btn>
      <v-btn color="#dbd6d2" @click.prevent="closeQnaAnswerModify">취소</v-btn>
    </div>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";

const qnaStore = "qnaStore";

export default {
  name: "QnaViewAnswerModify",

  data() {
    return {
      article: {},
    };
  },

  computed: {
    ...mapState(qnaStore, ["answer"]),
  },

  created() {
    this.article = { ...this.answer };
  },

  methods: {
    ...mapMutations(qnaStore, ["SET_IS_MODIFY"]),
    ...mapActions(qnaStore, ["modifyArticleAnswer", "getArticleAnswer"]),

    qnaAnswerModify() {
      console.log(this.article);
      this.modifyArticleAnswer(this.article);
      this.getArticleAnswer(this.answer.articleNo);
      this.SET_IS_MODIFY(false);
    },

    closeQnaAnswerModify() {
      this.article = { ...this.answer };
      this.SET_IS_MODIFY(false);
    },
  },
};
</script>

<style></style>

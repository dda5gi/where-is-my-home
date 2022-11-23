<template>
  <v-main>
    <h1 class="mb-5">QnA 목록</h1>
    <v-layout class="mt-5 align-center">
      <v-btn class="mx-1" color="#dbd6d2" @click.prevent="showWrite" v-show="memberInfo != null">글쓰기</v-btn>
      <v-spacer></v-spacer>
      <v-text-field
        id="searchBar"
        class="mx-4"
        v-model="search"
        append-icon="search"
        label="Search"
        single-line
      ></v-text-field>
    </v-layout>
    <v-data-table
      :headers="headers"
      :items="this.list"
      :search="search"
      :items-per-page="noticePerPage"
      class="elevation-1"
      @click:row="viewArticle"
    ></v-data-table>
  </v-main>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";

const memberStore = "memberStore";
const qnaStore = "qnaStore";

export default {
  name: "QnaList",

  data() {
    return {
      headers: [
        { text: "글번호", align: "center", value: "no", width: "12.5%" },
        {
          text: "제목",
          align: "start",
          sortable: false,
          value: "title",
        },
        { text: "조회수", align: "center", value: "hit", width: "12.5%" },
      ],
      search: "",
      noticePerPage: 5,
    };
  },

  async created() {
    await this.getListQna();
  },

  computed: {
    ...mapState(memberStore, ["memberInfo"]),
    ...mapState(qnaStore, ["list", "articleType", "question", "answer"]),
  },

  methods: {
    ...mapMutations(qnaStore, ["SET_ARTICLE_TYPE", "SET_QUESTION_NUMBER", "SET_ANSWER_NUMBER"]),
    ...mapActions(qnaStore, ["getListQna", "getArticleQuestion", "getArticleAnswer"]),

    viewArticle(element) {
      // console.log(element);
      this.SET_ARTICLE_TYPE(element.type);
      if (this.articleType === "question") {
        this.SET_QUESTION_NUMBER(element.articleNo);
        this.getArticleQuestion(this.question.articleNo);
      } else {
        this.SET_ANSWER_NUMBER(element.articleNo);
        this.getArticleAnswer(this.answer.articleNo);
      }
      this.$router.push({ name: "qnaview" });
    },
  },
};
</script>

<style></style>

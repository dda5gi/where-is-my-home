<template>
  <v-main>
    <h1 class="mb-4">QnA 작성 페이지</h1>
    <v-card>
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
      <v-layout class="float-right mt-5 mx-2">
        <v-btn class="mx-4" color="#6667AB" dark @click.prevent="write">글 작성</v-btn>
        <v-btn color="#686368" dark @click.prevent="reset">초기화</v-btn>
      </v-layout>
    </v-card>
  </v-main>
</template>

<script>
import { mapState } from "vuex";
import { writeQuestion } from "@/api/qna";

const memberStore = "memberStore";
const qnaStore = "qnaStore";

export default {
  name: "QnaWriteQuestion",

  data() {
    return {
      articleType: "",
      article: { title: "", content: "", memberId: "" },
    };
  },

  created() {
    this.article.memberId = this.memberInfo.id;
  },

  computed: {
    ...mapState(memberStore, ["memberInfo"]),
    ...mapState(qnaStore, ["questions"]),
  },

  methods: {
    async write() {
      // console.log(this.article);
      await writeQuestion(
        this.article,
        ({ data }) => {
          if (data.msg === "success") {
            alert("글이 작성되었습니다.");
          }
        },
        (error) => {
          console.log(error);
          alert("글이 작성되지 않았습니다.");
        }
      );
      this.$router.push({ name: "qnalist" });
    },

    reset() {
      this.article.title = "";
      this.article.content = "";
    },
  },
};
</script>

<style scoped></style>

<template>
  <v-main>
    <h1 class="mb-4">QnA 작성 페이지</h1>
    <v-card>
      <v-text-field class="mx-4 pt-5" placeholder="제목" rows="1" row-height="10" outlined></v-text-field>
      <v-textarea placeholder="내용" rows="4" row-height="30" outlined auto-grow></v-textarea>
      <v-layout class="float-right mt-5">
        <v-btn class="mx-4" color="#6667AB" dark @click.prevent="write">글 작성</v-btn>
        <v-btn color="#f1f0ec" @click.prevent="moveNoticeList">목록으로</v-btn>
      </v-layout>
    </v-card>
  </v-main>
</template>

<script>
import { mapState } from "vuex";

const qnaStore = "qnaStore";

export default {
  name: "QnaWriteQuestion",

  data() {
    return {
      articleType: "",
    };
  },

  computed: {
    ...mapState(qnaStore, ["questions"]),
  },
};
</script>

<style scoped></style>

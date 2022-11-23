<template>
  <v-main>
    <h1 class="mb-4">공지사항 작성 페이지</h1>
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
    </v-card>
    <v-layout class="float-right mt-5">
      <v-btn class="mx-4" color="#6667AB" dark @click.prevent="write">글 작성</v-btn>
      <v-btn color="#f1f0ec" @click.prevent="moveNoticeList">목록으로</v-btn>
    </v-layout>
  </v-main>
</template>

<script>
import { writeNotice } from "@/api/notice";

export default {
  name: "NoticeWrite",

  data() {
    return {
      article: { title: "", content: "" },
    };
  },

  methods: {
    write() {
      writeNotice(
        this.article,
        ({ data }) => {
          if (data.msg === "success") {
            alert("글 작성에 성공했습니다.");
          } else {
            alert("글 작성에 실패했습니다.");
          }
          this.$router.push({ name: "noticelist", params: { pageNo: 1 } });
        },
        (error) => {
          console.log(error);
        }
      );
    },

    moveNoticeList() {
      this.$router.push({ name: "noticelist" });
    },
  },
};
</script>

<style></style>

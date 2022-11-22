<template>
  <v-main>
    <v-card min-height="500">
      <v-card-title class="mx-3">[공지사항] {{ notice.title }}</v-card-title>
      <v-card-text class="text-end">
        조회수 : {{ notice.hit }}
        <br />
        작성시각 : {{ notice.registerTime }}
      </v-card-text>
      <v-divider></v-divider>
      <v-card-text class="text-start mx-3 my-2">
        <div v-html="message"></div>
      </v-card-text>
    </v-card>
  </v-main>
</template>

<script>
import { getNotice } from "@/api/notice";

export default {
  name: "NoticeView",

  data() {
    return {
      articleNo: this.$route.params.articleNo,
      notice: {},
    };
  },

  created() {
    getNotice(
      this.articleNo,
      ({ data }) => {
        if (data.msg === "success") {
          this.notice = data.payload.article;
          console.log(data.payload);
        } else {
          console.log("글을 읽어올 수 없습니다.");
        }
      },
      (error) => {
        console.log(error);
      }
    );
  },

  computed: {
    message() {
      if (this.notice.content) return this.notice.content.split("\n").join("<br>");
      return "";
    },
  },
};
</script>

<style></style>

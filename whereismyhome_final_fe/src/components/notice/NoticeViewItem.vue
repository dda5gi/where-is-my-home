<template>
  <div>
    <v-card min-height="500">
      <v-card-title class="mx-3">[공지사항] {{ this.notice.title }}</v-card-title>
      <v-card-text class="text-end">
        조회수 : {{ this.notice.hit }}
        <br />
        작성시각 : {{ this.notice.registerTime }}
      </v-card-text>
      <v-divider></v-divider>
      <v-card-text class="text-start mx-3 my-2">
        <div v-html="message"></div>
      </v-card-text>
    </v-card>
    <v-layout class="float-end mt-3 mx-2">
      <v-btn class="mx-4" color="#6667AB" dark @click.prevent="showModifyNotice">수정</v-btn>
      <v-btn class="me-4" color="#686369" dark>삭제</v-btn>
      <v-btn color="#f1f0ec" @click.prevent="moveNoticeList">목록으로</v-btn>
    </v-layout>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";

const noticeStore = "noticeStore";

export default {
  name: "NoticeViewItem",

  computed: {
    ...mapState(noticeStore, ["notice"]),

    message() {
      if (this.notice.content) return this.notice.content.split("\n").join("<br>");
      return "";
    },
  },

  methods: {
    ...mapMutations(noticeStore, ["SET_IS_MODIFY"]),

    showModifyNotice() {
      this.SET_IS_MODIFY(true);
    },

    moveNoticeList() {
      this.$router.push({ name: "noticelist" });
    },
  },
};
</script>

<style scoped></style>

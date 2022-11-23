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
      <v-btn
        class="mx-4"
        color="#6667AB"
        dark
        @click.prevent="showModifyNotice"
        v-show="this.isLogin && this.memberInfo.type === 'admin'"
        >수정
      </v-btn>
      <v-btn
        class="me-4"
        color="#686369"
        dark
        @click.prevent="deleteNotice"
        v-show="this.isLogin && this.memberInfo.type === 'admin'"
        >삭제
      </v-btn>
      <v-btn color="#f1f0ec" @click.prevent="moveNoticeList">목록으로</v-btn>
    </v-layout>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";

const memberStore = "memberStore";
const noticeStore = "noticeStore";

export default {
  name: "NoticeViewItem",

  computed: {
    ...mapState(memberStore, ["isLogin", "memberInfo"]),
    ...mapState(noticeStore, ["notice", "page"]),

    message() {
      if (this.notice.content) return this.notice.content.split("\n").join("<br>");
      return "";
    },
  },

  methods: {
    ...mapMutations(noticeStore, ["SET_IS_MODIFY"]),
    ...mapActions(noticeStore, ["deleteArticle"]),

    showModifyNotice() {
      this.SET_IS_MODIFY(true);
    },

    moveNoticeList() {
      this.$router.push({ name: "noticelist", params: { pageNo: this.page } });
    },

    async deleteNotice() {
      await this.deleteArticle();
      this.$router.push({ name: "noticelist", params: { pageNo: this.page } });
    },
  },
};
</script>

<style scoped></style>

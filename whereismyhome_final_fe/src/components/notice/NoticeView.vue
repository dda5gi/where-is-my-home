<template>
  <v-main>
    <notice-view-item v-show="!isModify"></notice-view-item>
    <notice-view-modify v-show="isModify"></notice-view-modify>
  </v-main>
</template>

<script>
import NoticeViewItem from "@/components/notice/NoticeViewItem";
import NoticeViewModify from "@/components/notice/NoticeViewModify";
import { mapState } from "vuex";

const noticeStore = "noticeStore";

export default {
  name: "NoticeView",

  components: { NoticeViewItem, NoticeViewModify },

  data() {
    return {};
  },

  computed: {
    ...mapState(noticeStore, ["isModify"]),
  },
};
</script>

<style></style>

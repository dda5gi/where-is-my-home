<template>
  <div>
    <v-card min-height="500">
      <v-text-field
        class="mx-4 pt-5"
        color="#6667AB"
        v-model="article.title"
        placeholder="제목"
        rows="1"
        row-height="10"
        outlined
      ></v-text-field>
      <v-textarea
        class="mx-4"
        color="#6667AB"
        v-model="article.content"
        placeholder="내용"
        rows="4"
        row-height="30"
        outlined
        auto-grow
      ></v-textarea>
    </v-card>
    <div class="float-right mt-5">
      <v-btn class="mx-4" color="#6667AB" dark @click.prevent="doModify">수정</v-btn>
      <v-btn color="#dbd6d2" @click.prevent="closeModify">취소</v-btn>
    </div>
  </div>
</template>

<script>
import { mapActions, mapMutations, mapState } from "vuex";

const noticeStore = "noticeStore";

export default {
  name: "NoticeViewModify",

  data() {
    return {
      article: { articleNo: "", title: "", content: "" },
    };
  },

  computed: {
    ...mapState(noticeStore, ["notice", "noticeNo"]),
  },

  created() {
    this.article = { ...this.notice };
  },

  methods: {
    ...mapMutations(noticeStore, ["SET_IS_MODIFY"]),
    ...mapActions(noticeStore, ["modifyArticle", "getArticle"]),

    async doModify() {
      await this.modifyArticle(this.article);
      await this.getArticle(this.noticeNo);
      this.SET_IS_MODIFY(false);
    },

    closeModify() {
      this.article = { ...this.notice };
      this.SET_IS_MODIFY(false);
    },
  },
};
</script>

<style scoped></style>

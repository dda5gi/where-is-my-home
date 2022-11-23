<template>
  <div>
    <v-container>
      <qna-view-question v-if="!this.isModify && this.articleType === 'question'"></qna-view-question>
      <qna-view-answer v-if="!this.isModify && this.articleType === 'answer'"></qna-view-answer>
      <qna-view-question-modify v-if="this.isModify && this.articleType === 'question'"></qna-view-question-modify>
      <qna-view-answer-modify v-if="this.isModify && this.articleType === 'answer'"></qna-view-answer-modify>
    </v-container>
  </div>
</template>

<script>
import QnaViewAnswer from "@/components/qna/QnaViewAnswer";
import QnaViewQuestion from "@/components/qna/QnaViewQuestion";
import QnaViewQuestionModify from "@/components/qna/QnaViewQuestionModify";
import QnaViewAnswerModify from "@/components/qna/QnaViewAnswerModify";
import { mapState } from "vuex";

const qnaStore = "qnaStore";

export default {
  name: "QnaView",

  components: { QnaViewQuestion, QnaViewAnswer, QnaViewQuestionModify, QnaViewAnswerModify },

  data() {
    return {};
  },

  computed: {
    ...mapState(qnaStore, ["articleType", "isModify"]),
  },
};
</script>

<style></style>

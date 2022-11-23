import {
  listQna,
  getQuestion,
  getAnswer,
  deleteQuestion,
  modifyQuestion,
  deleteAnswer,
  modifyAnswer,
} from "@/api/qna";

const qnaStore = {
  namespaced: true,
  state: {
    list: [],
    articleType: "",
    question: {},
    answer: {},
    isModify: false,
  },
  getters: {},
  mutations: {
    INIT_LIST: (state) => {
      state.list = [];
    },
    ADD_LIST_QUESTION: (state, item) => {
      item.no = state.list.length + 1;
      item.type = "question";
      state.list.push(item);
    },
    ADD_LIST_ANSWER: (state, list) => {
      list.forEach((answer, index) => {
        answer.no = state.list.length + index + 1;
        answer.title = "[답변] " + answer.title;
        answer.type = "answer";
      });
      state.list = state.list.concat(list);
    },
    SET_ARTICLE_TYPE: (state, type) => {
      state.articleType = type;
    },
    SET_QUESTION_NUMBER: (state, articleNo) => {
      state.question.articleNo = articleNo;
    },
    INIT_QUESTION: (state) => {
      state.question = {};
    },
    SET_QUESTION: (state, question) => {
      state.question = { ...state.question, ...question };
    },
    SET_ANSWER_NUMBER: (state, articleNo) => {
      state.answer.articleNo = articleNo;
    },
    INIT_ANSWER: (state) => {
      state.answer = {};
    },
    SET_ANSWER: (state, answer) => {
      state.answer = { ...state.answer, ...answer };
    },
    SET_IS_MODIFY: (state, flag) => {
      state.isModify = flag;
    },
  },

  actions: {
    async getListQna({ commit }) {
      commit("INIT_LIST");
      await listQna(
        ({ data }) => {
          if (data.msg === "success") {
            data.payload.list.forEach((element) => {
              commit("ADD_LIST_QUESTION", element.question);
              commit("ADD_LIST_ANSWER", element.answers);
            });
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    async getArticleQuestion({ commit }, articleNo) {
      await getQuestion(
        articleNo,
        ({ data }) => {
          if (data.msg === "success") {
            commit("SET_QUESTION", data.payload.article);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    async getArticleAnswer({ commit }, articleNo) {
      await getAnswer(
        articleNo,
        ({ data }) => {
          if (data.msg === "success") {
            commit("SET_ANSWER", data.payload.article);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    async deleteArticleQuestion({ state, commit }) {
      await deleteQuestion(
        state.question.articleNo,
        ({ data }) => {
          if (data.msg === "success") {
            alert("글이 삭제되었습니다.");
            commit("SET_ARTICLE_TYPE", "");
            commit("SET_QUESTION_NUMBER", 0);
            commit("INIT_QUESTION");
          }
        },
        (error) => {
          console.log(error);
          alert("글이 삭제되지 않았습니다.");
        }
      );
    },

    async modifyArticleQuestion({ state, commit }, article) {
      await modifyQuestion(
        article,
        ({ data }) => {
          if (data.msg === "success") {
            const questionArticleNo = state.question.articleNo;
            commit("INIT_QUESTION");
            commit("SET_QUESTION_NUMBER", questionArticleNo);
            alert("글이 수정되었습니다.");
          }
        },
        (error) => {
          console.log(error);
          alert("글이 수정되지 않았습니다.");
        }
      );
    },

    async deleteArticleAnswer({ state, commit }) {
      await deleteAnswer(
        state.answer.articleNo,
        ({ data }) => {
          if (data.msg === "success") {
            alert("글이 삭제되었습니다.");
            commit("SET_ARTICLE_TYPE", "");
            commit("SET_ANSWER_NUMBER", 0);
            commit("INIT_ANSWER");
          }
        },
        (error) => {
          console.log(error);
          alert("글이 삭제되지 않았습니다.");
        }
      );
    },

    async modifyArticleAnswer({ state, commit }, article) {
      await modifyAnswer(
        article,
        ({ data }) => {
          if (data.msg === "success") {
            const answerArticleNo = state.answer.articleNo;
            commit("INIT_ANSWER");
            commit("SET_ANSWER_NUMBER", answerArticleNo);
            alert("글이 수정되었습니다.");
          }
        },
        (error) => {
          console.log(error);
          alert("글이 수정되지 않았습니다.");
        }
      );
      console.log("modifyArticleAnswer1");
    },
  },
};

export default qnaStore;

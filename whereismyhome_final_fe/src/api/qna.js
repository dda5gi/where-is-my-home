import { apiInstance } from "./index";

const api = apiInstance();

async function listQna(success, fail) {
  await api.get(`/qna/list`).then(success).catch(fail);
}

async function getQuestion(articleNo, success, fail) {
  await api.get(`/qna/question/${articleNo}`).then(success).catch(fail);
}

async function getAnswer(articleNo, success, fail) {
  await api.get(`/qna/answer/${articleNo}`).then(success).catch(fail);
}

async function deleteQuestion(articleNo, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.post(`/qna/question/${articleNo}`).then(success).catch(fail);
}

async function modifyQuestion(article, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.put(`/qna/question`, article).then(success).catch(fail);
}

async function deleteAnswer(articleNo, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.post(`/qna/answer/${articleNo}`).then(success).catch(fail);
}

async function modifyAnswer(article, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.put(`/qna/answer`, article).then(success).catch(fail);
}

async function writeQuestion(article, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.post(`/qna/question`, article).then(success).catch(fail);
}

export {
  listQna,
  getQuestion,
  getAnswer,
  deleteQuestion,
  modifyQuestion,
  deleteAnswer,
  modifyAnswer,
  writeQuestion,
};

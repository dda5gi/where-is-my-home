import { apiInstance } from "./index.js";

const api = apiInstance();

function listNotice(success, fail) {
  api.get(`/notice/list`).then(success).catch(fail);
}

function writeNotice(article, success, fail) {
  api.post(`/notice`, JSON.stringify(article)).then(success).catch(fail);
}

function getNotice(articleno, success, fail) {
  api.get(`/notice/${articleno}`).then(success).catch(fail);
}

function modifyNotice(article, success, fail) {
  api.put(`/notice`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteNotice(articleNo, success, fail) {
  api.delete(`/notice/${articleNo}`).then(success).catch(fail);
}

export { listNotice, writeNotice, getNotice, modifyNotice, deleteNotice };

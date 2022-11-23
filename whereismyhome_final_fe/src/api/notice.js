import { apiInstance } from "./index.js";

const api = apiInstance();

async function listNotice(success, fail) {
  await api.get(`/notice/list`).then(success).catch(fail);
}

async function writeNotice(notice, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.post(`/notice`, JSON.stringify(notice)).then(success).catch(fail);
}

async function getNotice(articleno, success, fail) {
  await api.get(`/notice/${articleno}`).then(success).catch(fail);
}

async function modifyNotice(article, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.put(`/notice`, JSON.stringify(article)).then(success).catch(fail);
}

async function deleteNotice(articleNo, success, fail) {
  await api.delete(`/notice/${articleNo}`).then(success).catch(fail);
}

export { listNotice, writeNotice, getNotice, modifyNotice, deleteNotice };

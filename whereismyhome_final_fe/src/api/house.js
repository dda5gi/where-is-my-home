import { apiInstance } from "./index.js";

const api = apiInstance();

function sidoList(success, fail) {
  api.get(`/region/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/region/gugun`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/region/dong`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  api.get(`/apt/list`, { params: params }).then(success).catch(fail);
}

function dealList(params, success, fail) {
  api.get(`/apt/deal`, { params: params }).then(success).catch(fail);
}

function favorList(success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.get(`/region/favor/list`).then(success).catch(fail);
}

export { sidoList, gugunList, dongList, houseList, dealList, favorList };

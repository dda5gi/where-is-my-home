import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(member, success, fail) {
  await api.post(`/user/login`, JSON.stringify(member)).then(success).catch(fail);
}

async function findById(memberid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${memberid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await api.get(`/user/logout/${userid}`).then(success).catch(fail);
}

async function regist(member, success, fail) {
  await api.post(`/user/register`, member).then(success).catch(fail);
}

async function deleteMember(member, success, fail) {
  await api.post(`/user/delete`, member).then(success).catch(fail);
}

export { login, findById, tokenRegeneration, logout, regist, deleteMember };

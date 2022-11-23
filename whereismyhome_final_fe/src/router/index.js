import Vue from "vue";
import VueRouter from "vue-router";
import AppMain from "@/views/AppMain";

// import store from "@/store";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "main",
    component: AppMain,
  },
  {
    path: "/member",
    name: "member",
    component: () => import("@/views/AppMember"),
    children: [
      {
        path: "login",
        name: "login",
        component: () => import("@/components/member/MemberLogin"),
      },
      {
        path: "register",
        name: "register",
        component: () => import("@/components/member/MemberRegister"),
      },
      {
        path: "info",
        name: "info",
        component: () => import("@/components/member/MemberInfo"),
      },
    ],
  },
  {
    path: "/notice",
    name: "notice",
    redirect: "/notice/list",
    component: () => import("@/views/AppNotice"),
    children: [
      {
        path: "list",
        name: "noticelist",
        component: () => import("@/components/notice/NoticeList"),
      },
      {
        path: "view/:{articleNo}",
        name: "noticeview",
        component: () => import("@/components/notice/NoticeView"),
      },
      {
        path: "write",
        name: "noticewrite",
        component: () => import("@/components/notice/NoticeWrite"),
      },
    ],
  },
  {
    path: "/map",
    name: "map",
    // redirect: "/",
    component: () => import("@/views/AppMap"),
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

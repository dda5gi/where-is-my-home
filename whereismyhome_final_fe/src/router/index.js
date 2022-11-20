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
    path: "/user",
    name: "user",
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
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

import Vue from "vue";
import VueRouter from "vue-router";
import BoardView from "../views/BoardView.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "board",
    component: BoardView,
    redirect: "/list",

    children: [
      {
        path: "list",
        name: "boardlist",
        component: () => import("@/components/board/BoardList"),
      },
      {
        path: "detail/:boardId",
        name: "boardDetail",
        component: () => import("@/components/board/BoardDetail"),
      },
      {
        path: "write",
        name: "boardWrite",
        component: () => import("@/components/board/BoardWrite"),
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

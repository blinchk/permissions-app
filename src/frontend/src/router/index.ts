import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";
import Home from "../views/Dashboard.vue";
import PermissionsView from "../views/PermissionsView.vue";
import CreateView from "../views/CreateView.vue";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
  {
    path: "/",
    name: "Hierarchy",
    component: PermissionsView,
  },
  {
    path: "/create",
    name: "Create",
    component: CreateView,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;

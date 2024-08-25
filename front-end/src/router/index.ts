import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'
import userViewMenu from "@/utils/userViewMenu";

const routes: Array<RouteRecordRaw> = [
    {
        path: '/',
        name: 'login',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/LoginView.vue')
    }, {
        path: '/user',
        name: 'user',
        component: () => import(/* webpackChunkName: "about" */ '../views/UserView.vue'),
        children: getuserViews()
    }, {
        path: '/:pathMatch(.*)*',
        redirect: '/error/404',
        meta: {hidden: true}
    }, {
        path: '/error/:text',
        name: 'error',
        component: () => import(/* webpackChunkName: "about" */ '../views/ErrorView.vue'),
        meta: {hidden: true}
    }
]

const router = createRouter({
    //@ts-ignore
    history: createWebHistory(import.meta.env.BASE_URL),
    routes
})

function getuserViews(): RouteRecordRaw[] {
    let userViews: RouteRecordRaw[] = [];
    for (let item of userViewMenu.value) {
        userViews.push({
            path: item.route,
            name: item.name,
            component: item.component,
            meta: {index: item.id}
        })
    }
    return userViews;
}

export default router

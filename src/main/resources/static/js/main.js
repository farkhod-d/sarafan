import Vue from 'vue'
import Vuetify from 'vuetify'
import '@babel/polyfill'

import 'api/resource'
import store from 'store/store'
import router from 'router/router'

import App from 'pages/App.vue'
import {connect} from "./util/ws";
import 'vuetify/dist/vuetify.min.css'

Vue.use(Vuetify);

if (frontendData.profile !== null) {
    connect();
}
new Vue({
    el: '#app',
    store: store,
    router,
    render: a => a(App),
});

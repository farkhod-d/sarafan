import Vue from 'vue'
import Vuetify from 'vuetify'

import 'api/resource'
import 'vuetify/dist/vuetify.min.css'

import App from 'pages/App.vue'
import {connect} from "./util/ws";


Vue.use(Vuetify);

if (frontendData.profile !== null) {
    connect();
}
new Vue({
    el: '#app',
    render: a => a(App),
});

import Vue from 'vue'
import VueResource from 'vue-resource'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'

import App from 'pages/App.vue'
import {connect} from "./util/ws";


Vue.use(Vuetify);
Vue.use(VueResource);

if (frontendData.profile !== null) {
    connect();
}
new Vue({
    el: '#app',
    render: a => a(App),
});

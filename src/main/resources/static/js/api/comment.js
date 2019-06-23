import Vue from 'vue'

const comments = Vue.resource('/comments{/id}')

export default {
    add: message => comments.save({}, message),
    // update: message => comments.update({}, message),
    // remove: id => comments.remove({id}),
    // get: () => comments.get(),
}
function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }
    return -1;
}


var messagesApi = Vue.resource("/messages{/id}");

Vue.component('messages-form', {
    props: ['messages', 'messageAttr'],
    data: function () {
        return {
            id: null,
            text: '',
        }
    },
    watch: {
        messageAttr: function (newVal, oldVal) {
            this.text = newVal.text;
            this.id = newVal.id;
        }
    },
    template: '<div>' +
        '<input type="text" placeholder="Write something" v-model="text" required="required" />' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function () {
            var message = {
                text: this.text,
                id: this.id,
            };

            if (this.id) {
                messagesApi.update({}, message).then(response =>
                    response.json().then(data => {
                            var index = getIndex(this.messages, data.id);
                            this.messages.splice(index, 1, data);
                            this.text = '';
                            this.id = null;
                        }
                    )
                );
            } else {
                messagesApi.save({}, message).then(response =>
                    response.json().then(data => {
                            this.messages.push(data);
                            this.text = '';
                        }
                    )
                );
            }
        }
    },
});


Vue.component('messages-row', {
    props: ['message', 'editMethods', 'messages'],
    template: '<div style="padding: 2px"><b>{{message.id}}</b>: {{message.text}}' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" @click="edit"> ' +
        '<input type="button" value="Delete" @click="del">' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethods(this.message);
        },
        del: function () {
            messagesApi.remove({id: this.message.id}).then(response => {
                if (response.ok) {
                    this.messages.splice(this.messages.indexOf(this.message), 1)
                }
            })
        }
    }
});

// Определяем новый компонент под именем todo-item
Vue.component('messages-list', {
    props: ['messages'],
    data: function () {
        return {
            message: null
        }
    },
    template: '<div style="position: relative; width: 300px;">' +
        '<messages-form  v-bind:messages="messages" v-bind:messageAttr="message" />' +
        '<messages-row ' +
        'v-for="message in messages" ' +
        'v-bind:key="message.id" ' +
        'v-bind:message="message" ' +
        'v-bind:messages="messages" ' +
        'v-bind:editMethods="editMethods" ' +
        '/>' +
        '</div>',
    created: function () {
        messagesApi.get().then(response =>
            response.json().then(data =>
                data.forEach(message => this.messages.push(message))
            )
        );
    },
    methods: {
        editMethods: function (message) {
            this.message = message;
        }
    }
});


var app = new Vue({
    el: '#app',
    template: '<messages-list :messages="messages" />',
    data: {
        messages: []
    }
});
<template>
    <v-layout row>
        <v-flex xs12 sm6 md5>
            <v-text-field
                    label="Сообщение"
                    placeholder="Введите сообщение"
                    v-model="text"
                    required="required"
                    outline></v-text-field>
        </v-flex>
        <v-flex xs12 sm6 md3>
            <v-btn @click="save" large color="primary">
                Save
            </v-btn>
        </v-flex>
    </v-layout>
</template>

<script>
    import messagesApi from 'api/messages'

    export default {
        name: "MessagesForm",
        props: ['messages', 'messageAttr'],
        data() {
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
        methods: {
            save: function () {
                const message = {
                    id: this.id,
                    text: this.text
                };
                if (this.id) {
                    messagesApi.update(message).then(response =>
                        response.json().then(data => {
                                const index = this.messages.findIndex(item => item.id === data.id);
                                this.messages.splice(index, 1, data);
                            }
                        )
                    );
                } else {
                    messagesApi.add(message).then(response =>
                        response.json().then(data => {
                                const index = this.messages.findIndex(item => item.id === data.id);
                                if (index > -1) {
                                    this.messages.splice(index, 1, data);
                                } else {
                                    this.messages.push(data);
                                }
                            }
                        )
                    );
                }

                this.text = '';
                this.id = null;
            }
        },
    }
</script>

<style scoped>

</style>
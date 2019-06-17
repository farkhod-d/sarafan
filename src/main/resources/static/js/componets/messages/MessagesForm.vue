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

    <!--<v-layout row>-->

    <!--<v-btn @click="save">-->
    <!--Save-->
    <!--</v-btn>-->
    <!--</v-layout>-->
</template>

<script>
    import {sendMessage} from "util/ws";

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
                sendMessage({id: this.id, text: this.text})
                this.text = '';
                this.id = null;

                /*const message = {
                    text: this.text,
                    id: this.id,
                };
                if (this.id) {
                    this.$resource("/messages{/id}").update({}, message).then(response =>
                        response.json().then(data => {
                                const index = getIndex(this.messages, data.id);
                                this.messages.splice(index, 1, data);
                                this.text = '';
                                this.id = null;
                            }
                        )
                    );
                } else {
                    this.$resource("/messages{/id}").save({}, message).then(response =>
                        response.json().then(data => {
                                this.messages.push(data);
                                this.text = '';
                            }
                        )
                    );
                }*/
            }
        },
    }
</script>

<style scoped>

</style>
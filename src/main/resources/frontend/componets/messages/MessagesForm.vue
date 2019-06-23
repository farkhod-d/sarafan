<template>
    <v-layout row>
        <v-flex xs12 sm6 md5>
            <v-text-field
                    label="Сообщение"
                    placeholder="Введите сообщение"
                    v-model="text"
                    @keyup.enter="save"
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
    import {mapActions} from 'vuex';

    export default {
        name: "MessagesForm",
        props: ['messageAttr'],
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
            ...mapActions(['addMessageAction', 'updateMessageAction']),
            save: function () {
                const message = {
                    id: this.id,
                    text: this.text
                };
                if (this.id) {
                    this.updateMessageAction(message);
                } else {
                    this.addMessageAction(message);
                }

                this.text = '';
                this.id = null;
            }
        },
    }
</script>

<style scoped>

</style>
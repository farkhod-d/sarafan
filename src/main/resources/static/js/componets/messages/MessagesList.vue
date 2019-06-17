<template>
    <v-layout align-space-around justify-start column>
        <messages-form v-bind:messages="messages" v-bind:messageAttr="message"/>

        <v-layout row>
            <v-flex xs12 sm6>
            <messages-row v-for="message in sortedMessages"
                          v-bind:key="message.id"
                          v-bind:message="message"
                          v-bind:messages="messages"
                          v-bind:editMessage="editMessage"
                          v-bind:deleteMessage="deleteMessage"/>

            </v-flex>
        </v-layout>
    </v-layout>
</template>

<script>
    import MessagesRow from "componets/messages/MessagesRow.vue"
    import MessagesForm from "componets/messages/MessagesForm.vue"
    import messagesApi from 'api/messages'

    export default {
        name: "MessagesList",
        components: {
            MessagesRow, MessagesForm
        },
        props: ['messages'],
        data() {
            return {
                message: null
            }
        },
        methods: {
            editMessage(message) {
                this.message = message
            },
            deleteMessage(message) {
                messagesApi.remove(message.id).then(response => {
                    if (response.ok) {
                        const index = this.messages.findIndex(item => item.id === message.id);
                        if (index > -1) {
                            this.messages.splice(index, 1);
                        }
                    }
                })
            }
        },
        computed: {
            sortedMessages() {
                return this.messages.sort((a, b) => -(a.id - b.id))
            }
        }
    }
</script>

<style scoped>

</style>
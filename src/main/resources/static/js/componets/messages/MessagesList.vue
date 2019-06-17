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
                this.$resource("/messages{/id}").remove({id: message.id}).then(response => {
                    if (response.ok) {
                        this.messages.splice(this.messages.indexOf(message), 1)
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
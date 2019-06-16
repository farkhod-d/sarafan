<template>
    <div style="position: relative; width: 300px;">
        <messages-form v-bind:messages="messages" v-bind:messageAttr="message"/>
        <messages-row v-for="message in messages"
                      v-bind:key="message.id"
                      v-bind:message="message"
                      v-bind:messages="messages"
                      v-bind:editMessage="editMessage"
                      v-bind:deleteMessage="deleteMessage"
        />
    </div>
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
        }
    }
</script>

<style scoped>

</style>
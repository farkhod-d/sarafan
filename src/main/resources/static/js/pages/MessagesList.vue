<template>
    <v-container fluid>
        <v-layout align-space-around justify-start column>
            <messages-form v-bind:messageAttr="message"/>

            <v-layout row>
                <v-flex xs12 sm6>
                    <messages-row v-for="message in sortedMessages"
                                  v-bind:key="message.id"
                                  v-bind:message="message"
                                  v-bind:editMessage="editMessage"
                    />
                </v-flex>
            </v-layout>
        </v-layout>
    </v-container>
</template>

<script>
    import MessagesRow from "componets/messages/MessagesRow.vue"
    import MessagesForm from "componets/messages/MessagesForm.vue"
    import {mapGetters} from 'vuex';

    export default {
        name: "MessagesList",
        components: {
            MessagesRow, MessagesForm
        },
        data() {
            return {
                message: null
            }
        },
        methods: {
            editMessage(message) {
                this.message = message
            },
        },
        computed: mapGetters(['sortedMessages']),
    }
</script>

<style scoped>

</style>
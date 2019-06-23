<template>
    <v-flex my-2>
        <v-card>
            <v-card-title primary-title>
                <b>{{message.id}}</b>: {{message.text}}
            </v-card-title>
            <media v-if="message.link" :message="message"></media>
            <v-card-actions>
                <v-btn flat @click="edit" round>Edit</v-btn>
                <v-btn icon flat @click="del">
                    <v-icon>delete</v-icon>
                </v-btn>
            </v-card-actions>

            <comment-list
                    :message-id="message.id"
                    :comments="message.comments"
            />
        </v-card>
    </v-flex>
</template>

<script>
    import {mapActions} from 'vuex';
    import Media from 'componets/media/Media.vue'
    import CommentList from "../comment/CommentList.vue";

    export default {
        props: ['message', 'editMessage'],
        components: {
            CommentList,
            Media
        },
        methods: {
            ...mapActions(['removeMessageAction']),
            edit() {
                this.editMessage(this.message)
            },
            del() {
                this.removeMessageAction(this.message)
            }
        }
    }
</script>

<style scoped>

</style>
<template>
    <v-flex my-2>
        <v-card>
            <v-card-title primary-title>
                <b>{{message.id}}</b>: {{message.text}}
            </v-card-title>
            <v-card-actions>
                <v-btn flat @click="edit" round>Edit</v-btn>
                <v-btn icon flat @click="del">
                    <v-icon>delete</v-icon>
                </v-btn>
            </v-card-actions>
        </v-card>
    </v-flex>
</template>

<script>
    import {mapActions} from 'vuex';
    export default {
        props: ['message', 'editMessage'],
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
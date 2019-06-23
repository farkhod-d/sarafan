<template>
    <v-layout row>
        <v-flex xs12 sm6 md5>
            <v-text-field
                    label="Комментарий"
                    placeholder="Введите комментарий"
                    v-model="text"
                    @keyup.enter="save"
                    required="required"
                    outline></v-text-field>
        </v-flex>
        <v-flex xs12 sm6 md3>
            <v-btn @click="save" large color="primary">
                Add
            </v-btn>
        </v-flex>
    </v-layout>
</template>

<script>
    import {mapActions} from 'vuex';

    export default {
        name: "CommentForm",
        props: ['messageId'],
        data() {
            return {
                text: '',
            }
        },
        methods: {
            ...mapActions(['addCommentAction']),
            async save() {
                await this.addCommentAction({
                    text: this.text,
                    message: {
                        id: this.messageId
                    },
                });

                this.text = '';
            },
        },
    }
</script>

<style scoped>

</style>
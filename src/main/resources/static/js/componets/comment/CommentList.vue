<template>
    <v-list two-line>
        <v-subheader>Comments</v-subheader>

        <template v-for="(item, index) in comments">
            <v-divider v-if="index > 0" :key="'cl' + index"></v-divider>
            <comment-item :comment="item" :key="'c' + item.id"/>
        </template>

        <comment-form :message-id="messageId"/>
    </v-list>
</template>

<script>
    import CommentItem from "./CommentItem.vue";
    import CommentForm from "./CommentForm.vue";

    export default {
        name: "CommentList",
        components: {CommentForm, CommentItem},
        props: ['comments', 'messageId'],

    }
</script>

<style scoped>

</style>
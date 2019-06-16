<template>
    <div style="padding: 2px"><b>{{message.id}}</b>: {{message.text}}
        <span style="position: absolute; right: 0">
        <input type="button" value="Edit" @click="edit">
        <input type="button" value="Delete" @click="del">
        </span>
    </div>
</template>

<script>
    export default {
        props: ['message', 'editMessage', 'deleteMessage'],
        methods: {
            edit() {
                this.editMessage(this.message)
            },
            del() {
                this.deleteMessage(this.message)
            }
        }
    }
</script>

<style scoped>

</style>
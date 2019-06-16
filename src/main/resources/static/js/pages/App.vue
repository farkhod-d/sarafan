<template>
    <div>
        <div v-if="!profile" style="margin-bottom: 5px">Вход через <a href="/login">Google</a></div>
        <div v-else>
            <div style="margin-bottom: 5px">
                {{profile.name}} &nbsp; <a href="/logout">Выход</a>
            </div>
            <messages-list :messages="messages"/>
        </div>
    </div>
</template>

<script>
    import MessagesList from "componets/messages/MessagesList.vue"

    export default {
        components: {
            MessagesList
        },
        data() {
            return {
                messages: [],
                profile: frontendData.profile,
            };
        },

        created() {
            if (this.profile !== null) {
                this.$resource("/messages{/id}").get().then(response =>
                    response.json().then(data =>
                        data.forEach(message => this.messages.push(message))
                    )
                );
            }
        },
    }
</script>

<style>

</style>
<template>
    <v-container fluid>
        Необходимо авторизоваться через <a href="/login">Google</a>
    </v-container>
</template>

<script>
    import {mapState} from 'vuex'
    export default {
        name: "Auth",
        computed: mapState(['profile']),
        beforeMount() {
            if (this.profile !== null) {
                this.$router.push('/')
            }
        },
    }
</script>

<style scoped>

</style>
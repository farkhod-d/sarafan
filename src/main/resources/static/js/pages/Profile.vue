<template>
    <v-container>
        <v-layout justify-space-around>
            <v-flex>
                <div class="title mb-3"><h3>User profile</h3></div>
                <v-layout justify-start row>
                    <v-flex sm2 mb2 class="px-1">
                        <v-img :src="profile.userpic" />
                    </v-flex>
                    <v-flex sm10 mb10 class="px-1">
                        <v-layout column>
                            <v-flex>{{profile.name}}</v-flex>
                            <v-flex>{{profile.email}}</v-flex>
                            <v-flex>{{profile.locale}}</v-flex>
                            <v-flex>{{profile.gender}}</v-flex>
                            <v-flex>{{profile.lastVisit}}</v-flex>
                        </v-layout>
                    </v-flex>
                </v-layout>
            </v-flex>
        </v-layout>
    </v-container>
</template>

<script>
    import {mapState} from 'vuex';
    export default {
        name: "Profile",
        computed: mapState(['profile']),
    }
</script>

<style scoped>

</style>
<template>
    <v-app id="inspire">
        <v-navigation-drawer clipped fixed v-model="drawer" app>
            <v-list dense v-if="profile">

                <v-list-tile @click="showMessages" :disabled="$route.path === '/'">
                    <v-list-tile-action>
                        <v-icon>message</v-icon>
                    </v-list-tile-action>
                    <v-list-tile-content>
                        <v-list-tile-title>Messages</v-list-tile-title>
                    </v-list-tile-content>
                </v-list-tile>
                <v-list-tile @click="showProfile" :disabled="$route.path === '/profile'">
                    <v-list-tile-action>
                        <v-icon>contacts</v-icon>
                    </v-list-tile-action>
                    <v-list-tile-content>
                        <v-list-tile-title>Profile</v-list-tile-title>
                    </v-list-tile-content>
                </v-list-tile>
                <!-- <v-list-tile @click="">
                    <v-list-tile-action>
                        <v-icon>settings</v-icon>
                    </v-list-tile-action>
                    <v-list-tile-content>
                        <v-list-tile-title>Settings</v-list-tile-title>
                    </v-list-tile-content>
                </v-list-tile> -->
            </v-list>
        </v-navigation-drawer>
        <v-toolbar app fixed clipped-left>
            <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
            <v-toolbar-title>Sarafan</v-toolbar-title>
            <v-spacer></v-spacer>
            <div v-if="profile">
                <v-btn flat @click="showProfile">
                    {{profile.name}}
                </v-btn>
                <v-btn flat icon href="/logout">
                    <v-icon>exit_to_app</v-icon>
                </v-btn>
            </div>
            <div v-else>
                <v-btn flat href="/login" round>Вход</v-btn>
            </div>
        </v-toolbar>
        <v-content>
            <!-- отображаем тут компонент, для которого совпадает маршрут -->
            <router-view></router-view>
        </v-content>
    </v-app>
</template>

<script>
    import {addHandler} from "util/ws";
    import {mapState, mapActions, mapMutations} from 'vuex';

    export default {
        data() {
            return {
                drawer: null,
            }
        },
        computed: mapState(['profile', 'messages']),
        methods: {
            ...mapActions(['getAllMessagesAction']),
            ...mapMutations(['addMessageMutations', 'updateMessageMutations', 'removeMessageMutations']),
            showMessages() {
                this.$router.push('/')
            },
            showProfile() {
                this.$router.push('/profile')
            }
        },
        created() {
            if (this.profile !== null) {
                this.getAllMessagesAction();
                addHandler(data => {
                    if (data.objectType === 'MESSAGE') {
                        switch (data.eventType) {
                            case 'CREATE':
                                this.addMessageMutations(data.body);
                                break;
                            case 'UPDATE':
                                this.updateMessageMutations(data.body);
                                break;
                            case 'REMOVE':
                                this.removeMessageMutations(data.body);
                                break;
                            default:
                                console.error(`Look like the event type if unknown ${data.eventType}`);
                                break;
                        }
                    } else {
                        console.error(`Look like the object type if unknown ${data.objectType}`);
                    }
                })
            }
        },
        beforeMount() {
            if (!this.profile) {
                this.$router.replace('/auth')
            }
        },
    }
</script>

<style>
</style>
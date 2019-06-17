<template>
    <v-app id="inspire">
        <v-navigation-drawer clipped fixed v-model="drawer" app>
            <v-list dense>
                <v-list-tile @click="">
                    <v-list-tile-action>
                        <v-icon>dashboard</v-icon>
                    </v-list-tile-action>
                    <v-list-tile-content>
                        <v-list-tile-title>Dashboard</v-list-tile-title>
                    </v-list-tile-content>
                </v-list-tile>
                <v-list-tile @click="">
                    <v-list-tile-action>
                        <v-icon>settings</v-icon>
                    </v-list-tile-action>
                    <v-list-tile-content>
                        <v-list-tile-title>Settings</v-list-tile-title>
                    </v-list-tile-content>
                </v-list-tile>
            </v-list>
        </v-navigation-drawer>
        <v-toolbar app fixed clipped-left>
            <v-toolbar-side-icon @click.stop="drawer = !drawer"></v-toolbar-side-icon>
            <v-toolbar-title>Sarafan</v-toolbar-title>
            <v-spacer></v-spacer>
            <div v-if="profile">
                <span>{{profile.name}}</span>
                <v-btn flat icon href="/logout">
                    <v-icon>exit_to_app</v-icon>
                </v-btn>
            </div>
            <div v-else>
                <v-btn flat href="/login" round>Вход</v-btn>
            </div>
        </v-toolbar>
        <v-content>
            <v-container fluid v-if="profile">
                <messages-list/>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import MessagesList from "componets/messages/MessagesList.vue"
    import {addHandler} from "util/ws";
    import {mapState, mapActions, mapMutations} from 'vuex';

    export default {
        components: {
            MessagesList
        },
        data() {
            return {
                drawer: null,
            }
        },
        computed: mapState(['profile', 'messages']),
        methods: {
            ...mapActions(['getAllMessagesAction']),
            ...mapMutations(['addMessageMutations', 'updateMessageMutations', 'removeMessageMutations']),
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
    }
</script>

<style>
</style>
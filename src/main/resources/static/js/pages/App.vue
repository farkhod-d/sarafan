<template>
    <v-app id="inspire" dark1>
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
                <messages-list :messages="messages"/>
            </v-container>
        </v-content>
    </v-app>
</template>

<script>
    import MessagesList from "componets/messages/MessagesList.vue"
    import {addHandler} from "util/ws";
    import {getIndex} from "util/collections";

    export default {
        components: {
            MessagesList
        },
        data() {
            return {
                messages: [],
                profile: frontendData.profile,
                drawer: null
            };
        },
        beforeCreate() {
            // console.log("beforeCreate")
            // if (this.profile !== null) {
            //     this.$resource("/messages{/id}").get().then(response =>
            //         response.json().then(data => {
            //             data.forEach(message => {
            //                 this.messages.push(message);
            //                 console.log("message push")
            //                 console.log(message)
            //             });
            //             // addHandler(data => {
            //             //     let index = getIndex(this.messages, data.id)
            //             //     if (index > -1) {
            //             //         this.messages.splice(index, 1, data)
            //             //     } else {
            //             //         this.messages.push(data)
            //             //     }
            //             // })
            //         })
            //     );
            // }
        },
        // beforeMount() {
        //     console.log("beforeMount")
        // },
        // mounted() {
        //     console.log("mounted")
        // },
        // beforeUpdate() {
        //     console.log("beforeUpdate")
        // },
        updated() {
            // console.log("updated")
            // if (this.profile !== null) {
            //     addHandler(data => {
            //         let index = getIndex(this.messages, data.id)
            //         if (index > -1) {
            //             this.messages.splice(index, 1, data)
            //         } else {
            //             this.messages.push(data)
            //         }
            //     })
            // }
        },
        created() {
            if (this.profile !== null) {
                if (this.profile !== null) {
                    this.$resource("/messages{/id}").get().then(response =>
                        response.json().then(data => {
                            data.forEach(message => {
                                this.messages.push(message);
                            });
                        })
                    );

                    addHandler(message => {
                        let index = getIndex(this.messages, message.id)
                        if (index > -1) {
                            this.messages.splice(index, 1, message)
                        } else {
                            this.messages.push(message)
                        }
                    })
                }
            }
        },
    }
</script>

<style>
</style>
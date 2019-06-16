<template>
    <div>
        <input type="text" placeholder="Write something" v-model="text" required="required"/>
        <input type="button" value="Save" @click="save"/>
    </div>
</template>

<script>
    export default {
        name: "MessagesForm",
        props: ['messages', 'messageAttr'],
        data() {
            return {
                id: null,
                text: '',
            }
        },
        watch: {
            messageAttr: function (newVal, oldVal) {
                this.text = newVal.text;
                this.id = newVal.id;
            }
        },
        methods: {
            save: function () {
                const message = {
                    text: this.text,
                    id: this.id,
                };

                if (this.id) {
                    this.$resource("/messages{/id}").update({}, message).then(response =>
                        response.json().then(data => {
                                const index = getIndex(this.messages, data.id);
                                this.messages.splice(index, 1, data);
                                this.text = '';
                                this.id = null;
                            }
                        )
                    );
                } else {
                    this.$resource("/messages{/id}").save({}, message).then(response =>
                        response.json().then(data => {
                                this.messages.push(data);
                                this.text = '';
                            }
                        )
                    );
                }
            }
        },
    }

    function getIndex(list, id) {
        for (let i = 0; i < list.length; i++) {
            if (list[i].id === id) {
                return i;
            }
        }
        return -1;
    }
</script>

<style scoped>

</style>
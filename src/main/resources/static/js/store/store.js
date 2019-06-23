import Vue from 'vue'
import Vuex from "vuex"
import messagesApi from 'api/messages'
import commentsApi from 'api/comment'

Vue.use(Vuex);

export default new Vuex.Store({
    state: {
        messages: [],
        profile: frontendData.profile,
    },
    getters: {
        sortedMessages: state => state.messages.sort((a, b) => -(a.id - b.id))
    },
    mutations: {
        addMessageMutations(state, message) {
            state.messages = [
                ...state.messages,
                message
            ]
        },
        updateMessageMutations(state, message) {
            const updateIndex = state.messages.findIndex(item => item.id === message.id);
            state.messages = [
                ...state.messages.splice(0, updateIndex),
                message,
                ...state.messages.splice(updateIndex + 1),
            ]
        },
        removeMessageMutations(state, message) {
            const deletionIndex = state.messages.findIndex(item => item.id === message.id);
            if (deletionIndex > -1) {
                state.messages = [
                    ...state.messages.splice(0, deletionIndex),
                    ...state.messages.splice(deletionIndex + 1),
                ]
            }
        },
        getAllMessages(state, data) {
            state.messages = data
        },

        addCommentMutations(state, comment) {
            const updateIndex = state.messages.findIndex(item => item.id === comment.message.id);
            const message = state.messages[updateIndex];

            state.messages = [
                ...state.messages.splice(0, updateIndex),
                {
                    ...message,
                    comments: [
                        ...message.comments,
                        comment,
                    ],
                },
                ...state.messages.splice(updateIndex + 1),
            ]
        },

    },
    actions: {
        async addMessageAction({commit, state}, message) {
            const result = await messagesApi.add(message)
            const data = await result.json()
            const index = state.messages.findIndex(item => item.id === data.id);
            if (index > -1) {
                commit('updateMessageMutations', data);
            } else {
                commit('addMessageMutations', data);
            }
        },
        async updateMessageAction({commit}, message) {
            const result = await messagesApi.update(message)
            const data = await result.json()
            commit('updateMessageMutations', data);
        },
        async removeMessageAction({commit}, message) {
            const result = await messagesApi.remove(message.id)
            if (result.ok) {
                commit('removeMessageMutations', message);
            }
        },
        async getAllMessagesAction({commit}) {
            const result = await messagesApi.get()
            const data = await result.json()
            commit('getAllMessages', data);
        },

        async addCommentAction({commit, state}, comment) {
            const result = await commentsApi.add(comment)
            const data = await result.json()
            commit('addCommentMutations', comment);
        }
    }
})





















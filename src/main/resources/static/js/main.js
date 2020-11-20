Vue.component('messages-list',{
  props: ['messages'],
  template: '<div><div v-for="message in messages">{{ message.text }}</div></div>'
});

var app = new Vue({
  el: '#app',
  template: '<messages-list :messages="messages" />',
  data: {
    messages: [
      {id: '1', text: 'First'},
      {id: '2', text: 'Second'},
      {id: '3', text: 'Third'}
    ]
  }
});
import Ember from 'ember';

export default Ember.Route.extend({
  model: function() {
      // return a list of user models, populated with your friends
      var friends = [];
      var friend;

      friend = this.get('store').createRecord('user', {name: "bob", firstName: 'Bob', lastName: "Jones", email: "bob@gmail.com"});
      friends.push(friend);

      friend = this.get('store').createRecord('user', {name: "tom", firstName: 'Tom', lastName: "Smith", email: "tom@gmail.com"});
      friends.push(friend);

      return friends;
  }
});

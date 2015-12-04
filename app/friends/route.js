import Ember from 'ember';

export default Ember.Route.extend({
  model: function() {
      // return a list of user models, populated with your friends
      var friends = [];
      var friend;

      friend = this.get('store').createRecord('user', {firstName: 'Bob'});
      friends.push(friend);
      friend = this.get('store').createRecord('user', {firstName: 'Tom'});
      friends.push(friend);

      return friends;
  }
});

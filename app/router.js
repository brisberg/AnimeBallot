import Ember from 'ember';
import config from './config/environment';

var Router = Ember.Router.extend({
  location: config.locationType
});

Router.map(function() {
  this.route('users', {});
  this.route('series', {});
  this.route('episodes', {}, function() {
    this.route('by-week', {path: "by-week/:week_index"});
  });
});

export default Router;

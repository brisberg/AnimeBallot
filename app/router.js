import Ember from 'ember';
import config from './config/environment';

var Router = Ember.Router.extend({
    location: config.locationType
});

Router.map(function () {
    this.route('vote', {});
    this.route('episodes', {}, function () {
        this.route('calendar', {});
        this.route('by-week', {path: "by-week/:week_index"});
    });
    this.route('users', {});
    this.route('series', {});
});

export default Router;

import Ember from 'ember';
import config from './config/environment';

var Router = Ember.Router.extend({
    location: config.locationType,

    initializeConfig: function (transition) {
        //stuff
        //var configuration = this.get('store').findRecord('configuration', 0);
        if (transition) {
            console.log(transition);
            console.log("logging transition");
        }
        console.log(transition);
        //console.log(configuration);
        console.log("do something");
        console.log("loading configuration");
    }.on('willTransition')
});

Router.map(function () {
    this.route('register');
    this.route('login');
    this.route('voting', {}, function () {
    });
    this.route('episodes', {}, function () {
        this.route('calendar', {});
        this.route('by-week', {path: "by-week/:week_index"});
    });
    this.route('profile', {}, function () {
        this.route('show', {});
        this.route('edit', {});
    });
    this.route('friends', {}, function () {
        //this.route('show', {});
        //this.route('edit', {});
    });
    this.route('configuration');
});

export default Router;

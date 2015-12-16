import Ember from 'ember';
import config from './config/environment';

var Router = Ember.Router.extend({
    location: config.locationType
});

Router.map(function () {
    this.route('register');
    this.route('login');

    this.route('configured', {}, function () {
        this.route('voting', {});
        this.route('dashboard', {path: 'dashboard/:user_id'});
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
});

export default Router;

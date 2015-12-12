import Ember from 'ember';

export default Ember.Route.extend({
    model: function () {
        // Testing the configuration
        var configuration = this.get('store').findRecord('configuration', 0);

        return configuration;
    }

});

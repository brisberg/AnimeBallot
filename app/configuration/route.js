import Ember from 'ember';
import WithConfiguration from 'animeBallot/mixins/with-configuration';

export default Ember.Route.extend(WithConfiguration, {
    model: function () {
        // Testing the configuration
        //var configuration = this.get('store').findRecord('configuration', 0);

        return this.get('configuration');
    }

});

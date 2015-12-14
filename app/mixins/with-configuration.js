import Ember from 'ember';

export default Ember.Mixin.create({
    beforeModel: function () {
        this.set('configuration', this.get('store').findRecord('configuration', 0));

    }
});

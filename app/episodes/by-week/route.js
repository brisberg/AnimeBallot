import Ember from 'ember';

export default Ember.Route.extend({
    model: function (params) {
        var weekIndex = params.week_index;
        var episodes = this.get('store').query('episode', {weekIndex: weekIndex});

        return {weekIndex: weekIndex, episodes: episodes};
    },
    actions: {
    }
});

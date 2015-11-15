import Ember from 'ember';

export default Ember.Route.extend({
    model() {
        var weekIndexes = [];

        weekIndexes.push(1);
        weekIndexes.push(2);
        weekIndexes.push(3);
        weekIndexes.push(4);
        weekIndexes.push(5);

        var episodes = this.get('store').findAll('episode');

        return {weekIndexes: weekIndexes, episodes: episodes};
    }
});

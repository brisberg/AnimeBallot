import DS from 'ember-data';

export default DS.Model.extend({
    ballot: DS.belongsTo('ballot', {async: true}),
    episode: DS.belongsTo('episode', {async: true}),
    score: DS.attr('number')
});
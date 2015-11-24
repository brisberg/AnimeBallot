import DS from 'ember-data';

export default DS.Model.extend({
    ballot: DS.belongsTo('ballot'),
    episode: DS.belongsTo('episode'),
    score: DS.attr('number')
});
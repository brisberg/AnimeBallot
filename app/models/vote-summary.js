import DS from 'ember-data';

export default DS.Model.extend({
    rank: DS.attr('number'),
    series: DS.belongsTo('series'),
    episodeIndex: DS.attr('number'),
    percent: DS.attr('number'),
    change: DS.attr('string')
});

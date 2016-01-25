import DS from 'ember-data';

export default DS.Model.extend({
    episodeIndex: DS.attr('number'),
    series: DS.belongsTo('series', {async: true}),
    season: DS.belongsTo('season', {async: true}),
    weekIndex: DS.attr('number'),

    rank: DS.attr('number'),
    rawScore: DS.attr('number'),
    percentage: DS.attr('number'),
    change: DS.attr('number')
});

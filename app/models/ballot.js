import DS from 'ember-data';

export default DS.Model.extend({
    user: DS.belongsTo('user'),
    weekIndex: DS.attr('number'),
    season: DS.belongsTo('season'),
    ballotVotes: DS.hasMany('ballot-vote'),
    comment: DS.attr('string')
});
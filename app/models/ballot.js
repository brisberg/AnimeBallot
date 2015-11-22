import DS from 'ember-data';

export default DS.Model.extend({
    ballotVotes: DS.hasMany('ballot-vote'),
    user: DS.belongsTo('user'),
    weekIndex: DS.attr('number'),
    season: DS.belongsTo('season')
});
import DS from 'ember-data';

export default DS.Model.extend({
    weekStartDate: DS.attr('number'),
    currentSeason: DS.belongsTo('season', {async: true}),
    currentWeekIndex: DS.attr('number'),

    formattedWeekStartDate: function () {
        var date = this.get('weekStartDate');

        return moment(date).format('LL');
    }.property('weekStartDate')
});

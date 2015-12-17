import DS from 'ember-data';

export default DS.Model.extend({
    title: DS.attr('string'),
    weekIndex: DS.attr('number'),
    airDate: DS.attr('number'),
    series: DS.belongsTo('series', {async: true}),

    formattedAirDate: function () {
        var date = this.get('airDate');

        return moment(date).format('LL');
    }.property('airDate')
});
// we should be using a helper instead of a computed property here
// {{format-air-date episode.airDate}}
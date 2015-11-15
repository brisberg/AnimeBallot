import DS from 'ember-data';

export default DS.Model.extend({
    title: DS.attr('string'),
    weekIndex: DS.attr('number'),
    airDate: DS.attr('number'),

    formattedAirDate: function () {
        var date = this.get('airDate');

        return moment(date).format('LL');
    }.property('airDate')
});

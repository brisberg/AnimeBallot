import DS from 'ember-data';

export default DS.Model.extend({
    title: DS.attr('string'),
    startDate: DS.attr('number'),
    endDate: DS.attr('number'),

    formattedStartDate: function () {
        var date = this.get('startDate');

        return moment(date).format('LL');
    }.property('startDate'),

    formattedEndDate: function () {
        var date = this.get('endDate');

        return moment(date).format('LL');
    }.property('endDate')
});
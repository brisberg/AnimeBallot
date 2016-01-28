import DS from 'ember-data';

export default DS.Model.extend({
    episodeIndex: DS.attr('number'),
    seriesTitle: DS.attr('string'),

    rank: DS.attr('number'),
    percentage: DS.attr('number'),
    change: DS.attr('string'),

    formattedPercentage: function () {
        var value = this.get('percentage') * 100.0;
        var formatted =  value.toFixed(2);
        return formatted + "%";
    }.property('percentage')
});

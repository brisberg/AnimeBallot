import DS from 'ember-data';

export default DS.Model.extend({
    rank: DS.attr('number'),
    series: DS.belongsTo('series', {async: true}),
    episodeIndex: DS.attr('number'),
    percentage: DS.attr('number'),
    change: DS.attr('string'),

    formattedPercentage: function () {
        var value = this.get('percentage') * 100.0;
        var formatted =  value.toFixed(2);
        return formatted + "%";
    }.property('percentage')
});

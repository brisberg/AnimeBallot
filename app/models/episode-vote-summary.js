import DS from 'ember-data';

export default DS.Model.extend({
    seriesTitle: DS.attr('string'),
    weekIndex: DS.attr('number'),
    weekStartDate: DS.attr('number'),

    episodeIndex: DS.attr('number'),
    rank: DS.attr('number'),
    rawScore: DS.attr('number'),
    percentage: DS.attr('number'),
    change: DS.attr('number'),

    formattedPercentage: function () {
        var value = this.get('percentage') * 100.0;
        var formatted = value.toFixed(2);
        return formatted + "%";
    }.property('percentage'),

    formattedChange: function () {
        var change = this.get('change');
        if (change > 0) {
            return "+" + change;
        }
        else if (change === 0) {
            return change;
        }
        else {
            return "<span style='color:red'>" + change + "</span>";
        }
    }.property('change')
});

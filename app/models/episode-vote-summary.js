import DS from 'ember-data';

export default DS.Model.extend({
    seriesTitle: DS.attr('string'),
    weekIndex: DS.attr('number'),
    weekStartDate: DS.attr('number'),

    rank: DS.attr('number'),
    rawScore: DS.attr('number'),
    percentage: DS.attr('number'),
    change: DS.attr('number')
});

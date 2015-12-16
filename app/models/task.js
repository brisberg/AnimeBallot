import DS from 'ember-data';

export default DS.Model.extend({
    name: DS.attr('string'),
    user: DS.belongsTo('user', {async: true}),
    dueDate: DS.attr('number'),
    done: DS.attr('boolean'),

    formattedDueDate: function () {
        var date = this.get('dueDate');

        return moment(date).format('LL');
    }.property('dueDate')
});
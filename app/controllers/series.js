import Ember from "ember";

export default Ember.Controller.extend({
    actions: {
        createTask(task) {
            const item = this.get('store').createRecord('series', {
                task,
                isDone: false
            });
            item.save();
        },
        toggleIsDone(item) {
            item.toggleProperty('isDone');
            item.save();
        }
    }
});
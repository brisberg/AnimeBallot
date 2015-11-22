import Ember from 'ember';

export default Ember.Route.extend({
    model: function (params) {
        console.log(params);
        var weekIndex = 1; // TODO: Hard coded for now
        var formData = null;
        var episodes = this.get('store').query('episode', {weekIndex: weekIndex}).then((episodes) => {
            console.log("Episodes loaded");

            formData = episodes.map(function (episode) {
                return Ember.Object.create({
                    episode: episode,
                    value: null
                });
            });
            console.log("Model hook formData: ");
            console.log(formData);
        });

        //this.controller.get('formData').addObjects(formData);
        return {weekIndex: weekIndex, episodes: episodes, formData: formData};
    },
    actions: {
        submitVote: function (params) {
            console.log(params);
            console.log(this.controller.get('model').formData);
            this.transitionTo('index');
        }
    }
});

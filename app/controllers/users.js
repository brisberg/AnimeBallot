import Ember from "ember";

export default Ember.Controller.extend({
    actions: {
        createSurvey(title) {
            const survey = this.get('store').createRecord('user', {
                title,
                expired: "false",
                totalVotes: 0
            });
            console.log("about to save");
            survey.save();
            console.log("done saving");
        }
    }
});
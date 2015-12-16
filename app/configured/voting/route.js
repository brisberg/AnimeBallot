import Ember from 'ember';

export default Ember.Route.extend({

    model: function () {
        var user = this.controllerFor('application').get('user');

        var configuration = this.modelFor('configured');
        var currentSeason = configuration.get('currentSeason');
        var currentWeekIndex = configuration.get('currentWeekIndex');

        var episodes = this.get('store').query('episode', {weekIndex: currentWeekIndex});
        episodes.then(
            (data) => {
                this.set('currentEpisodes', data);
            }
        );

        // Handover to the action
        this.set('currentUser', user);
        this.set('currentSeason', currentSeason);
        this.set('currentWeekIndex', currentWeekIndex);

        return {weekIndex: currentWeekIndex, episodes: episodes};
    },

    actions: {
        submitVote: function () {
            var user = this.get('currentUser');
            var season = this.get('currentSeason');
            var weekIndex = this.get('currentWeekIndex');
            var episodes = this.get('currentEpisodes');

            // Create a ballot
            var ballot = this.get('store').createRecord('ballot',
                {user: user, weekIndex: weekIndex, season: season, comment: this.controller.get('comment')});

            var me = this;
            episodes.forEach(function (episode) {
                var episodeId = episode.id;
                var element = document.getElementById("score_" + episodeId);
                var score = element.value;

                // Create a ballotVote
                var ballotVote = me.get('store').createRecord('ballot-vote', {episode: episode, score: score});
                ballot.get('ballotVotes').pushObject(ballotVote);
            });

            ballot.save().then(() => {
                this.transitionTo('configured.dashboard', user.get('id'));
            });
        }
    }
});

import Ember from 'ember';
import WithConfiguration from 'animeBallot/mixins/with-configuration';

export default Ember.Route.extend(WithConfiguration, {
    model: function () {
        var configuration, userId, weekIndex, episodes;

        this.get('configuration').then(() => {
            configuration = this.get('configuration');
            userId = 1; // TODO: Hard coded for now
            weekIndex = configuration.currentWeekIndex;
            console.log(configuration);
            console.log("WeekIndex: " + weekIndex);
            this.set('currentSeason', configuration.currentSeason);

            var user = this.get('store').findRecord('user', userId);

            user.then(
                (data) => {
                    this.set('currentUser', data);
                }
            );

            this.set('currentWeekIndex', weekIndex);

            episodes = this.get('store').query('episode', {weekIndex: weekIndex});

            episodes.then(
                (data) => {
                    this.set('currentEpisodes', data);
                }
            );
        });

        return {weekIndex: weekIndex, episodes: episodes};
    },

    actions: {
        submitVote: function () {
            var user = this.get('currentUser');
            var season = this.get('currentSeason');
            var weekIndex = this.get('currentWeekIndex');
            var episodes = this.get('currentEpisodes');

            // Create a ballot
            var ballot = this.get('store').createRecord('ballot',
                {user: user, weekIndex: weekIndex, season: season, comment: "Hello Jeff"});

            var me = this;
            episodes.forEach(function (episode) {
                var episodeId = episode.id;
                var element = document.getElementById("score_" + episodeId);
                var score = element.value;

                // Create a ballotVote
                var ballotVote = me.get('store').createRecord('ballot-vote', {episode: episode, score: score});
                ballot.get('ballotVotes').pushObject(ballotVote);
            });

            ballot.save();
            this.transitionTo('index');
        }
    }
});

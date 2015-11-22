import Ember from 'ember';

export default Ember.Route.extend({
    model: function () {
        var userId = 1; // TODO: Hard coded for now
        var seasonId = 1; // TODO: Hard coded for now
        var weekIndex = 1; // TODO: Hard coded for now

        var user = this.get('store').findRecord('user', userId);

        user.then(
            (data) => {
                this.set('currentUser', data);
            }
        );

        var season = this.get('store').findRecord('season', seasonId);

        season.then(
            (data) => {
                this.set('currentSeason', data);
            }
        );

        this.set('currentWeekIndex', weekIndex);

        var episodes = this.get('store').query('episode', {weekIndex: weekIndex});

        episodes.then(
            (data) => {
                this.set('currentEpisodes', data);
            }
        );

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
                {user: user, weekIndex: weekIndex, season: season});

            episodes.forEach(function (episode) {
                var episodeId = episode.id;
                console.log(episodeId);
                var element = document.getElementById("score_" + episodeId);
                var value = element.value;
                console.log(value);

                // Create a ballot-vote
            });

            ballot.save();
            this.transitionTo('index');
        }
    }
});

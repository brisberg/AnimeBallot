import Ember from 'ember';

export default Ember.Route.extend({
    model: function () {
        var userId = 1; // TODO: Hard coded for now
        var seasonId = 1; // TODO: Hard coded for now
        var weekIndex = 2; // TODO: Hard coded for now

        // Set up environment
        var user = this.get('store').findRecord('user', userId);

        var season = this.get('store').findRecord('season', seasonId);

        // Get the current ballot votes
        var ballotVotes = this.get('store').query('ballot-vote', {userId: userId, weekIndex: weekIndex});

        // Get the vote summary
        var voteSummaries = this.get('store').query('vote-summary', {weekIndex: weekIndex});

        return {ballotVotes: ballotVotes, voteSummaries: voteSummaries};
    },
    actions: {
    }
});

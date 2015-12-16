import Ember from 'ember';

export default Ember.Route.extend({

    model: function (params) {
        var userId = params.user_id;

        var configuration = this.modelFor('configured');
        var currentSeason = configuration.get('currentSeason');
        var currentWeekIndex = configuration.get('currentWeekIndex');

        // Get the chart data
        // to be written

        // Get the vote summaries
        var voteSummaries = this.get('store').query('vote-summary', {weekIndex: currentWeekIndex});

        // Get the current ballot
        var ballots = [];
        if (userId !== '0') {
            ballots = this.get('store').query('ballot', {userId: userId, weekIndex: currentWeekIndex});
        }

        // Get the current ballot votes
        var ballotVotes = [];
        if (userId !== '0') {
            ballotVotes = this.get('store').query('ballot-vote', {
                userId: userId,
                weekIndex: currentWeekIndex
            });
        }

        // Get the tasks
        var tasks = [];
        if (userId !== '0') {
            tasks = this.get('store').query('task', {userId: userId});
        }

        return {
            userId: userId,
            loggedIn: (userId !== '0'),
            season: currentSeason,
            voteSummaries: voteSummaries,
            ballots: ballots,
            ballotVotes: ballotVotes,
            tasks: tasks
        };
    },
    actions: {}
});

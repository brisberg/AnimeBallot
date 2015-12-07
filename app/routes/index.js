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

        ballotVotes.then(
            (data) => {
                this.set('ballotVotes', data);
            }
        );

        // Collect the current results list
        var resultList = [];
        var result;

        result = {rank: 1, title: "Glasslip", episodeIndex: 4, percent: 93.2, change: "+2"};
        resultList.push(result);

        result = {rank: 2, title: "K: Return of Kings", episodeIndex: 4, percent: 57.3, change: "+0"};
        resultList.push(result);

        result = {rank: 3, title: "Absolute", episodeIndex: 4, percent: 45.7, change: "+0"};
        resultList.push(result);

        result = {rank: 4, title: "School-Live", episodeIndex: 4, percent: 37.3, change: "+1"};
        resultList.push(result);

        result = {rank: 5, title: "Danganronpa", episodeIndex: 4, percent: 25.5, change: "-2"};
        resultList.push(result);

        return {user: user, season: season, ballotVotes: ballotVotes, resultList: resultList};
    }
});

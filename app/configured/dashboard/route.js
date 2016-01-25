import Ember from 'ember';

export default Ember.Route.extend({

    model: function (params) {
        var userId = params.user_id;

        var configuration = this.modelFor('configured');
        var currentSeason = configuration.get('currentSeason');
        var currentWeekIndex = configuration.get('currentWeekIndex');

        // Get the chart data
        var episodeVoteSummaries = this.get('store').query('episode-vote-summary', {weekIndex: currentWeekIndex});

        var chartData = [];
        var chart;

        chart = {
            name: "Asterisk",
            data: [
                {x: 1449101800, y: 10},
                {x: 1449706600, y: 20},
                {x: 1450311401, y: 30}
            ],
            color: 'red'
        };
        chartData.push(chart);

        chart = {
            name: "One-Punch Man",
            data: [
                {x: 1449101800, y: 20},
                {x: 1449706600, y: 19},
                {x: 1450311401, y: 24}
            ],
            color: 'blue'
        };
        chartData.push(chart);

        chart = {
            name: "Rakudai",
            data: [
                {x: 1449101800, y: 12},
                {x: 1449706600, y: 22},
                {x: 1450311401, y: 29}
            ],
            color: 'green'
        };
        chartData.push(chart);

        // check the episodeVoteSummaries
        var newChartData = [];
        episodeVoteSummaries.forEach(function (evs) {
            // get the series
            var series = evs.get("series");
            var chartData = null;

            // find if it already exists
            newChartData.forEach(function (cd) {
                if (cd.name === series.name) {
                    chartData = cd;
                }
            });

            if (chartData == null) {
                chartData = {};
                chartData.name = series.name;
                chartData.color = "green";
                newChartData.push(chartData);
            }

            // now add to the time values
            // to be written
        });

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

        Ember.run.scheduleOnce('afterRender', this, function () {
            console.log("beginAfterRender");

            /* jshint ignore:start */
            var graph = new Rickshaw.Graph({
                element: document.querySelector("#chart"),
                width: 540,
                height: 240,
                renderer: 'line',
                series: chartData
            });

            var x_axis = new Rickshaw.Graph.Axis.Time({graph: graph});

            var y_axis = new Rickshaw.Graph.Axis.Y({
                graph: graph,
                orientation: 'left',
                tickFormat: Rickshaw.Fixtures.Number.formatKMBT,
                element: document.getElementById('y_axis')
            });

            var legend = new Rickshaw.Graph.Legend({
                element: document.querySelector('#legend'),
                graph: graph
            });

            console.log(graph);
            console.log(x_axis);
            console.log(y_axis);
            console.log(legend);

            graph.render();
            /* jshint ignore:end */
            console.log("endAfterRender");
        });

        return {
            userId: userId,
            loggedIn: (userId !== '0'),
            season: currentSeason,
            chartData: chartData,
            voteSummaries: voteSummaries,
            ballots: ballots,
            ballotVotes: ballotVotes,
            tasks: tasks
        };
    },

    actions: {}
});

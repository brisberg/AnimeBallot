import Ember from 'ember';

/*global $:false */
/*global d3:false */
/*global Rickshaw:false */
export default Ember.Route.extend({

    model: function (params) {
        var userId = params.user_id;

        var configuration = this.modelFor('configured');
        var currentSeason = configuration.get('currentSeason');
        var currentWeekIndex = configuration.get('currentWeekIndex');

        var seriesData = [];
        var graph;

        // Get the chart data
        this.get('store').query('episode-vote-summary', {startWeekIndex: 1, endWeekIndex: currentWeekIndex-1})
            .then(function (data) {
                var p = d3.scale.category10();
                var newChartData = {};
                var seriesCount = 0;

                data.forEach(function (evs) {
                    // get the series
                    var seriesTitle = evs.get("seriesTitle");
                    var chartData = newChartData[seriesTitle];

                    if (chartData == null) {
                        chartData = {};
                        chartData.name = seriesTitle;
                        chartData.color = p(seriesCount);
                        chartData.data = [];
                        newChartData[seriesTitle] = chartData;
                        seriesCount++;
                    }

                    // now add to the time values
                    chartData.data.push({x: evs.get("weekStartDate")/1000, y: evs.get("rawScore")});
                });
                $.each(newChartData, function (k, v) {
                    seriesData.push(v);
                });
                graph.update();
                $('#legend').empty();
                new Rickshaw.Graph.Legend({
                    element: document.querySelector('#legend'),
                    graph: graph
                });
            });

        Ember.run.scheduleOnce('afterRender', this, function () {
            graph = new Rickshaw.Graph({
                element: document.querySelector("#chart"),
                width: 540,
                height: 240,
                renderer: 'line',
                series: seriesData
            });

            new Rickshaw.Graph.HoverDetail( {
                graph: graph
            } );

            new Rickshaw.Graph.Axis.Time({
                graph: graph
            });

            new Rickshaw.Graph.Axis.Y({
                graph: graph,
                orientation: 'left',
                tickFormat: Rickshaw.Fixtures.Number.formatKMBT,
                element: document.getElementById('y_axis')
            });

            new Rickshaw.Graph.Legend({
                element: document.querySelector('#legend'),
                graph: graph
            });

            graph.render();
        });

        // Get the vote summaries
        var voteSummaries = this.get('store').query('episode-vote-summary', {startWeekIndex: currentWeekIndex-1, endWeekIndex: currentWeekIndex-1});

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
            currentWeekIndex: currentWeekIndex,
            voteSummaries: voteSummaries,
            ballots: ballots,
            ballotVotes: ballotVotes,
            tasks: tasks
        };
    },

    actions: {}
});

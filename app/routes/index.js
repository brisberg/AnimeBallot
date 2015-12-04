import Ember from 'ember';

export default Ember.Route.extend({
    model: function () {
        var resultList = [];
        // add to the resultList
        // this list will be chronological
        var result1 = {date: '11/30/15', text: 'text for Friday'};
        resultList.push(result1);

        var result2 = {date: '12/01/15', text: 'text for Saturday'};
        resultList.push(result2);

        var result3 = {date: '12/02/15', text: 'text for Sunday'};
        resultList.push(result3);

        return {resultList: resultList, comment: 'The summary comment goes here'};
    }
});

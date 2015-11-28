import DS from 'ember-data';

export default DS.RESTSerializer.extend(DS.EmbeddedRecordsMixin, {
    attrs: {
        ballotVotes: {serialize: 'records', deserialize: 'ids'}
    }
});
import Ember from 'ember';
import WithConfigurationMixin from '../../../mixins/with-configuration';
import { module, test } from 'qunit';

module('Unit | Mixin | with configuration');

// Replace this with your real tests.
test('it works', function(assert) {
  var WithConfigurationObject = Ember.Object.extend(WithConfigurationMixin);
  var subject = WithConfigurationObject.create();
  assert.ok(subject);
});

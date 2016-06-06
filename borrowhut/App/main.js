var React  = require('react');
var ReactDOM = require('react-dom');
var injectTapEventPlugin = require('react-tap-event-plugin');
injectTapEventPlugin();
var routes = require('./routes.js');

ReactDOM.render(routes, document.getElementById('main'));

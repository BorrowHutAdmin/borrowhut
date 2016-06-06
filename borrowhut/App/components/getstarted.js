var React  = require('react');
var ReactCSSTransitionGroup = require('react-addons-css-transition-group');
var routerModule = require('react-router');
var Link = routerModule.Link;

var Getstarted = React.createClass({
	render: function() {
	    return (
	    	<div className="scrollContent intro-screens">
	    		<ReactCSSTransitionGroup
		          component="div"
		          transitionName="transition"
		          transitionAppear={true}
		          transitionAppearTimeout={500}
		        >
		        <div className="text-center" id="signUp">
		          	<div className="steps getStart formMessageArea" id="step1">
		            	<h2>Hi, Let’s Set you up</h2>
		            	<p>Follow the following screens<br /> to quickly set you up.</p>
		            	<Link to="/setup-twitter-digits" className="button button-white">Get started</Link>
		          	</div>
		        </div>
		        </ReactCSSTransitionGroup>
	        </div>
	    );
	}
});

module.exports = Getstarted;

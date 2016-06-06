var React  = require('react');
var ReactCSSTransitionGroup = require('react-addons-css-transition-group');
var routerModule = require('react-router');
var Link = routerModule.Link;

var ThankYou = React.createClass({
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
						<div className="steps formMessageArea">
							<h2>Thank you for completing the set up</h2>
							<p>Your now just a button away from borrowing your stuff</p>
							<Link className="button button-white" to="/home/stream">Discover</Link>
						</div>
			        </div>
		        </ReactCSSTransitionGroup>
	        </div>
	    );
	}
});

module.exports = ThankYou;

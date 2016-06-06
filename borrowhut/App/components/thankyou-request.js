var React  = require('react');
var ReactCSSTransitionGroup = require('react-addons-css-transition-group');
var routerModule = require('react-router');
var Link = routerModule.Link;

var ThankYouRequest = React.createClass({
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
							<h2>Thank you for requesting a product</h2>
							<Link className="button button-white" to="/home/stream">Discover</Link>
						</div>
			        </div>
		        </ReactCSSTransitionGroup>
	        </div>
	    );
	}
});

module.exports = ThankYouRequest;

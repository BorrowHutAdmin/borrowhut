var React  = require('react');
var ReactCSSTransitionGroup = require('react-addons-css-transition-group');
var routerModule = require('react-router');
var Link = routerModule.Link;

var VerifyNumber = React.createClass({
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
			            <div className="steps verifyNumber">
							<Link className="backArrow" to="/setup-twitter-digits"><i className="fa fa-chevron-left"></i></Link>
							<h2>Verify your mobile number</h2>
							<div className="formGroup">
								<div className="group">      
									<input className="inputMaterial" type="password" maxLength="6" required />
									<span className="highlight"></span>
									<span className="bar"></span>
									<label>Enter 6 digit code</label>
								</div>
							</div>
							<Link className="button button-white" to="/thankyou">Create account</Link>
						</div>
			        </div>
		        </ReactCSSTransitionGroup>
	        </div>
	    );
	}
});

module.exports = VerifyNumber;

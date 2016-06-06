var React  = require('react');
var ReactCSSTransitionGroup = require('react-addons-css-transition-group');
var Burgermenu  = require('./burgermenu.js');
var Tabmenu  = require('./tab.js');

var Home = React.createClass({
  render: function() {
  	var scrollHeight = 50;
  	setTimeout(function(){
	  	$('.scrollContent').on('scroll', function(){
			if($('.scrollContent').scrollTop() > scrollHeight) {
				$('#navIcons').fadeOut('fast');
			} else {
				$('#navIcons').fadeIn('fast');
			}
		});
  	}, 100)
    return (
    	<div>
    		<ReactCSSTransitionGroup
	          component="div"
	          transitionName="fade"
	          transitionEnterTimeout={500}
	          transitionLeaveTimeout={500}
	        >
	          {React.cloneElement(this.props.children, {
	            key: this.props.location.pathname
	          })}
	        </ReactCSSTransitionGroup>
		    <Tabmenu />
	    </div>
    );
  }
});

module.exports = Home;

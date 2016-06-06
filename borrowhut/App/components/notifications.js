var React  = require('react');
var routerModule = require('react-router');
var Link = routerModule.Link;

var Feedtab = React.createClass({
	render:function(){
		return (
			<ul className="borrowTab">
				<li><Link to="/notifications/borrow-feed">Borrowing</Link></li>
				<li><Link to="/notifications/lending-feed">Lending</Link></li>
			</ul>
		)
	}
});

var Notifications = React.createClass({
  render: function() {
    return (
    	<div className="notification-container">
		    {this.props.children}
		    <Feedtab />
	    </div>
    );
  }
});

module.exports = Notifications;

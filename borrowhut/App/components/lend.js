var React  = require('react');
var routerModule = require('react-router');
var Link = routerModule.Link;
var Burgermenu  = require('./burgermenu.js');

var Lend = React.createClass({
  render: function() {
  	setTimeout(function(){
  		$('.closeIcon').click(function(){
  			$(this).parents('.reminderNotification').slideUp();
  		});
      $('a.borrowTab').removeClass('active');
      $('a.lendTab').addClass('active');
      $('.borrowTab a').on('click', function(){
        $('a.lendTab').addClass('active');
      });
  	});
    return (
    	<div className="borrowPage">
	    	{ /* <div className="reminderList reminderNotification">
          <div className="closeIcon"><i className="fa fa-times"></i></div>
          <div className="reminderMsgWrapper tbRow">
            <div className="tbCell">
              <div className="circleIcon"><img src="img/notification-icon.png" alt="" /></div>
            </div>
            <div className="reminderMsg tbCell"><p><strong>You have one product to check out.</strong> You have <strong>120 unread</strong> messages!</p></div>
          </div>
        </div> */ }
	    	<div className="scrollContent has-tab has-borrow-tab">
			    {this.props.children}
        </div>
        <ul className="borrowTab">
          <li><Link to="/home/lend/currently-lending">Currently Lending</Link></li>
          <li><Link to="/home/lend/lending-history">History</Link></li>
        </ul>
		  </div>	    
    );
  }
});

module.exports = Lend;
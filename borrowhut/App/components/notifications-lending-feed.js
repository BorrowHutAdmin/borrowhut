var React  = require('react');
var routerModule = require('react-router');
var Link = routerModule.Link;
var Burgermenu  = require('./burgermenu.js');
var Search  = require('./search.js');

var LendingFeed = React.createClass({
  render: function() {
    return (
      <div className="feeds">
        <div id="navIcons">
         
          <Burgermenu />
        </div>
        <div className="scrollContent has-header has-borrow-tab-only">
          <div className="header">
            <h2>Notifications</h2>
            <p>Lending feed.</p>
          </div>
          <div className="tbRow lists">
            <div className="tbCell">
              <div className="circleIcon"><img src="img/user-profile-img.jpg" alt="" /></div>
            </div>
            <div className="reminderMsg tbCell">
              <div>
                <h4><strong>Check-in</strong> reminder</h4>
                <p>Dave is ready to check in the product.</p>
                <div className="bldTxt">
                  <a href="#" className="callToUser">Call Dave</a>
                </div>
                <div className="reminderAlertTime">14:54 PM</div>
              </div>
            </div>
          </div>
          <div className="tbRow lists">
            <div className="tbCell">
              <div className="circleIcon"><img src="img/jhon.jpg" alt="" /></div>
            </div>
            <div className="reminderMsg tbCell">
              <div>
                <h4><strong>Request</strong> Fullfilled</h4>
                <p>John, just accepted your listing.</p>
                <div className="reminderAlertTime">10:10 AM</div>
              </div>
            </div>
          </div>
          <div className="tbRow lists">
            <div className="tbCell">
              <div className="circleIcon"><img src="img/borrowhut-logo.png" alt="" /></div>
            </div>
            <div className="reminderMsg tbCell">
              <div>
                <h4><strong>Earned Borrow Coins</strong></h4>
                <p>You just earned 200 Borrow coins.</p>
                <div className="bldTxt">1200 Borrow coins</div>
                <div className="reminderAlertTime">23/08/2015</div>
              </div>
            </div>
          </div>
          <div className="tbRow lists">
            <div className="tbCell">
              <div className="circleIcon"><img src="img/borrowhut-logo.png" alt="" /></div>
            </div>
            <div className="reminderMsg tbCell">
              <div>
                <h4><strong>Earned Borrow Coins</strong></h4>
                <p>You just earned 200 Borrow coins.</p>
                <div className="bldTxt">1200 Borrow coins</div>
                <div className="reminderAlertTime">23/08/2015</div>
              </div>
            </div>
          </div>
          <div className="tbRow lists">
            <div className="tbCell">
              <div className="circleIcon"><img src="img/alice.jpg" alt="" /></div>
            </div>
            <div className="reminderMsg tbCell">
              <div>
                <h4><strong>Check-in</strong> reminder</h4>
                <p>Alice is ready to check in the product.</p>
                <div className="bldTxt">
                  <a href="#" className="callToUser">Call Dave</a>
                </div>
                <div className="reminderAlertTime">14:54 PM</div>
              </div>
            </div>
          </div>
        </div> 
      </div>
    );
  }
});

module.exports = LendingFeed;

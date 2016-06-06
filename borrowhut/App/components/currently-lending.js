var React  = require('react');
var Burgermenu  = require('./burgermenu.js');
var Search  = require('./search.js');

var CurrentlyLending = React.createClass({
  render: function() {
    return (
      <div>
        <div id="navIcons">
          
          <Burgermenu />
        </div> 
        <div className="reminderList">
            <div className="reminderMsgWrapper tbRow">
              <div className="tbCell"><div className="circleIcon user-icon"><img src="img/user-profile-img.jpg" alt="" /></div></div>
              <div className="reminderMsg tbCell">
                <div>
                  <h4>Check-in reminder</h4>
                  <p>Dave awaits your Lawn Mower.</p>
                  <div className="reminderAlertTime">Today</div>
                </div>
              </div>
            </div>
          </div>
          <div className="reminderList">
            <div className="reminderMsgWrapper tbRow">
              <div className="tbCell"><div className="circleIcon user-icon"><img src="img/user-profile-img.jpg" alt="" /></div></div>
              <div className="reminderMsg tbCell">
                <div>
                  <h4>Check-in reminder</h4>
                  <p>Dave awaits your Lawn Mower.</p>
                  <div className="reminderAlertTime">Tomorrow</div>
                </div>
              </div>
            </div>
          </div>
          <div className="cards">
            <img src="img/iphone6-plus-white.jpg" alt="" />
            <div className="cardBoxContent">
              <div className="circleIcon user-icon"><img src="img/user-profile-img.jpg" alt="" /></div>
              <h2>Iphone 6 plus white</h2>
              <div className="totalCount">ios - White - Apple</div>
              <div className="coinsCount">
                <img src="img/check-in-icon.png" alt="" width="30" />
                8 days to check-in
              </div>
            </div>
          </div>
          <div className="cards">
            <img src="img/nikon-camera.jpg" alt="" />
            <div className="cardBoxContent">
              <div className="circleIcon user-icon"><img src="img/user-profile-img2.jpg" alt="" /></div>
              <h2>Nikon Camera</h2>
              <div className="totalCount">Manual - 20MP - Film</div>
              <div className="coinsCount">
                <img src="img/check-in-icon.png" alt="" width="30" />
                8 days to check-in
              </div>
            </div>
          </div>
          <div className="requests">
            <h5>My Requests</h5>
            <div className="tbRow lists">
            <div className="tbCell">
              <div className="circleIcon"><img src="img/user-profile-img.jpg" alt="" /></div>
            </div>
            <div className="tbCell">
              <h4>You requested a Lawn Mower</h4>
              <div className="status">Status: <strong>Not fulfilled</strong></div>
            </div>
          </div>
          <div className="tbRow lists fulFiled">
            <div className="tbCell">
              <div className="circleIcon"><img src="img/user-profile-img.jpg" alt="" /></div>
            </div>
            <div className="tbCell">
              <h4>You requested a Lawn Mower</h4>
              <div className="status">Status: <strong>Fulfilled</strong></div>
            </div>
          </div>
          <div className="tbRow lists">
            <div className="tbCell">
              <div className="circleIcon"><img src="img/mobile-icon.png" alt="" /></div>
            </div>
            <div className="tbCell">
              <h4>You requested a Lawn Mower</h4>
              <div className="status">Status: <strong>Not fulfilled</strong></div>
            </div>
          </div>
        </div>
      </div>
    );
  }
});

module.exports = CurrentlyLending;

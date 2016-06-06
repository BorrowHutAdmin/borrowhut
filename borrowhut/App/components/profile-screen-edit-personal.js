var React  = require('react');
var routerModule = require('react-router');
var Link = routerModule.Link;
var Burgermenu  = require('./burgermenu.js');

var ProfileScreen = React.createClass({
  render: function() {
    return (
      <div className="borrowPage"> 
        <div id="navIcons" className="iconTypeTwo">
          <Link to="/home/stream" id="back" className="navIcons"><i className="fa fa-chevron-left"></i></Link>
          <Burgermenu />
        </div>
        <div className="scrollContent subContainer">
          <div className="profile-screen">
            <div className="cards">
              <div className="cardBoxContent">
                <div className="circleIcon user-icon"><img src="img/user-profile-img.jpg" alt="" /></div>
                <h2>Jake Stark</h2>
                <div className="abstract">10 Reviews</div>
                <div className="bProcess">
                  <div>
                    <img src="img/borrowhut-logo.png" alt="" width="30" />
                    3 Cycle
                  </div>
                  <div>
                    <img src="img/vector-icon.png" alt="" width="30" />
                    Waterloo, RD
                  </div>
                  <div>
                    <img src="img/phone-icon.png" alt="" width="30" />
                    07987654321
                  </div>
                </div>
              </div>
            </div>

            <div className="cardPad abtBoxContent">
              <h3>About Me</h3>
              <p>Sed us perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsta quae</p>
              <div className="status"><strong>Read more</strong></div>
            </div>

            <div className="cards cardPad abtBoxContent">
              <h3>My History</h3>
              <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem</p>
                <div className="bProcess">
                  <div>
                    <img src="img/check-in-icon-green.png" alt="" width="30" />
                    12 Borrowed
                  </div>
                  <div>
                     <img src="img/check-out-icon-green.png" alt="" width="30" />
                     7 lent
                  </div>
                </div>
            </div>

            <div className="reviewBlock">
              <div className="reminderMsgWrapper tbRow">
                <div className="tbCell"><div className="circleIcon user-icon"><img src="img/user-profile-img.jpg" alt="" /></div></div>
                <div className="reminderMsg tbCell1">
                  <div>
                    <h4>Roger Federer</h4>
                    <div className="reminderAlertTime">28 Aug</div>
                  </div>
                  <p>Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsta quae</p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
});

module.exports = ProfileScreen;

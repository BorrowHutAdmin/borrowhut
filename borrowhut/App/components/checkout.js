var React  = require('react');
var routerModule = require('react-router');
var Link = routerModule.Link;
var Burgermenu  = require('./burgermenu.js');

var libraries = [
  { name: 'Backbone.js', url: 'http://documentcloud.github.io/backbone/'},
  { name: 'AngularJS', url: 'https://angularjs.org/'},
  { name: 'jQuery', url: 'http://jquery.com/'}
];

var Checkout = React.createClass({
  render: function() {
    return (
      <div>
        <div id="navIcons">
          <Link to="/home" id="back" className="navIcons"><i className="fa fa-chevron-left"></i></Link> 
          <Burgermenu />
        </div>
        <div className="scrollContent has-btn has-header">
          <div className="header">
            <h2>Check-out</h2>
            <p>Confirmation screen</p>
          </div>
          <div className="cards">
            <img src="img/iphone6-plus-white.jpg" alt="" />
            <div className="cardBoxContent">
              <div className="circleIcon whiteBg user-icon"><img src="img/jake.jpg" alt="" /></div>
              <h2>Iphone 6 plus white</h2>
              <div className="totalCount">ios - White - Apple</div>
              <div className="bProcess">
                <div className="coinsCount">
                  <img src="img/borrowhut-logo.png" alt="" width="30" />
                  1000 Coins
                </div>
                <div>
                  <img src="img/calendar-icon.png" alt="" width="30" />
                  18/08/2015
                </div>
                <div>
                  <img src="img/phone-icon.png" alt="" width="30" />
                  Call jake
                </div>
              </div>
            </div>
          </div>
          <div className="cards">
            <img src="img/mobile-banner.jpg" alt="" />
          </div>
          <div className="test">
          </div>
          <div className="cardPad">
            <textarea className="textArea" placeholder="Tell us about the product (optional)" rows="5" />
          </div>
          <div className="fixedBottom">
            <a href="#" className="button button-green">Check-out now</a>
          </div>
        </div>
      </div>
    );
  }
});

module.exports = Checkout;

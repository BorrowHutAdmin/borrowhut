var React  = require('react');
var Burgermenu  = require('./burgermenu.js');
var Search  = require('./search.js');

var BorrowHistory = React.createClass({
  render: function() {
    return (
      <div>
        <div id="navIcons">
          
          <Burgermenu />
        </div>
        <div className="cards cardPad">
          <div className="abstract orderNumber">order #B1289</div>
          <h2>Iphone 6 plus white</h2>
          <div className="abstract">ios - White - Apple</div>
          <div className="bProcess">
            <div className="coinsCount">
              <img src="img/borrowhut-logo.png" alt="" width="30" />
              600 Coins
            </div>
            <div>
              <img src="img/calendar-icon.png" alt="" width="30" />
              12/08/2015 - 18/08/2015
            </div>
            <div>
              <img src="img/calendar-icon.png" alt="" width="30" />
              COMPLETED
            </div>
          </div>
        </div>

        <div className="cards cardPad">
          <div className="abstract orderNumber">order #B1290</div>
          <h2>Nikon Camera</h2>
          <div className="abstract">Manual - 20MP - Film</div>
          <div className="bProcess">
            <div className="coinsCount">
              <img src="img/borrowhut-logo.png" alt="" width="30" />
              1000 Coins
            </div>
            <div>
              <img src="img/calendar-icon.png" alt="" width="30" />
              14/08/2015 - 24/08/2015
            </div>
            <div>
              <img src="img/process-icon.png" alt="" width="30" />
              INPROCESS
            </div>
          </div>
        </div>

        <div className="cards cardPad">
          <div className="abstract orderNumber">order #B1295</div>
          <h2>Kindle paperwhite</h2>
          <div className="abstract">Tablet - Black</div>
          <div className="bProcess">
            <div className="coinsCount">
              <img src="img/borrowhut-logo.png" alt="" width="30" />
              600 Coins
            </div>
            <div>
              <img src="img/calendar-icon.png" alt="" width="30" />
              12/08/2015 - 18/08/2015
            </div>
            <div>
              <img src="img/calendar-icon.png" alt="" width="30" />
              INPROCESS
            </div>
          </div>
        </div>

        <div className="cards cardPad">
          <div className="abstract orderNumber">order #B1295</div>
          <h2>Kindle paperwhite</h2>
          <div className="abstract">Tablet - Black</div>
          <div className="bProcess">
            <div className="coinsCount">
              <img src="img/borrowhut-logo.png" alt="" width="30" />
              600 Coins
            </div>
            <div>
              <img src="img/calendar-icon.png" alt="" width="30" />
              12/08/2015 - 18/08/2015
            </div>
            <div>
              <img src="img/calendar-icon.png" alt="" width="30" />
              INPROCESS
            </div>
          </div>
        </div>

      </div>
    );
  }
});

module.exports = BorrowHistory;

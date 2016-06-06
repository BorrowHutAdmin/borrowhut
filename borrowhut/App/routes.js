var React  = require('react');
var routerModule = require('react-router');
var history = require("react-router/lib/HashHistory").history;

var Router = routerModule.Router;
var Route = routerModule.Route;
var Redirect = routerModule.Redirect;

var Getstarted = require('./components/getstarted.js');
var SetupTwitter = require('./components/setup-twitter-digits.js');
var VerifyNumber = require('./components/verify-number.js');
var ThankYou = require('./components/thankyou.js');
var Home = require('./components/home.js');
var Tabmenu = require('./components/tab.js');
var Burgermenu = require('./components/burgermenu.js');
var Search = require('./components/search.js');
var Stream = require('./components/stream.js');
var Borrow = require('./components/borrow.js');
var CurrentlyBorrowing = require('./components/currently-borrowing.js');
var BorrowHistory = require('./components/borrow-history.js');
var Lend = require('./components/lend.js');
var CurrentlyLending = require('./components/currently-borrowing.js');
var LendingHistory = require('./components/lending-history.js');
var Notifications = require('./components/notifications.js');
var BorrowFeed = require('./components/notifications-borrow-feed.js');
var LendingFeed = require('./components/notifications-lending-feed.js');
var ListProduct = require('./components/list-a-product.js');
var ProfileScreen = require('./components/profile-screen-edit-personal.js');
var AddtoCircle = require('./components/add-to-circle.js');
var Checkin = require('./components/checkin.js');
var Checkout = require('./components/checkout.js');
var ProductDetails = require('./components/product-details.js');
var SearchResults = require('./components/search-results.js');
var RequestProduct = require('./components/request-a-product.js');
var ThankYouRequest = require('./components/thankyou-request.js');
var ConfirmationScreen = require('./components/confirmation-screen.js');

module.exports = (
  <Router history={history}>
    <Route>
      <Redirect from='/' to='getstarted' />
      <Route path='getstarted' component={Getstarted} />
      <Route path='setup-twitter-digits' component={SetupTwitter} />
      <Route path='verify-number' component={VerifyNumber} />
      <Route path='thankyou' component={ThankYou} />
      <Route path='home' component={Home}>
      	<Route path='stream' component={Stream} />
        <Route path='borrow' component={Borrow}>
          <Route path='currently-borrowing' component={CurrentlyBorrowing} />
          <Route path='borrow-history' component={BorrowHistory} />  
        </Route>
        <Route path='lend' component={Lend}>
          <Route path='currently-lending' component={CurrentlyLending} />
          <Route path='lending-history' component={LendingHistory} />
        </Route>
      </Route>
      <Route path='notifications' component={Notifications}>
        <Route path='borrow-feed' component={BorrowFeed} />
        <Route path='lending-feed' component={LendingFeed} />
      </Route>
      <Route path='list-a-product' component={ListProduct} />
      <Route path='product-details/:itemId' component={ProductDetails} />
      <Route path='profile-screen-edit-personal' component={ProfileScreen} />
      <Route path='add-to-circle' component={AddtoCircle} />
      <Route path='checkin' component={Checkin} />
      <Route path='checkout' component={Checkout} />
      <Route path='search-results' component={SearchResults} />
      <Route path='request-a-product' component={RequestProduct} />
      <Route path='request-thankyou' component={ThankYouRequest} />
      <Route path='confirmation-screen/:itemId/:bowlid' component={ConfirmationScreen} />
    </Route>
  </Router>
);
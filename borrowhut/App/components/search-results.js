var React  = require('react');
var routerModule = require('react-router');
var Link = routerModule.Link;
var Tabmenu  = require('./tab.js');
var Burgermenu  = require('./burgermenu.js');
var Search  = require('./search.js');
var history = require("react-router/lib/HashHistory").history;


var ResultCard = React.createClass({
  getInitialState:function() {
    return {
      data :[]
    };
  },
  componentDidMount: function () {
    var productName = localStorage.getItem('currentProduct');
    var searchInput = JSON.parse(localStorage.getItem('searchInput'));
    var searchLink ='';
    searchLink = '/productname/'+searchInput.productName+'/location/51.6121,-0.37432/distance/'+searchInput.distance;
    
    if(productName == '') {productName = 'asdf'}
    $.ajax({
      url: 'http://ec2-52-31-84-146.eu-west-1.compute.amazonaws.com:8080/borrowhut/searchProduct'+searchLink,
      dataType: 'json',
      success:function(response, xhr){
        this.setState({ data: response });
        $('#preloader').delay(500).fadeOut();
        this.setState({statusCode:xhr.status});
      }.bind(this),
      error:function(xhr){
        this.setState({statusCode:xhr.status});
      }.bind(this)
    });
    
  },
  render: function() {
    var searchDataResults = localStorage.getItem('searchResults');
   
    if(searchDataResults!="" && searchDataResults != undefined) {
      $('#preloader').delay(500).fadeOut();
        var searchDataResults = JSON.parse(searchDataResults);
        var searchTagsDataInput = localStorage.getItem('inputTags');        
        var count = 0;
        var customers = searchDataResults.map(function(el,i) {
        var productTags = [];
        var k = 0;
        var common = '';
        var searchRes = 0;  
          if(searchTagsDataInput!="" && searchTagsDataInput!="[]")
          {            
            var searchTagsData = JSON.parse(searchTagsDataInput);
            el.FEATURE.map(function(val,key) {
                  for(var item in val) {                     
                      productTags[k]= val[item];
                      k = k + 1;                                       
                  }                                   
                });
             common = $.grep(productTags, function(element) {
                return $.inArray(element, searchTagsData ) !== -1;
            }); 
            searchRes=1; 
           }                
        var results, banner, partyImage;
          if(el.PRD_PHOTO_LINK == "null") {
            banner = <img src="img/default1.jpg" alt="" />;
          } else {
            banner = <img src={el.PRD_PHOTO_LINK} alt="" />;
          }
          if(el.PTY_PHOTO == "null") {
            partyImage = <img src="img/default1.jpg" alt="" />;
          } else {
            partyImage = <img src={el.PTY_PHOTO} alt="" />;
          }
          
          if(searchRes==1 && common.length>0)
          {
            count = count+1;
            results = <div className="cards result-items" key={i}><Link to={'/product-details/'+el.PLS_ID}>{banner}<div className="cardBoxContent"><div className="circleIcon">{partyImage}</div><h2>{el.PRD_NAME}</h2><div>{el.LISTED_PRODUCT_FEATURES}</div><p>{el.PRD_DESCRIPTION}</p><div></div></div></Link></div>;
            return <div>{results}</div>            
          }
          if(searchRes==0)
          {
            results = <div className="cards result-items" key={i}><Link to={'/product-details/'+el.PLS_ID}>{banner}<div className="cardBoxContent"><div className="circleIcon">{partyImage}</div><h2>{el.PRD_NAME}</h2><div>{el.LISTED_PRODUCT_FEATURES}</div><p>{el.PRD_DESCRIPTION}</p><div></div></div></Link></div>;
            return <div>{results}</div> 
          }
            
          /* results = <div className="cards result-items" key={i}><Link to={'/product-details/'+el.PLS_ID}>{banner}<div className="cardBoxContent"><div className="circleIcon">{partyImage}</div><h2>{el.PRD_NAME}</h2><div>{el.LISTED_PRODUCT_FEATURES}</div><p>{el.PRD_DESCRIPTION}</p><div></div></div></Link></div>;
          return <div>{results}</div> */
          });
          return <div><div id="preloader"></div>{ customers }</div>;
          /* if(count>0)
          {
            return <div><div id="preloader"></div>{ customers }</div>;
          }
          else
          {
            $('.search-result-container').fadeIn();
           return  <div>
                <div className="search-result-container">
                  <div className="circleIcon"><img src="img/notification-icon.png" alt="" /></div>
                  <h2>Sorry item not found!</h2>
                  <p>Do not worry. We’ve got you covered. Request a product.</p>
                  <Link className="button button-white" to="/request-a-product">REQUEST PRODUCT</Link>
                </div>
              </div>
          } */
         
    } else {
     $('.search-result-container').fadeIn();
      return  <div>
                <div className="search-result-container">
                  <div className="circleIcon"><img src="img/notification-icon.png" alt="" /></div>
                  <h2>Sorry item not found!</h2>
                  <p>Do not worry. We’ve got you covered. Request a product.</p>
                  <Link className="button button-white" to="/request-a-product">REQUEST PRODUCT</Link>
                </div>
              </div>;
    }
  }
});

var SearchResults = React.createClass({
  render: function() {
    var previousUrl = history.location.pathname; 
        localStorage.setItem('previousUrl', previousUrl);
    return (
      <div>
        <div id="navIcons">
          <Search />
          <Burgermenu />
        </div>
        <div className="scrollContent search-result has-tab">
          <div className="header"></div>
          <ResultCard />
          <Tabmenu />
        </div>
      </div>
    );
  }
});

module.exports = SearchResults;

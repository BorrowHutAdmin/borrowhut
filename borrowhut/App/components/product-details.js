var React  = require('react');
var DateRange = require('react-date-range').DateRange;
var routerModule = require('react-router');
var Link = routerModule.Link;
var Burgermenu  = require('./burgermenu.js');
var validator = require('validator');
var ReactCSSTransitionGroup = require('react-addons-css-transition-group');
var history = require("react-router/lib/HashHistory").history;

var Calendarcomponent = React.createClass({

  handleSelect:function(range){
    this.setState({
      data:range,
      startDate:range.startDate.format('L'),
      endDate:range.endDate.format('L'),
      minDate:"02/13/2016",
      maxDate:"02/18/2016",
      excludeDates: "02/18/2016"
    });
  },
  handleClick:function(range){ 
    var startDateVal = this.state.startDate;
    var endDateVal = this.state.endDate;

    $('#date-select-field').val(startDateVal+' to '+endDateVal);
    //diff = Math.floor((endDateVal.getTime() - startDateVal.getTime()) / 86400000); 
    var startDateArray = startDateVal.split('/');
    var endDateArray = endDateVal.split('/');
    var startDateFormat = new Date(startDateArray[2], startDateArray[0], startDateArray[1]);
    var endDateFormat = new Date(endDateArray[2], endDateArray[0], endDateArray[1]);
    var diff = Math.floor((endDateFormat.getTime() - startDateFormat.getTime()) / 86400000);
    var coinAmount = $('.coinsCount').attr('data-val');
    var totalAmount = coinAmount * diff;
    if(totalAmount!="")
    {
      $('.total-coins').text(totalAmount+" coins");
    }
    $('.set-borrow-dates').removeClass('opened');    
  },
  render:function(){
      
      return (        
        <div className="set-borrow-dates">
          <div id="select-date">Select Borrow date</div>
          <DateRange onInit={this.handleSelect} excludeDates="13/02/2016"   onChange={this.handleSelect} />
          <button id="apply-dates" onClick={this.handleClick}><i className="fa fa-check-circle"></i> Apply Dates</button>
        </div>
      )
  }
});
var ProductDetails = React.createClass({ 
  mixins: [routerModule.Navigation],
  getInitialState:function(){
    return {
      productDetail:{
        productlisting: [{
          PTY_NAME:{},
          CATEGORY:{},
          PTY_PHOTO:{},
          PRD_PHOTOLINK:{},
          FEATURE:{},
          PRD_NAME:{},
          PLS_ID:{},
          PRD_DESC:{}
        }],
        partyid:{}, 
      },
      partyDetails:{
        PARTY:{}
      }
      
    }
  }, 
  componentWillMount:function(){
   $.ajax({
      url:'http://ec2-52-31-84-146.eu-west-1.compute.amazonaws.com:8080/borrowhut/getListedProduct/plsid/'+this.props.params.itemId,
      dataType:'json',
      cache:false,
      success:function(data){
        this.setState({
          productDetail:data          
        });
        var party = this.state.productDetail.partyid
        $.ajax({      
          url:'http://ec2-52-31-84-146.eu-west-1.compute.amazonaws.com:8080/borrowhut/retrieveCustomerDetails/partyid/'+party,
          dataType:'json',
          cache:false,
          success:function(data){ 
            this.setState({
              partyDetails:data
            });       
          }.bind(this)
         })
        
      }.bind(this)
    })
  },
  handle_submit:function(range){
    var self = this;      
    var dateRange = $('#date-select-field').val();
    var dateRangeArray = dateRange.split(' to ');
    var startDate  = dateRangeArray[0];
    var endDate  = dateRangeArray[1];
    var partyId = this.state.productDetail.partyid;
    var plsId = this.state.productDetail.productlisting.PLS_ID;
    var borrowEvent = 'RESERVE';
    var userId = '2'

    if(validator.isNull(dateRange)==true)
    {
      $('.date-range-field').addClass('has-error');
      //alert('Please enter a valid date.');
      return false;
    }
    else
    {
      $('.date-range-field').removeClass('has-error');
    }  
    $.ajax({
      url: 'http://ec2-52-31-84-146.eu-west-1.compute.amazonaws.com:8080/borrowhut/createBorrowTXN/',
      contentType: "application/json; charset=utf-8",
      type: 'POST',
      data: JSON.stringify({PLS_ID:plsId, LENDER_PTY_ID: partyId, BORROWER_PTY_ID:userId,
             START_DATE:startDate, END_DATE: endDate, BORROW_EVENT: borrowEvent}),
      dataType:'json',
      success: function(data) {
        // this.setState({data: data});
        if(data.BOL_ID!="")
        {
          var redirectUrl = 'confirmation-screen/'+this.props.params.itemId+'/'+data.BOL_ID;
                    
          self.transitionTo(redirectUrl);
        }        
      }.bind(this),
      error: function(xhr, status, err) {
        console.log(err);
      }.bind(this)
    });
  },   
  render: function() {
    var productDetail = this.state.productDetail;
    var pdItems = productDetail.productlisting;
    var productBanner;
    var profileImage;
    var telephone;
    var partyDetail = this.state.partyDetails;
    
    
    if(pdItems.PRD_PHOTOLINK == null) {
      productBanner = <img src="img/default1.jpg" alt="" />;
    } else {
      productBanner = <img src={pdItems.PRD_PHOTOLINK} alt="" />;
    }   
    if(partyDetail.PARTY.PTY_PHOTO == null) {
      profileImage = <img src="img/default1.jpg" alt="" />;
    } else {
      profileImage = <img src={partyDetail.PARTY.PTY_PHOTO} alt="" />;
    }
    if(partyDetail.PARTY.PTY_TEL == null) {
      telephone = "";
    } else {
      telephone = "tel:"+partyDetail.PARTY.PTY_TEL;
    }
    
    var distance = 0;
    var Party_lat = partyDetail.PARTY.PTY_LATITUDE;
    var Party_long = partyDetail.PARTY.PTY_LONGITUDE;
    if(Party_lat!=undefined && Party_lat!="" && Party_long!=undefined && Party_long!="")
    {
      // var locationdata = findMyLocation();
      // var Userlocation = locationdata.split(',');
      // var User_lat = Userlocation[0];
      // var User_long = Userlocation[1];
      // distance = finddistance(User_lat, User_long, Party_lat, Party_long);
    }
    
    var productFeatures="";
    if(pdItems.FEATURE!="" && pdItems.FEATURE!=undefined)
    {
      var productFeaturesArray =  pdItems.FEATURE.split(',');
     
      var count = 1;
      productFeaturesArray.forEach(function(product) {        
        if(count % 2 === 0 )
        {
          
          if(productFeatures!="")
          {
            productFeatures = productFeatures +' - '+product;
          }
          else
          {
            productFeatures = product;
          }         
        }
        count = count+1;
      });
    }
    

    /*  Lat{partyDetail.PARTY.PTY_LATITUDE}Long{partyDetail.PARTY.PTY_LONGITUDE} */
    setTimeout(function(){
      $('.date-range-field').click(function(){
        $('.set-borrow-dates').addClass('opened');
      });
    }, 0);
    var previousUrl = localStorage.getItem('previousUrl');
    return (
                <ReactCSSTransitionGroup
              component="div"
              transitionName="transition"
              transitionAppear={true}
              transitionAppearTimeout={500}
            >

      <div> 
        <div id="navIcons">
          <Link to={previousUrl} id="back" className="navIcons"><i className="fa fa-chevron-left"></i></Link>
          <Burgermenu />
        </div>
        <div className="scrollContent">          
          <div className="cards">
            {productBanner}
            <div className="cardBoxContent">
              <div className="circleIcon whiteBg user-icon">{profileImage}</div>
              <h2>{pdItems.PRD_NAME}</h2>
              <div className="totalCount">{productFeatures}</div>
              <div className="bProcess">
                <div className="coinsCount" data-val={pdItems.PLS_FIRST_CIRCLE_PRICE}>
                  <img src="img/borrowhut-logo.png" alt="" width="30" />
                  {pdItems.PLS_FIRST_CIRCLE_PRICE} COINS A DAY
                </div>
                <div>
                  <img src="img/vector-icon.png" alt="" width="30" />
                  {distance} miles 
                </div>
                <div>
                  <a href={telephone}>
                    <img src="img/phone-icon.png" alt="" width="30" />
                    CALL<br/>{partyDetail.PARTY.PTY_NAME}
                  </a>
                </div>
              </div>
            </div>
          </div>
          <Calendarcomponent />
          <div className="cardPad abtBoxContent">
            <h3>About the product</h3>
            <p>{pdItems.PRD_DESC}</p>
          </div>
          <div className="date-range text-center">
            <p><img src="img/cal-icon.png" alt="" width="20" /></p>
            <h4>For how long would you like to borrow?</h4>
            <div className="date-range-field">
              <label>Date range</label>
              <input className="inputMaterial" id="date-select-field" type="text" value="" disabled required />
              <input type="hidden" value="" classNam/>
            </div>
            <Calendarcomponent />
          </div>
          <div className="total-coins text-center"></div>
          <button onClick={this.handle_submit} className="button button-green">Borrow now</button>
        </div>
         
      </div>
      </ReactCSSTransitionGroup>
    );
  }
});

module.exports = ProductDetails;

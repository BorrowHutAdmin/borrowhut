var React  = require('react');
var routerModule = require('react-router');
var Link = routerModule.Link;
var Burgermenu  = require('./burgermenu.js');


var ConfirmationScreen = React.createClass({
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
        partyid:{}
      },
      partyDetails:{
        PARTY:{}
      }, 
      borrowedDetails:{
      }, 
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
   $.ajax({      
          url:'http://ec2-52-31-84-146.eu-west-1.compute.amazonaws.com:8080/borrowhut/getRefData/tablename/borrow_log/filtercond/bol_id='+this.props.params.bowlid,
          dataType:'json',
          type:'GET',
          cache:false,
          success:function(data){ 
            this.setState({
              borrowedDetails:data
            });       
          }.bind(this)
         })
  },
  render: function() {
    var productDetail = this.state.productDetail;
    var pdItems = productDetail.productlisting;
    var productBanner, bowId, totalAmount, startDate, endDate;
    var profileImage;
    var telephone;
    var borrowDetail='';
    var partyDetail = this.state.partyDetails;
    borrowDetail = this.state.borrowedDetails;
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
    var value = borrowDetail[Object.keys(borrowDetail)[0]];
    if(value!=undefined)   
    {
      var startDateVal = value.BOL_START;
      var endDateVal = value.BOL_END;
      var startDateArray = startDateVal.split('/');
      var endDateArray = endDateVal.split('/');
      var startDateFormat = new Date(startDateArray[2], startDateArray[0], startDateArray[1]);
      var endDateFormat = new Date(endDateArray[2], endDateArray[0], endDateArray[1]);
      var diff = Math.floor((endDateFormat.getTime() - startDateFormat.getTime()) / 86400000);      
      var coinAmount = pdItems.PLS_FIRST_CIRCLE_PRICE;
      totalAmount = coinAmount * diff;
      bowId = value.BOL_ID;
      startDate = startDateArray[1]+'/'+startDateArray[0]+'/'+startDateArray[2];
      endDate = endDateArray[1]+'/'+endDateArray[0]+'/'+endDateArray[2];
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
    
    return (
      <div>
        <div id="navIcons">
          <Link to="/home/stream" id="back" className="navIcons"><i className="fa fa-chevron-left"></i></Link> 
          <Burgermenu />
        </div>
        <div className="scrollContent confirmationScreen has-header">
          <div className="header">
            <h2>Thank you!</h2>
            <p>Confirmation screen</p>
          </div>
          <div className="cardPad abtBoxContent">
            <h3>order #{bowId}</h3>
            <p>Thank you for borrowing. Please feel free to contact the lender to fix on the pick up time and place.</p>
            <div className="">
              <div className="coinsCount">
                <img src="img/borrowhut-logo.png" alt="" width="30" />
                {totalAmount} Coins
              </div>
            </div>
          </div>
          <div className="cards">
            {productBanner}
            <div className="cardBoxContent">
              <div className="circleIcon user-icon">{profileImage}</div>
              <h2>{pdItems.PRD_NAME}</h2>
              <div className="totalCount">{productFeatures}</div>
              <div className="bProcess">
                <div className="coinsCount">
                  <img src="img/borrowhut-logo.png" alt="" width="30" />
                  {totalAmount} Coins
                </div>
                <div>
                  <img src="img/calendar-icon.png" alt="" width="30" />
                  {startDate}<br/>-<br/>{endDate}
                </div>
                <div>
                  <a href={telephone}>
                    <img src="img/phone-icon.png" alt="" width="30" />
                    Call<br/>{partyDetail.PARTY.PTY_NAME}
                  </a>
                </div>
              </div>
            </div>
          </div>
          <div className="cardPad abtBoxContent">
            <p>By purchasing you agree to BorrowHut’s <br /><Link className="highlighted" to="/">Terms &amp; Conditions</Link></p>
          </div>
        </div>
      </div>
    );
  }
});

module.exports = ConfirmationScreen;

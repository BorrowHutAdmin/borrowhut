var React  = require('react');
var routerModule = require('react-router');
var Link = routerModule.Link;
var Burgermenu  = require('./burgermenu.js');
var DateRange = require('react-date-range').DateRange;
var validator = require('validator');

var Calendarcomponent = React.createClass({
  handleSelect:function(range){
    this.setState({
      data:range,
      startDate:range.startDate.format('L'),
      endDate:range.endDate.format('L')
    });
  },
  handleClick:function(range){
    var startDateVal = this.state.startDate;
    var endDateVal = this.state.endDate;
    $('#date-select-field').val(startDateVal+' to '+endDateVal);
    $('.set-borrow-dates').removeClass('opened');
  },
    render:function(){
      setTimeout(function(){
        $('.date-range-field').click(function(){
          $('.set-borrow-dates').addClass('opened');
        });
      }, 0);
      return (
        <div className="group">
          <div className="date-range-field">            
            <input className="inputMaterial date-select-field" id="date-select-field" type="text" placeholder="Borrow Date" disabled required />
            <span className="highlight"></span>
            <span className="bar"></span>
            
          </div>
          <div className="set-borrow-dates">
            <div id="select-date">Select Borrow date</div>
            <DateRange onInit={this.handleSelect} onChange={this.handleSelect} />
            <button id="apply-dates" onClick={this.handleClick}><i className="fa fa-check-circle"></i> Apply Dates</button>
          </div>
        </div>
      )
  }
});

var ListProduct = React.createClass({
    mixins: [routerModule.Navigation],
    componentDidMount: function() {
    var productListSession = JSON.parse(localStorage.getItem('productListSession'));
    var tags = [];
    var increment = 0;
    if(productListSession)
    {     
      productListSession.map(function(el,i) {

        tags[increment] = el.PRD_NAME;
        increment = increment +1;
      })
    }
    $("#product-name").autocomplete({source: tags});
  },
  componentWillUnmount: function() {
    $("#product-name").autocomplete('destroy');
  },
  handle_submit:function(range){
    var self = this;      
    var dateRange = $('#date-select-field').val();
    var productName = $('#product-name').val();
    var postCode = $('#post-code').val();
    var productDescription = $('#product-description').val();
    var productId = "";
    var productCategory = "";
    var productListSession = JSON.parse(localStorage.getItem('productListSession'));
    if(productListSession)
    {     
      productListSession.map(function(el,i) {
        
        if(el.PRD_NAME == productName)
        {
          console.log('test')
          productId = el.PRD_ID;
          productCategory = el.CAT_NAME;          
        }
        
      })
    }    
    var dateRangeArray = dateRange.split(' to ');
    var startDate  = dateRangeArray[0];
    var endDate  = dateRangeArray[1];    
    var borrowEvent = 'RESERVE';
    var userId = '2'
    var x=0;

    if(validator.isNull(dateRange)==true)
    {
      $('.date-range-field').parent('.group').addClass('has-error');
      x++;
    }
    else
    {
      $('.date-range-field').parent('.group').removeClass('has-error');
    }
    if(validator.isNull(productName)==true)
    {
      $('#product-name').parent('.group').addClass('has-error');
      x++;
    }
    else
    {
      $('#product-name').parent('.group').removeClass('has-error');
    }
    if(validator.isNull(postCode)==true)
    {
      $('#post-code').parent('.group').addClass('has-error');      
      x++;
    }
    else
    {
      $('#post-code').parent('.group').removeClass('has-error');
    }
    if(validator.isNull(productDescription)==true)
    {
      $('#product-description').addClass('has-error'); 
      x++;
    }     
    else
    {
      $('#product-description').removeClass('has-error'); 
    }
    if(x>0)
    {     
      return false;
    }
    else
    {
      if(productId!=undefined && productId!="")
      {
        $.ajax({
          url: 'http://ec2-52-31-84-146.eu-west-1.compute.amazonaws.com:8080/borrowhut/createRequest/',
          contentType: "application/json; charset=utf-8",
          type: 'POST',
          data: JSON.stringify({PTY_ID:userId, PRD_ID: productId, CAT_NAME:productCategory, PROD_DESC:productDescription,}),
          dataType:'json',
          success: function(data) {
            if(data.result=="success")
            {
              
              self.transitionTo('request-thankyou');
            }
          }.bind(this),
          error: function(xhr, status, err) {
            
          }.bind(this)
        });
      }
      else
      {
        productId = 0;
         $.ajax({
          url: 'http://ec2-52-31-84-146.eu-west-1.compute.amazonaws.com:8080/borrowhut/createRequest/',
          contentType: "application/json; charset=utf-8",
          type: 'POST',
          data: JSON.stringify({PTY_ID:userId, PRD_ID: productId, CAT_NAME:'', PROD_DESC:productDescription,}),
          dataType:'json',
          success: function(data) {
            if(data.result=="success")
            {
              self.transitionTo('request-thankyou');
            }
          }.bind(this),
          error: function(xhr, status, err) {
            
          }.bind(this)
        });
      }
    }
  },
  render: function() {
    return (
      <div> 
        <div id="navIcons" className="iconTypeTwo">
          <Link to="/search-results" id="back" className="navIcons"><i className="fa fa-chevron-left"></i></Link>
          <Burgermenu />
        </div>
        <div className="scrollContent subContainer has-header">
          <div className="header">
            <h2>Request a product</h2>
            <p>Tell us what you’re lookin for.</p>
          </div>
          <div className="listProdBlock">
            <div className="prodFormGroup">
              <div className="group">      
                <input className="inputMaterial" id="product-name" type="text" required />
                <span className="highlight"></span>
                <span className="bar"></span>
                <label>Product Name</label>
              </div>
              <div className="group">      
                <input className="inputMaterial" id="post-code" type="text" required />
                <span className="highlight"></span>
                <span className="bar"></span>
                <label>Post Code</label>
              </div>
              <Calendarcomponent />
              <textarea className="textArea" id="product-description" placeholder="Tell us about the product (optional)" rows="5" />
            </div>
          </div>
          <input  onClick={this.handle_submit} type="submit" value="Request product" className="button button-green" />
        </div>
      </div>
    );
  }
});

module.exports = ListProduct;

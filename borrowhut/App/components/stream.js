var React  = require('react');
var routerModule = require('react-router');
var Link = routerModule.Link;
var Burgermenu  = require('./burgermenu.js');
var Search  = require('./search.js');
var history = require("react-router/lib/HashHistory").history;

// Inspirational front token
var InspirationalFrontToken = React.createClass({
	//{el.CATEGORY}
	render: function(){
		var frontItmes = this.props.data.map(function(el, i){
		var banner;
		if(el.IMAGE_LINK == null) {
			banner = <img src="img/default1.jpg" alt="" width="143" />;
		} else {
			banner = <img src={el.IMAGE_LINK} alt="" width="143" />;
		}
		var previousUrl = history.location.pathname; 
        localStorage.setItem('previousUrl', previousUrl);
		return <div key={i}>
					{banner}
					<h2>{el.TITLE}</h2>
					<div className="totalCount">{el.TOTAL_ITEMS}  items found</div>
		      	</div>;	
		});
	    return <div className="cards cardTypeOne cardParent">{ frontItmes }</div>;
	}
});

// Category front token
var CategoryFrontToken = React.createClass({
	render: function(){
		var frontItmes = this.props.data.map(function(el, i){
			var banner;
			if(el.CAT_IMAGE == null) {
				banner = <img src="img/default1.jpg" alt="" />;
			} else {
				banner = <img src={el.CAT_IMAGE} alt="" />;
			}
			// <div className="circleIcon"><img src={el.ICON} alt="" /></div>
			return <div key={i}>
						{banner}
						<div className="cardBoxContent">
							
			      			<h2>{el.CATEGORY}</h2>
			      			<div className="totalCount">{el.TOTAL_ITEMS} in your area</div>
						</div>
			      	</div>;
		});
	    return <div className="cards cardParent">{ frontItmes }</div>;
	}
});

// Product front token
var ProductFrontToken = React.createClass({
	render: function(){
		setTimeout(function(){
			var swiper = new Swiper('.productCard', {
	  			spaceBetween: 7,
	  			autoHeight:true
	  		});
		}, 500);
		var frontItmes = this.props.data.map(function(el, i){
			var banner;
			var productFeaturesArray = el.LISTED_PRODUCT_FEATURES.split(',');
			var productFeatures="";
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
			 
			if(el.PRD_PHOTO_LINK == null) {
				banner = <img src="img/default1.jpg" alt="" />;
			} else {
				banner = <img src={el.PRD_PHOTO_LINK} alt="" />;
			}
			return <div className="swiper-slide cards" key={i}>
						<Link to={'/product-details/'+el.PLS_ID}>{banner}
						<div className="cardBoxContent">
							<div className="circleIcon"><img src={el.PTY_PHOTO} alt="" /></div>
							<h2>{el.PRD_NAME}</h2>
				  			<div>{productFeatures}</div>
			  			</div></Link>
			      	</div>;  
			      	    	
		});
	    return <div className="productCard swiper-container"><div className="swiper-wrapper">{ frontItmes }</div></div>;
	}
});

// Message card
var MessageCard = React.createClass({
	render: function(){
		var messageCard = this.props.data.map(function (el, i) {
			return  <div className="cards cardTypeMessage" key={i}>
						<div className="circleIcon"><img src={el.ICON} alt="" /></div>
						<h2>{el.COINS} Coins Earned!</h2>
						<p>{el.TEXT}</p>
					</div>;
		});
		return <div>{messageCard}</div>;
	}
});

// Reverse token
var ReverseToken = React.createClass({
	render: function(){
		var reverseItmes = this.props.data.map(function(el, i){
			var banner;
			var profileImage;
			if(el.PRD_PHOTO_LINK == null) {
				banner = <img src="img/default1.jpg" alt="" />;
			} else {
				banner = <img src={el.PRD_PHOTO_LINK} alt="" />;
			}
			if(el.PTY_PHOTO == null) {
				profileImage = <img src="img/default1.jpg" alt="" />;
			} else {
				profileImage = <img src={el.PTY_PHOTO} alt="" />;
			}

			var productFeaturesArray = el.ListedProductFeature.split(',');
			var productFeatures="";
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
			return <div className="swiper-slide cards" key={i}>
						<Link to={'/product-details/'+el.PLS_ID}>{banner}
						<div className="cardBoxContent">
							<div className="circleIcon">{profileImage}</div>
							<h2>{el.PRD_NAME}</h2>
				  			<div>{productFeatures}</div>
			  			</div></Link>
			      	</div>;
		});
	    
	    return <div className="coverSlider swiper-container"><div className="card-close"><i className="fa fa-times"></i></div><div className="swiper-wrapper">{ reverseItmes }</div></div>;			
	}
});

// Stream card
var Streamcards = React.createClass({
  getInitialState:function() {
    return {
      data :[]
    };
  },
  componentDidMount: function () {
  	//var locationdata =  findMyLocation();
  	var locationdata = "51.6121,-0.374325";
    $.ajax({
      url: "http://ec2-52-31-84-146.eu-west-1.compute.amazonaws.com:8080/borrowhut/getStream/partyid/1/userlocation/51.6121,-0.374325",
      dataType: 'json',
      cache: false
    }).done(function(response) {
      this.setState({ data: response });
    }.bind(this));

    $.ajax({
      url: "http://ec2-52-31-84-146.eu-west-1.compute.amazonaws.com:8080/borrowhut/getRefData/tablename/product",
      dataType: 'json',
      cache: false,
      success: function(data) {
        if(data)
        {
        	localStorage.setItem('productListSession', JSON.stringify(data)); 
        } 
            
      }
    })
  },

  render: function() {
  	$('a.lendTab, a.borrowTab').removeClass('active');
  	setTimeout(function(){
  		$('.cardParent').click(function(){
  			$('.cardParent').fadeIn();
  			$('.coverSlider').hide();
	  		$(this).hide();
	  		$(this).next('.coverSlider').fadeIn(function(){
	  			var swiper = new Swiper('.swiper-container', {
		  			spaceBetween: 7,
		  			autoHeight:true
		  		});
	  		});
	  	});
		$('.card-close').on('click', function(){
			$(this).parents('.coverSlider').hide();
			$(this).parents('.coverSlider').prev('.cardParent').fadeIn();
		});
	  	
  	}, 200);
    var cardmain = this.state.data.map(function(el,i) {
		switch(el.UIC_NAME) {
			case "INSPIRATIONAL":
			return  <div className="cardHolder" key={i}>
						<InspirationalFrontToken data={el.CardFronttokens} />
						<ReverseToken data={el.CardReversetokens}/>
					</div>;
			break;
			case "MESSAGE":
			return  <div className="cardHolder" key={i}>
						<MessageCard data={el.CardFronttokens}/>
					</div>;
			break;
			case "CATEGORY":
			return  <div className="cardHolder" key={i}>
						<CategoryFrontToken data={el.CardFronttokens}/>
						<ReverseToken data={el.CardReversetokens}/>
					</div>;
			break;
			case "PRODUCT":
			return  <div className="cardHolder" key={i}>
						<ProductFrontToken data={el.CardFronttokens}/>
					</div>;
			break;
		}
    });

    return <div>{ cardmain }</div>;
  }
});


var Stream = React.createClass({
  render: function() {
    return (
    	<div>
    		<div id="navIcons">
	    		<Search />
		    	<Burgermenu />
	    	</div>
	    	<div className="scrollContent has-tab">
			    <Streamcards />
		    </div>
	    </div>
    );
  }
});

module.exports = Stream;

var React  = require('react');
var routerModule = require('react-router');
var Link = routerModule.Link;
var DateRange = require('react-date-range').DateRange;
var moment = require('moment');

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
  		
	    return (
	      	<div>
	      		<h4>FILTER BY DATE</h4>
				<div className="date-range text-center">
					<div className="date-range-field">
						<label>Date range</label>
						<input className="inputMaterial" id="date-select-field" type="text" value="09/03/2015 to 17/03/2015" disabled required />
					</div>
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
var Volume = React.createClass({
	getInitialState: function(){
		return {

			value: 10,
			start_value:5,
			end_value:100
		};
	},
	handleChange: function(value) {
		console.log(value);
		$('.slider-value').val(value);
		this.setState({
			value: value,
		});

	},
	render: function() {		
		return (
			<div className="rangeSliderContainer">
				<Slider value={this.state.value} orientation="horizontal" onChange={this.handleChange} />
				<div className="start-end-value start-value">{this.state.start_value} miles</div>
				<div className="start-end-value end-value">{this.state.end_value} miles</div>
				
			</div>
		);
	}
});

var TagCard = React.createClass({
  getInitialState:function() {
    return {
      data :[]
    };
  },  
  render: function() {
    var resultTagsData = localStorage.getItem('tags');
    var inputSelectedTagsData = JSON.parse(localStorage.getItem('inputTags'));
    if(resultTagsData!="" && resultTagsData != undefined) {      
      	var resultTagsData = JSON.parse(resultTagsData);
  		var customers = resultTagsData.map(function(el,i) {
        var results, checked;        
        if(jQuery.inArray( el, inputSelectedTagsData) != -1)
        {
        	 return results = <label className="checked"><input name="tags" value={el} type="checkbox" className="checkboxval"  />{el}</label>;
        }   
        else
        {
        	return results = <label><input name="tags" value={el} type="checkbox" className="checkboxval"  />{el}</label>;
        }	         
      	});
        return <div><h4>FILTER BY TAGS</h4><div className="filterTags">{ customers }</div></div>;        
    } else {     
      return <div></div>;
    }
    
  }
});
var Search = React.createClass({
	mixins: [routerModule.Navigation],
	applyClick:function(){		
		var self = this;
		this.setState({productName:$('#searchItem').val()});
		var searchInput = {};
		var tags =[];
		var inputTags =[];
		$('.filterTags .checked').each(function(index) {		   
		   inputTags[index] = $(this).find('.checkboxval').val();
		});		
		localStorage.setItem('inputTags', JSON.stringify(inputTags));
		
		var productName = searchInput['productName'] = $('#searchItem').val();
		
		var distance = searchInput['distance'] = $('.slider-value').val();
		searchInput['tags'] = '';
		localStorage.setItem('currentProduct', $('#searchItem').val());
		var searchDataInput = JSON.parse(localStorage.getItem('searchInput'));
		var searchLink ='';
		//var locationdata =  findMyLocation();
		var locationdata =  "51.6121,-0.374325";
		if(distance=='' || distance==undefined)
    	{
    		distance =10;
    	}
    	
    	searchLink = '/productname/'+productName+'/location/'+locationdata+'/distance/'+distance;
    	var checkProduct ='';
    	var checkDistance ='';

    	
		if(searchDataInput!=null)
		{
			checkProduct = searchDataInput.productName;
			checkDistance = searchDataInput.distance;
		}
    	if(checkProduct!=productName || checkDistance!=distance) 
    	{    		
    		$.ajax({
		      url: 'http://ec2-52-31-84-146.eu-west-1.compute.amazonaws.com:8080/borrowhut/searchProduct'+searchLink,
		      dataType: 'json',
		      success:function(response, xhr){		
		      	if(response!="")
		      	{
		      		var k =0;
	      			localStorage.setItem('searchResults', JSON.stringify(response));
	      			response.map(function(el,i) {
      				 	el.FEATURE.map(function(val,key) {
      					 	for(var item in val) { 
      					 		if(jQuery.inArray( val[item], tags) == -1)
      					 		{
      					 			tags[k]= val[item];
      					 			k = k + 1; 
      					 		}      					 		
      					 	}	      					      						
	      				}); 
	      			});	
	      			localStorage.setItem('tags', JSON.stringify(tags));	

		      	}
		      	localStorage.setItem('searchInput', JSON.stringify(searchInput));	      	
		        self.transitionTo('search-results');
		      },
		      error:function(xhr){
		      	localStorage.setItem('searchInput', JSON.stringify(searchInput));
		      	localStorage.setItem('searchResults', '');		      	
		      	self.transitionTo('search-results');
		      }
	    	});
    	}  	
    	else
    	{
    		self.transitionTo('search-results');
    	}		
		return false;
	},
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
	  $("#searchItem").autocomplete({source: tags});
	},

	componentWillUnmount: function() {
	  $("#searchItem").autocomplete('destroy');
	},
	render:function(){

		setTimeout(function(){
			$('#searchIcon').on('click', function(){
				$('#searchSection').toggleClass('opened');
			});
			$(document).on('click', function(e){
				var $searchContainer = $('#searchContainer, .ui-autocomplete');
				if(!$searchContainer.is(e.target) && $searchContainer.has(e.target).length === 0) {
					$('#searchSection').removeClass('opened');	
				}
			});
			$('.date-range-field').click(function(){
				$('.set-borrow-dates').addClass('opened');
			});
			$(".filterTags input").unbind('click');
			$('.filterTags input').on('click', function(){
				$(this).parent().toggleClass('checked');
			});
			$('#searchBtn').on('click', function(){
				$('#filterArea').addClass('opened');
			});
			$('.filter-button-item').on('click', function(){
				$('#filterArea').removeClass('opened');
			});
			$('#searchItem').keypress(function (e) {
			 	var key = e.which;
		 		if(key == 13)  // the enter key code
			  	{
			    	$('#apply-filters').trigger('click');
			  	}
			}); 

			var minVal = 5,
				maxVal = 50;
			$( "#slider-range-min" ).slider({
		      range: "min",
		      value: 8,
		      min: 5,
		      max: 50,
		      slide:function(event, ui) {
		      	$('.slider-value').val(ui.value);
		      }
		    });
		    $( ".start-value" ).html(minVal+' miles');
		    $( ".end-value" ).html(maxVal+' miles');
		}, 0);
	
		

		return (
			<div id="searchContainer">
				<div id="searchSection">
					<a href="javascript:void(0);" id="searchIcon" className="navIcons"></a>
					<div id="searchArea">
						<div>
							<input id="searchItem" type="text" placeholder="Search for a product" />
							<button id ="searchBtn"></button>
						</div>
					</div>
				</div>
				<div id="filterArea">					
					<h4>Extend Search radius</h4>
					<div className="rangeSliderContainer">
						<div id="slider-range-min"></div>
						<div className="start-end-value start-value"></div>
						<div className="start-end-value end-value"></div>
					</div>
					<input className="slider-value" type="hidden" value="20" />
					<TagCard />					
					<div className="filter-button">
						<button className="filter-button-item fL" id="apply-filters" onClick={this.applyClick}><i className="fa fa-check-circle"></i> Apply filters</button>
						
						<button className="filter-button-item fR"><i className="fa fa-times-circle"></i> Clear filters</button>
					</div>
				</div>
			</div>
		)
	}
});

module.exports = Search;
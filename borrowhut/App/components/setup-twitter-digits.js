var React  = require('react');
var ReactCSSTransitionGroup = require('react-addons-css-transition-group');
var routerModule = require('react-router');
var Link = routerModule.Link;
var Digits = require('react-digits');

var SetupTwitter = React.createClass({
	mixins: [routerModule.Navigation],
	get consumerKey() {
		return 'xRpXGTql2SvjT08hOOAIE2ti1'
	},
	handleLogin(resp) {
		console.info(resp)
	},
	handle_submit:function(range){
		//digitlogin();

		var self = this;
		self.transitionTo('/home/stream'); 
		/* var digit = new DigitsCordova('xRpXGTql2SvjT08hOOAIE2ti1', 'http://www.borrowhut.uk/');
	    digit.open()
	    .successCallback(function(){
	//                     alert(JSON.stringify(loginResponse));
	//                     window.location = '/home/stream';
	                     // var code = loginResponse.code;
	                     //cordova.InAppBrowser.open('http://apache.org', '_blank', 'location=yes');
	                     self.transitionTo('/home/stream');
	                     // localStorage.setItem("code", code);
	                     //alert(code);

	                     // $.ajax({
	                     //            url: "http://192.168.1.49:8080/turnout-0.0.1-SNAPSHOT/signUpInst",
	                     //            contentType: "application/json; charset=utf-8",
	                     //            type: 'POST',
	                     //            data:JSON.stringify({"AMH_ID":"IN", "AMH_CODE":code}),
	                     //            dataType: 'json',
	                     //            success: function(data) {

	                     //                if(data.RESULT) {
	                     //                    //alert('done');
	                     //                    hideSpinner();
	                     //                    location.href = 'http://www.google.com';
	                     //                    //location.href = '/home/stream';
	                     //                }
	                     //            },
	                     //            error: function(){
	                     //               //alert('Already registered');
	                     //            }
	                     //        });


	                     })
	    .failCallback(function(error){
	                  //alert("Please Try again, Instagram Closed Unexpectedly");
	                     })
	    .errorCallback(function(error){
	                  //alert("Please Try again, Instagram Closed Unexpectedlfy");
	                     }); */
	},
	render: function() {
		/*
		<div className="formGroup">
				                <div className="group">      
				                  <input className="inputMaterial" type="text" required />
				                  <span className="highlight"></span>
				                  <span className="bar"></span>
				                  <label>Name</label>
				                </div>
				                <div className="group">      
				                  <input className="inputMaterial" type="text" required />
				                  <span className="highlight"></span>
				                  <span className="bar"></span>
				                  <label>Mobile</label>
				                </div>
				              </div>
		*/
	    return (
	    	<div className="scrollContent intro-screens">
	    		<ReactCSSTransitionGroup
		          component="div"
		          transitionName="transition"
		          transitionAppear={true}
		          transitionAppearTimeout={500}
		        >

			        <div className="text-center" id="signUp">
			          	<div className="steps setTwitter">
				              <Link className="backArrow" to="/getstarted"><i className="fa fa-chevron-left"></i></Link>
				              <h2>Set up Twitter Digits</h2>
				              <br/><br/><br/>
				              <button onClick={this.handle_submit} className="button button-white">SIGN-UP WITH MOBILE</button>
				             
			            </div>
			        </div>
		        </ReactCSSTransitionGroup>
	        </div>
	    );
	}
});

module.exports = SetupTwitter;



(function(window){

	window.DigitsCordova = function DigitsCordova(consumerKey, callbackLink, options){

		var _this = this;


		var defaultOptions = {
			autoFill: true,
			autoProceed: true
		}


		options = defaultOptions;


		this.consumerKey = consumerKey;

		var successCallback = function(){}; //When digits successfully receives an oAuth response
		var failCallback = function(){}; //When user is able to open digits but does not authenticate properly
		var errorCallback = function(){}; //If we something breaks in digits

		var openWindow; //Variable for storing the new window

		var callbacks = {
			successCallback: function(cb){
				if (typeof cb === 'function'){
	            	successCallback = cb;
	          	} else {
	            	throw('successCallback(param) - param is expecting a function');
	          	}
	          	return this;
			},
			failCallback: function(cb){
				if (typeof cb === 'function'){
	            	failCallback = cb;
	          	} else {
	            	throw('failCallback(param) - param is expecting a function');
	          	}
	          	return this;
			},
			errorCallback: function(cb){
				if (typeof cb === 'function'){
	            	errorCallback = cb;
	          	} else {
	            	throw('errorCallback(param) - param is expecting a function');
	          	}
	          	return this;
			}
		}

		/*
		 * Opens the inAppBrowser with digits
		 *
		 * @return {object} - callbacks
		 */
		this.open = function(){
			document.addEventListener("deviceready", function(){
				var succeed = false; //means if we have successfully got a response
				var smsIntercepting;
				//The code below is for mobile only
				if (cordova.InAppBrowser){
					//Open inAppBrowser to the twitter digits site
					
					var openWindow = cordova.InAppBrowser.open('https://www.digits.com/login?consumer_key=' + _this.consumerKey + '&host=' + callbackLink, "_blank", 'location=no');

						if (localStorage.getItem('deviceType') == "Android") {
							window.plugins.spinnerDialog.show(null, null, true);
						}

					//listen to loadstart event which fires off whenever the inAppBrowser starts loading any site
					openWindow.addEventListener('loadstart', function(event){
						var url = event.url;

						if (url.search('specialsauce=') > -1){
							var param = decodeURIComponent(url.split('&specialsauce=')[1]);
			              	if (param && !succeed) {
			                	succeed = true;
			                	openWindow.close();
			                	successCallback(JSON.parse(param).result);
			              	}
						}

						//Long poll the inAppBrowser for a response
						// setInterval(function(){
			   //            	openWindow.executeScript({
			   //                	code : "var res = document.querySelector(\"script[id='callback-data']\").textContent.trim(); if (res) {location.href = (location.href + '&specialsauce=' + res)};"
			   //            	});
			   //          });
					});

					openWindow.addEventListener('exit', function(event) {

						//If we unsuccessfully got a response, this will call the failed Callback
			            if (!succeed){
			              	failCallback('failed');
			            }
			        });

			        openWindow.addEventListener('loaderror', function(event){

			        	//If something did not load properly
			            errorCallback("Could not load page")
			        });
					openWindow.addEventListener('loadstop', function (event) {

						if (options.autoFill) {
							if (localStorage.getItem('deviceType') == "Android") {
								setTimeout(function () {
									window.plugins.spinnerDialog.hide();
								}, 500);
							}
						}
					});
			    	
				} else {
					errorCallback('An error with inAppBrowser has occured, is this plugin installed?');
				}
			}, false);	

			return callbacks;
		}
	}
})(window);






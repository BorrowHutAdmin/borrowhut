var React  = require('react');
var Burgermenu = React.createClass({
	render:function(){
		setTimeout(function(){
			$(document).on('click', '#menuTrigger', function(){
				$('#sideMenu').addClass('opened');
			});
			$('#closeNav').on('click', function(){
				$('#sideMenu').removeClass('opened');
			});
			$(document).on('click', function(e){
				var $menuContainer = $('#sideMenu, #menuTrigger');
				if(!$menuContainer.is(e.target) && $menuContainer.has(e.target).length === 0) {
					$('#sideMenu').removeClass('opened');	
				}
			});
		}, 0);
		
		return (
			<div>
				<a href="javascript:void(0);" id="menuTrigger" className="navIcons"><i className="fa fa-bars"></i></a>
				<div id="sideMenu">
					<div className="mainNavSection">
						<a href="javascript:void(0);" id="closeNav" className="navIcons"></a>
						<div className="userInfo text-right">
							<strong>John Miller</strong> Waterloo, Rd
						</div>
						<div className="flexRow userNotificatins text-center">
							<div className="flexCol"><div className="sm_icons"><img src="img/borrowhut-logo.png" alt="" width="30" /></div>5 unread</div>
							<div className="flexCol">
								<div className="circleIcon"><img src="img/user-profile-img.jpg" alt="" /></div>
							</div>
							<div className="flexCol"><div className="sm_icons"><img src="img/borrowhut-logo.png" alt="" width="30" /></div> 1400 coins</div>
						</div>
						<ul>
							<li><a href="#">Home</a></li>
							<li><a href="#">Circle of Trust</a></li>
							<li><a href="#">Notification</a></li>
							<li><a href="#">Reedem Borrow Coins</a></li>
							<li><a href="#">Settings</a></li>
						</ul>
					</div>
					<ul>
						<li><a href="#">Terms &amp; Conditions</a></li>
						<li><a href="#">Log out</a></li>
					</ul>
				</div>
			</div>
		)
	}
});

module.exports = Burgermenu;
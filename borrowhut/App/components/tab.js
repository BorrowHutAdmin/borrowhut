var React  = require('react');
var routerModule = require('react-router');
var Link = routerModule.Link;

var Tabmenu = React.createClass({
	render:function(){
		return (
			<ul id="tabBar">
				<li><Link to="/home/stream" className="discoverTab">Discover</Link></li>
				<li><Link to="/home/borrow/currently-borrowing" className="borrowTab">Borrow</Link></li>
				<li><Link to="/home/lend/currently-lending" className="lendTab">Lend</Link></li>
			</ul>
		)
	}
});

module.exports = Tabmenu;
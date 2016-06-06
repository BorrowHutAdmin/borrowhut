var React  = require('react');
var routerModule = require('react-router');
var Link = routerModule.Link;
var Burgermenu  = require('./burgermenu.js');

var ListProduct = React.createClass({
  render: function() {
    $(document).on('click', '.filterTags input', function(){
      $(this).parent().toggleClass('checked');
    });
    return (
      <div> 
        <div id="navIcons" className="iconTypeTwo">
          <Link to="/home/stream" id="back" className="navIcons"><i className="fa fa-chevron-left"></i></Link>
          <Burgermenu />
        </div>
        <div className="scrollContent subContainer has-header">
          <div className="header">
            <h2>List a product</h2>
            <p>Upload a product</p>
          </div>
          <div className="listProdBlock">
            <div className="prodFormGroup">
              <div className="group">      
                <input className="inputMaterial" type="text" required />
                <span className="highlight"></span>
                <span className="bar"></span>
                <label>Product name</label>
              </div>
              <h4>Tag</h4>
              <p>Select 3 tags max, that best describes your product. </p>
              <div className="filterTags">
                <label><input type="checkbox" />IPHONE</label>
                <label><input type="checkbox" />White</label>
                <label><input type="checkbox" />BLACK</label>
                <label><input type="checkbox" />64GB</label>
                <label><input type="checkbox" />32GB</label>
                <label><input type="checkbox" />16GB</label>
              </div>
              <div className="group">      
                <input className="inputMaterial" type="text" required />
                <span className="highlight"></span>
                <span className="bar"></span>
                <label>Borrow coins</label>
              </div>
              <div className="group">      
                <input className="inputMaterial" type="text" required />
                <span className="highlight"></span>
                <span className="bar"></span>
                <label>Pincode</label>
              </div>
              <textarea className="textArea" placeholder="Tell us about the product (optional)" rows="5" />
            </div>
          </div>
          <div className="upldProdShots">
            <div className="circleIcon"><img src="img/camera-icon.png" alt="" /></div>
            <h3>Upload product shots.</h3>
            <p>Maximum of 4 shots.</p>
          </div>
          <input type="submit" value="Get started" className="button button-green" />
        </div>
      </div>
    );
  }
});

module.exports = ListProduct;

var React  = require('react');

var AddtoCircle = React.createClass({
  render: function() {
    setTimeout(function(){
      var swiper = new Swiper('.swiper-container', {
          pagination: '.swiper-pagination',
          slidesPerView: 3,
          slidesPerColumn: 3,
          paginationClickable: true,
          spaceBetween: 30
      });
      $(document).on('click', '.addCircleSlide .swiper-slide', function(){
        $(this).toggleClass('selected');
      });
    }, 100);
    return (
      <div>
        <div className="scrollContent add-to-circle has-btn">
          <div className="header">
            <h2>Add to your Circle</h2>
            <p>Add friends, who are already using BorrowHut</p>
          </div>
          <div className="sortDesc text-center">You’ve selected <strong>3 of 82 friends</strong> to join your circle</div>
          <div className="addCircleSlide swiper-container">
            <div className="swiper-wrapper text-center">
                <div className="swiper-slide">
                  <div className="circleImg"><img src="img/perspiciatis.jpg" alt="" /></div>
                  <h5>Perspiciatis</h5>
                  <p>Unde</p>
                </div>
                <div className="swiper-slide">
                  <div className="circleImg"><img src="img/perspiciatis.jpg" alt="" /></div>
                  <h5>Perspiciatis</h5>
                  <p>Unde</p>
                </div>
                <div className="swiper-slide">
                  <div className="circleImg"><img src="img/perspiciatis.jpg" alt="" /></div>
                  <h5>Perspiciatis</h5>
                  <p>Unde</p>
                </div>
                <div className="swiper-slide">
                  <div className="circleImg"><img src="img/perspiciatis.jpg" alt="" /></div>
                  <h5>Perspiciatis</h5>
                  <p>Unde</p>
                </div>
                <div className="swiper-slide">
                  <div className="circleImg"><img src="img/perspiciatis.jpg" alt="" /></div>
                  <h5>Perspiciatis</h5>
                  <p>Unde</p>
                </div>
                <div className="swiper-slide">
                  <div className="circleImg"><img src="img/perspiciatis.jpg" alt="" /></div>
                  <h5>Perspiciatis</h5>
                  <p>Unde</p>
                </div>
                <div className="swiper-slide">
                  <div className="circleImg"><img src="img/perspiciatis.jpg" alt="" /></div>
                  <h5>Perspiciatis</h5>
                  <p>Unde</p>
                </div>
                <div className="swiper-slide">
                  <div className="circleImg"><img src="img/perspiciatis.jpg" alt="" /></div>
                  <h5>Perspiciatis</h5>
                  <p>Unde</p>
                </div>
                <div className="swiper-slide">
                  <div className="circleImg"><img src="img/perspiciatis.jpg" alt="" /></div>
                  <h5>Perspiciatis</h5>
                  <p>Unde</p>
                </div>
                <div className="swiper-slide">
                  <div className="circleImg"><img src="img/perspiciatis.jpg" alt="" /></div>
                  <h5>Perspiciatis</h5>
                  <p>Unde</p>
                </div>
                <div className="swiper-slide">
                  <div className="circleImg"><img src="img/perspiciatis.jpg" alt="" /></div>
                  <h5>Perspiciatis</h5>
                  <p>Unde</p>
                </div>
                <div className="swiper-slide">
                  <div className="circleImg"><img src="img/perspiciatis.jpg" alt="" /></div>
                  <h5>Perspiciatis</h5>
                  <p>Unde</p>
                </div>
            </div>
          </div>
          <div className="fixedBottom">
            <a href="#" className="button button-green">Add to circle</a>
          </div>
        </div>
      </div>
    );
  }
});

module.exports = AddtoCircle;

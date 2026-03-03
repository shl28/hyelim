$(function(){
    let slider = $('.bxslider').bxSlider({
        auto: true,
    });

    $('.slider-basic').slick({
        autoplay: true,
        dots: true,
        arrows: false,
        infinite: true,
        speed: 500,
        slidesToShow: 1,
        slidesToScroll: 1
    });

    $('.mobile_tab').click(function(){
        $('.mobile_nav').addClass('active');
    });

    $('.mobile_nav > ul > li > a').click(function(){
        var k = $(this).next('.sub').css('display');
        // alert(k);

        if(k == 'none') {
            $('.mobile_nav .sub').slideUp(300);
            $(this).next('.sub').slideDown(300);
        } else {
            $(this).next('.sub').slideUp(300);
        }

        return false;
    });

});
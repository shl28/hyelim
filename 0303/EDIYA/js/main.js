$(function(){
    $(window).scroll(function(){
        var sct = $(this).scrollTop();  // 현재 스크롤 위치값(스트롤 양)
        
        $('.s_Top').text(sct);

        if(sct > 10) {
            $('.header_top').addClass('fixed');
            $('.header_middle').addClass('fixed');
            $('nav').addClass('fixed');
        } else {
            $('.header_top').removeClass('fixed');
            $('.header_middle').removeClass('fixed');
            $('nav').removeClass('fixed');
        }
    });
    

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
        if($(this).hasClass('active')) {
            $(this).removeClass('active');
            $(this).find('img').attr('src', 'images/ham.png');
            $(this).css('right', '20px');
            $('.mobile_nav').removeClass('active');
            $('.mobile_nav .sub').css('display', 'none');
        } else {
            $(this).addClass('active');
            $(this).find('img').attr('src', 'images/bar.png');
            $(this).css('right', '250px');
            $('.mobile_nav').addClass('active');
        }
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
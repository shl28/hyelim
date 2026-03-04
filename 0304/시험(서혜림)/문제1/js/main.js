$(function(){
    $(window).resize(function(){
        let window_width = $(this).width();

        if(window_width <= 1024) {

        } else {
            if($('.mobile_nav').hasClass('active')){
                $('.mobile_nav').removeClass('active');
                $('.mobile_nav .sub').hasClass('display', 'none');
            }
        }
    });

    $(window).trigger('resize');

    $('nav > ul').hover(function(){
        $(this).addClass('over');
    }, function(){
        $(this).removeClass('over');
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
            $('.container').addClass('active');
        }
    });

    $('.mobile_nav > ul > li > a').click(function(){
        var k = $(this).next('.sub').css('display');

        if(k === 'none') {
            $('.mobile_nav .sub').slideUp(300);
            $(this).next('.sub').slideDown(300);
        } else {
            $('.mobile_nav .sub').slideUp(300);
        }

        return false;
    });
});
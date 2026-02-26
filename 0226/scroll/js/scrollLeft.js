$(function(){
    $(window).scroll(function(){
        let scl = $(this).scrollLeft();

        $('.container > div').each(function(index){
            if(scl >= $(this).offset().left) {
                $('nav ul li').removeClass('on').eq(index).addClass('on');
            }
        });
    });

    $('nav ul li').click(function(){
        var i = $(this).index();

        var offset_l = $('.container > div').eq(i).offset().left;

        $('html, body').stop().animate({scrollLeft : offset_l}, 1000);

        return false;
    });

    $('.container > div').mousewheel(function(event, d){
        if(d > 0) {
            let preVal = $(this).prev().offset().left;
            $('html, css').stop().animate({scrollLeft : preVal}, 1000);
        } else if(d < 0) {
            let nextVal = $(this).next().offset().left;
            $('html, css').stop().animate({scrollLeft : nextVal}, 1000);
        }
    });
});
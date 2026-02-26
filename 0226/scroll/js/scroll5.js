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

    if(sct >= 450  && sct < 1000) {
        $('.left1').addClass('on');
    } else {
        $('.left1').removeClass('on');
    }

    if(sct >= 1000  && sct <= 1800) {
        $('.right1').addClass('on');
    } else {
        $('.right1').removeClass('on');
    }

    if(sct >= 2500) {
        $('.s4_1').addClass('active');

        setTimeout(function(){
            $('.s4_2').addClass('active');
        }, 400);

        setTimeout(function(){
            $('.s4_3').addClass('active');
        }, 800);
        
        setTimeout(function(){
            $('.s4_4').addClass('active');
        }, 1200);
    } 

    // for(let i = 0; i < $('.container > div').length; i++) {
        // if(sct >= $('.container > div').eq(i).offset().top) {
        //     $('nav ul li').removeClass('on');
        //     $('nav ul li').eq(i).addClass('on');
        // } 
    // }

    $('.container > div').each(function(index){
        if(sct >= $(this).offset().top) {
            $('nav ul li').removeClass('on').eq(index).addClass('on');
        } 
    });

});

$('nav ul li').click(function(){
    var i = $(this).index();

    var offset_t = $('.container > div').eq(i).offset().top;
    // alert(offset_t);

    $('html, body').stop().animate({scrollTop : offset_t}, 1000);

    // $('nav ul li').removeClass('on');
    // $('nav ul li').eq(i).addClass('on');

    return false;
});
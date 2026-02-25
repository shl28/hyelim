$(function(){

    setTimeout(function(){
        $('.slider li .text0').addClass('on');
        $('.slider li .atext0').addClass('on');
    }, 1000);
    // 1초 후 최초 한번 실행

    var bx = $('.slider').bxSlider({
        auto : true,
        controls : false,
        pager : false,
        mode : 'fade',
        pause : 5000,
        autoHover : true,
        onSlideAfter : function(){
            var k = bx.getCurrentSlide();  // 현재 슬라이드 번호
            $('.slider li').find('h2').removeClass('on');
            $('.slider li').find('p').removeClass('on');
            $('.slider li .text' + k).addClass('on');
            $('.slider li .atext' + k).addClass('on');
        }
    });
});
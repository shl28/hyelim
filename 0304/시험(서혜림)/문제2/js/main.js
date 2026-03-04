$(function(){
    $('.tab_link').click(function(){
        let index = $(this).index();

        $('.tab_link').removeClass('active');
        $('.tab_link').eq(index).addClass('active');

        $('.tab_content').removeClass('active');
        $('.tab_content').eq(index).addClass('active');
    });
});
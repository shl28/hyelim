$(function(){
    $('dd').hide();

    $('dt').click(function(){

        if($(this).hasClass('selected')){
            $(this).removeClass('selected');
        } else {
            $('dt').removeClass('selected');
            $(this).addClass('selected');
        }

        let status = $(this).next('dd').css('display');

        if(status === 'none') {
            $('dd').slideUp();
            $(this).next('dd').slideDown();
        } else {
            $('dd').slideUp();
        }
    });
});
$(function(){
    // $('.m_menu li ul').css('display','none');
    // $('.m_menu li:first ul').css('display','block');

    $('.sub').hide();
    // $('.sub').eq(0).show();

    //$('.m_menu li:nth-child(1) ul').css('display','block');
    //$('.m_menu li:nth-child(1) ul').show();
    //$('.m_menu li:eq(0) ul').show();
    //$('.m_menu li:first ul').show();

    // $('.m_menu > li > a').on('click', function(e){
    //     // .m_menu 안의 li 자식인 a 클릭
    //     e.preventDefault();

    //     var status = $(this).next('.sub').css('display');
    //     // 클릭한 다음에 있는 .sub 의 display 상태(none / block)
    //     // alert(status);

    //     if(status === 'none') {
    //         $('.sub').slideUp();  // 모든 sub 닫기

    //         $(this).next('.sub').slideDown(500);
    //     } else {
    //         $('.sub').slideUp();
    //     }
    // });

     $('.m_menu>li>a').on('click',function(e){
        e.preventDefault(); //a 링크 금지
        //.m_menu 안의 li자식 a 태그를 클릭하면
        var status =  $(this).next('.sub').css('display');
        // 클릭한 a 태그 담에 있는 요소중 클래스가 sub인 요쇼의 display 속성값을 가져와서
        //status 저장  none이면 현재 sub가 닫혀있는 상태 block 현재 서브가 열린상태
        //alert(status);
        if(status ==='none'){
            $('.sub').slideUp();
            $(this).next('.sub').slideDown();
        }else{
            $('.sub').slideUp();
        }
     //return false;
    });

});
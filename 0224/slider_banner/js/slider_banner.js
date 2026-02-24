$(function(){
    var visual = $('#brandVisual > ul > li');  // 큰사진
    var button = $('#buttonList > li');  // pager 버튼
    var current = 0; // 현재 사진 (처음- 초기화)
    var id;  // 자동 setInterval 을 기억하는 변수
    // var i; 전역변수로 사용 시  - move(i) 매개변수 안써도됨

    button.on('click', function(){
        var i = $(this).index();   // 현재 클릭한 버튼의 인덱스 번호(지역변수)
        // alert(i);
        button.removeClass('on');
        button.eq(i).addClass('on');

        move(i);

        return false;
    });

    function timer(){
        id = setInterval(function(){
            var n = current + 1;
            if(n === 3) n = 0;

            button.eq(n).trigger('click');
            // 컴퓨터가 1씩 중가하면서 버튼을 강제(trigger)로 클릭
        }, 3000);
    }

    timer();

    function move(i){
        if(current === i) return;  // current 와 클릭한 i 버튼이 같으면 빠져나옴

        var cu = visual.eq(current);
        var ne = visual.eq(i);

        cu.css('left', '0').stop().animate({'left' : '-100%'}, 500);
        ne.css('left', '100%').stop().animate({'left' : '0%'}, 500);

        current = i;
    }
});
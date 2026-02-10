function changeTab(tabId){
    const allTabs = document.querySelectorAll('.tab');
    const allContent = document.querySelectorAll('.tab-content');

    // 자바스크립트 함수 실행할 때 보통 기존 클래스 삭제- 추가 식으로 진행함
    allTabs.forEach(function(a){
        a.classList.remove('active-tab');
    });
    allContent.forEach(function(a){
        a.classList.remove('active-content');
    });

    const selectedTab = document.getElementById('menu' + tabId);
    selectedTab.classList.add('active-tab');

    const selectedContent = document.getElementById(tabId);
    selectedContent.classList.add('active-content');
    
}
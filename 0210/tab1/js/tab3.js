const targetLink = document.querySelectorAll('.tab_menu li a');
const tabContent = document.querySelectorAll('#tabContent > div');

targetLink.forEach(function(link){
    link.addEventListener('click', function(e){
        e.preventDefault();
        let orgTarget = e.target.getAttribute('href');
        let tabTarget = orgTarget.replace('#', '');  // #제거
        
        tabContent.forEach(function(content){
            content.style.display = 'none';
            // 모든 탭 내용 안 보이게
        });

        document.getElementById(tabTarget).style.display = 'block';
        // tabId 찾아서 해당하는 것을 보이게 함

        targetLink.forEach(function(eva){
            eva.classList.remove('active');
        });

        e.target.classList.add('active');
    });
});
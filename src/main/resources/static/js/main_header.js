//회원가입에서 주소검색 버튼 클릭 시, 실행.
function openPost(){
    new daum.Postcode({
        oncomplete: function(data) {
            document.querySelector("#memberAddr").value = data.roadAddress;
        }
    }).open();
}

//회원가입 시, 데이터 유효성 검사
function joinValidate(){
    //1. 데이터 유효성 검사

    //id 입력 여부 체크
    //form 태그 선택
    //form 태그 안의 요소는 name 속성으로 접근이 가능.
    const joinForm = document.querySelector('#joinForm');


    if(joinForm.memberId.value == ''){
        inputInvalidate('#id-error-div','id는 필수 입력입니다.');
        return;
    }
    else if(joinForm.memberId.value.length < 4){
        inputInvalidate('#id-error-div','id는 4자리 이상입니다.');
        return;
    }

    //휴대폰 정규식표현식
    let telRegex = /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/;
    //telRegex.test('010-111-9999'); //true
    //telRegex.test('010-11-55'); //false
    const tel = joinForm.memberTels[0].value+'-'+joinForm.memberTels[1].value+'-'+joinForm.memberTels[2].value;
    if(!telRegex.test(tel)){
        inputInvalidate('#tel-error-div', '연락처가 잘못 입력되었습니다.');
        return;
    }

    //이메일 정규식표현식
    let emailRegex = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/;


    //2. submit 실행
    //form 태그 선택 -> submit() 함수 실행.
    joinForm.submit();
}

//validate 실패 시 메세지 설정
function inputInvalidate(tagid, content){
    document.querySelector(tagid).style.display = 'block';
    document.querySelector(tagid).textContent = content;
}

//회원가입 시, 아이디 중복체크
function selectId(){

    fetch('/member/idFetch', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            'memberId' : document.querySelector('#memberId').value
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }

        //return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        if(data){
            alert('사용 가능한 아이디');
            document.querySelector('#join-btn').disabled = false;
        }else{
            alert('사용 불가능 아이디');
        }
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}
function setDisabled(){
    document.querySelector('#join-btn').disabled = true;
}

//회원 가입 모달창이 닫힐 때 마다 실행되는 이벤트
const joinModal = document.querySelector('#join-modal')
joinModal.addEventListener('hidden.bs.modal', event => {
    document.querySelector('#joinForm').reset();
    document.querySelector('#join-btn').disabled = true; 
})

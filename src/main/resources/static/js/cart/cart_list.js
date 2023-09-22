allPrice();

//제목줄의 체크박스 올 체크
function allCheck(check){
    const checkboxes = document.querySelectorAll('input[type="checkbox"]');
    // checkboxes.forEach(checkbox => {
    //     checkbox.checked = check.checked;
    // });
    if(check.checked){
        checkboxes.forEach(function(checkbox, idx){
            checkbox.checked = true;
        });
    }else{
        checkboxes.forEach(function(checkbox, idx){
            checkbox.checked = false;
        });
    }
    allPrice();
}

//총 가격 세팅
function allPrice(){
    //input 태그 중 name이 check 인 태그에 checked 속성이 있는 태그를 선택
    const checkboxes = document.querySelectorAll('.chk:checked')
    let sum = 0;

    checkboxes.forEach(checkbox => {
        sum += parseInt(checkbox.dataset.totalPrice);
    })

    document.querySelector('.cart-all-price').innerHTML = '<b>￦</b>'+sum.toLocaleString('ko-KR');
}

function deleteCarts(){
    const checkedCart = document.querySelectorAll('.chk:checked');
    
    //선택된 상품이 없을 경우.
    if(checkedCart.length == 0){
        alert('삭제할 상품을 선택');
        return;
    }
    if(confirm('선택된 상품을 삭제?')){
        //모든 CART_CODE를 가져갈 배열 생성
        let cartCodeArr = [];

        //삭제하고자 하는 CART_CODE를 구하기.
        checkedCart.forEach(function(checkbox,idx){
            cartCodeArr[idx] = checkbox.value;
        });

        //선택한 상품 삭제 이동
        location.href=`/cart/deleteCart?cartCodes=${cartCodeArr}`;
    }
}

//장바구니 수량 변경
function updateCartCnt(cartCode, selectedTag, itemPrice){
    const cartCnt = selectedTag.closest('.row').querySelector('input[type="number"]').value 

    fetch('/cart/updateCartCntFetch', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            'cartCode' : cartCode,
            'cartCnt' : cartCnt
        })
    })
    .then((response) => {
        if(!response.ok){
            alert('fetch error!\n컨트롤러로 통신중에 오류가 발생했습니다.');
            return ;
        }

        return response.text(); //컨트롤러에서 return하는 데이터가 없거나 int, String 일 때 사용
        //return response.json(); //나머지 경우에 사용
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        const result = itemPrice * cartCnt;
        selectedTag.closest('td').nextElementSibling.querySelector('.reusult-span').innerHTML = '<b>￦</b>'+result.toLocaleString('ko-KR');
        selectedTag.closest('tr').querySelector('.chk').dataset.totalPrice = result;
        allPrice();
        alert("변경되었습니다.");
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}

function buy(){
    // -- 구매하는 장바구니 품목의 CART_CODE들을 가지고 컨트롤러 이동.
    // 1. 구매 결정 한 품목에 대한 CART_CODE들을 추출
    //    체크된 체크박스의 value 값 추출
    const chks = document.querySelectorAll('.chk:checked');
    
    // 2. CART_CODE 값들을 저장할 수 있는 배열을 생성.
    const cartCodeArr = [];

    for(const chk of chks){
        cartCodeArr.push(chk.value);
    }
    
    location.href=`/buy/insertBuy?cartCodes=${cartCodeArr}`;

}

// /buy/insertBuyFetch 자바 콘솔에 출력
function buy1(){

    //컨트롤러에 전달 될 데이터
    //구매 상품 개수
    const chks = document.querySelectorAll('.chk:checked');
    const len = chks.length;
    const detailList = [];
    const cartCodes = []
    let buyTotalPrice = 0;

    for(let i = 0; i<len; i++){

        const buyPrice = chks[i].dataset.totalPrice;
        const buyCnt = chks[i].closest('tr').querySelector('input[type="number"]').value;
        const itemCode = chks[i].dataset.itemCode;
        const cartCode = chks[i].value;
        buyTotalPrice += parseInt(buyPrice);

        const detailInfo ={
            'itemCode' : itemCode,
            'buyCnt' : buyCnt,
            'buyPrice' : buyPrice,
        }
        
        detailList.push(detailInfo);
        cartCodes.push(cartCode);
    }

    const data = {
        'buyTotalPrice' : buyTotalPrice,
        'detailList' : detailList,
        'cartCodes' : cartCodes
    }


    fetch('/buy/insertBuyFetch', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/json; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: JSON.stringify(data)
    })
    //response 로 넘겨진 데이터를 json 으로 변경해서 보냄.
    //response가 아무것도 없다면, 주석처리

    .then((response) => {
        return response.text(); //나머지 경우에 사용
        //주석처리가 하기 싫다면, response.json 에 text()로 변환
    })
    //fetch 통신 후 실행 영역
    .then((data) => {//data -> controller에서 리턴되는 데이터!
        
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}
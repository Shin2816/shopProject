function goCartList(info, itemCode){
    if(info == null){
        if(confirm('로그인이 필요합니다. \n로그인 하시겠습니까?')){
            location.href='/member/loginForm';
        }
    }else{
        insertCart(itemCode);
    }
}

function insertCart(itemCode){

    fetch('/cart/insertCartFetch', { //요청경로
        method: 'POST',
        cache: 'no-cache',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
        },
        //컨트롤러로 전달할 데이터
        body: new URLSearchParams({
            'itemCode' : itemCode,
            'cartCnt' : document.querySelector('input[type="number"]').value
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
        if(data == 1){
            if(confirm('장바구니에 담겼습니다 \n 장바구니 목록으로 이동할까요?')){
                location.href = '/cart/list';
            }
        }else{
            alert('일시적인 오류가 발생했습니다.');
        }
    })
    //fetch 통신 실패 시 실행 영역
    .catch(err=>{
        alert('fetch error!\nthen 구문에서 오류가 발생했습니다.\n콘솔창을 확인하세요!');
        console.log(err);
    });
}
// function sumPrice(price){
//     const cartCnt = document.querySelector('#cartCnt').value;
//     const chk = /\d/;
//     if(chk.test(cartCnt)){
//         document.querySelector('.item-price-sum').innerHTML = cartCnt * price;
//     }else{
//         alert('숫자만 입력');
//     }
    
// }



//////////////////////////////////////////////////////////////////////////////////////////

//이벤트 영역


//상품 수량이 변경되었을 때 마다, 실행이 되는 이벤트
document.querySelector('input[type=number]').addEventListener('input',function(e){

    if(e.target.value == ''){
        e.target.value = 1;
    }
    //단가
    const itemPrice = e.target.dataset.itemPrice;
    //수량
    //현재 이벤트가 진행되는 태그 자체
    const cnt = e.target.value;
    const result = itemPrice * cnt;
    document.querySelector('input[name="buyPrice"]').value = result;

    document.querySelector('.item-price-sum').textContent = '￦'+result.toLocaleString('ko-KR');
})
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>

const productOption = document.getElementsByClassName('productOption')
const selectedProducts = document.getElementById('selectedProducts')
const totalPriceEl = document.getElementById('totalPriceEl')

let totalPrice = 0;
const selectedProductsArr = [];
for(let i = 0; i < productOption.length; i++) {
  productOption[i].addEventListener('change', e => {
    const selectedProductsNum = e.target.options[e.target.selectedIndex].value
    if(!selectedProductsNum) return;
    if(~selectedProductsArr.indexOf(selectedProductsNum)) return;
    selectedProductsArr.push(selectedProductsNum)
    const strArr = e.target.options[e.target.selectedIndex].textContent.split('/');
    selectedProducts.insertAdjacentHTML("beforeend", "<div>" + strArr[0] + " <strong>" + strArr[1].substring(0, strArr[1].length - 1)
    + "</strong>원 X <input id='" + selectedProductsNum + "' type='number' value=1 onchange='changeAmount(this)' onkeypress='inNumber(event)' style='width:15%;'>개"
    + "<button value='" + selectedProductsNum + "' onclick='deleteEl(this)'>삭제</button>" + "</div>");
    for(let pd of productListObj){
      if(pd.pNum === selectedProductsNum) pd.pAmount = 1;
    }
    calculateTotalPrice()
  })
}
// productListObj
function deleteEl(el){
  const index = selectedProductsArr.indexOf(el.value)
  selectedProductsArr.splice(index, 1)
  for(let pd of productListObj){
    if(pd.pNum === el.value) pd.pAmount = 0;
  }
  const target = el.parentNode
  target.parentNode.removeChild(target)
  calculateTotalPrice()
}

function changeAmount(el){
  if(el.value < 0 || !el.value) el.value = 0;
  for(let pd of productListObj){
    if(pd.pNum === el.id) pd.pAmount = Number(el.value);
  }
  calculateTotalPrice()
}

function calculateTotalPrice(){
  totalPrice = 0;
  for(let pd of productListObj){
    totalPrice += pd.pPrice * pd.pAmount
  }
  totalPriceEl.textContent = totalPrice + "원"
}

function inNumber(event){
  if(event.keyCode<48 || event.keyCode>57){
     event.returnValue=false;
  }
}

calculateTotalPrice()
///////////////////

const addCartBtn = document.getElementById('addCartBtn')


addCartBtn.addEventListener('click', e => {
  const selectedArr = []
  productListObj.forEach(product => {
    if(product.pAmount) selectedArr.push(product)
  });
  if(!selectedArr.length) return alert('상품을 선택하세요.')
  let cart = JSON.parse(localStorage.getItem('cart'))
  if(cart === null) cart = [];
  cart.push(...selectedArr)
  const cartStr = JSON.stringify(cart)
  localStorage.setItem("cart", cartStr)
  const choose = confirm('장바구니에 추가했습니다. 장바구니로 이동하겠습니까?')
  if(choose) location.href = '/team3_ShoppingSite/member/cart.do'
})

const reviewBtn = document.getElementById('reviewBtn')
const userScore = document.getElementById('userScore')
const reviewInput = document.getElementById('reviewInput')

reviewBtn.addEventListener('click', async e => {
  if(!reviewInput.value) return alert('리뷰를 작성하세요')
  const saleNum = location.href.split('=')[1]
  const stars = userScore.options[userScore.selectedIndex].value
  const content = reviewInput.value
  const json = {saleNum, stars, content}
  const data = JSON.stringify(json)

  const res = await fetch('/team3_ShoppingSite/member/review.ajax', {
    method: 'post',
    headers: {
      'Content-Type': 'application/json'
    },
    body: data
  })
  const status = res.status
  if(status === 200){
    const result = await res.text()
    if(result ==='success'){
      location.reload()
    } else {
      alert('리뷰등록 실패. 정보를 올바르게 입력했는지 다시 확인하세요.')
    }
  } else if(status == 404){
    alert('해당 요청을 찾을 수 없습니다.')
  } else {
    alert('서버 에러: 관리자에게 문의하십시오.')
  }
})

</script>

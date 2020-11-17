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
  const cart = JSON.parse(localStorage.getItem('selectedProducts'))
  cart.push(...selectedArr)
  const cartStr = JSON.stringify(cart)
  localStorage.setItem("selectedProducts", cartStr)
  const choose = confirm('장바구니에 추가했습니다. 장바구니로 이동하겠습니까?')
  if(choose) location.href = '/team3_ShoppingSite/member/cart.do'
})


</script>

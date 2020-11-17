<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<script>


const _doc = document;
const _el = (el) => _doc.getElementById(el);
const _creEl = (el) => _doc.createElement(el);
let cart = null;

const cartTable = document.getElementById('cartTable')
const totalPrice = document.getElementById('totalPrice')
let total = 0;

function createProductEl(obj, index){
  const tbody = _creEl('tbody')
  const tr = _creEl('tr')
  for(let i = 0; i < 5; i++){
    const th = _creEl('th')
    th.scope = 'col'
    th.textContent = i === 3 ? '수량' : i === 4 ? '주문금액' : ''
    tr.appendChild(th)
  }
  tbody.appendChild(tr)

  const tr2 = _creEl('tr')
  const th = _creEl('th')
  th.scope = 'row'
  th.style = 'line-heigt: 200px;'
  const input = _creEl('input')
  input.type = 'checkbox'
  input.className = 'checkProduct'
  input.value = index
  input.addEventListener('click', changeallCheckedStatus)
  th.appendChild(input)
  tr2.appendChild(th)

  const td = _creEl('td')
  const div = _creEl('div')
  div.className = 'pd_info_img'
  div.style = 'line-heigt: 200px;'
  const img = _creEl('img')
  img.src = '/team3_ShoppingSite' + obj.imageAddr
  div.appendChild(img)
  td.appendChild(div)
  tr2.appendChild(td)

  const td2 = _creEl('td')
  td2.style = 'line-heigt: 200px;'
  const div2 = _creEl('div')
  div2.textContent = obj.saleTitle
  const div3 = _creEl('div')
  div3.textContent = obj.pName
  td2.appendChild(div2)
  td2.appendChild(div3)
  tr2.appendChild(td2)
  
  const arr = [obj.pAmount, obj.pAmount * obj.pPrice]
  for(let i = 0; i < 2; i++){
    const td3 = _creEl('td')
    td3.style = 'line-heigt: 200px;'
    td3.textContent = arr[i]
    tr2.appendChild(td3)
  }
  tbody.appendChild(tr2)
  return tbody
}

function showInsideCart(){
  // cartTable 모든 자식요소 삭제
  while(cartTable.hasChildNodes()){
    cartTable.removeChild(cartTable.firstChild)
  }
  const tr = createNav()
  cartTable.appendChild(tr)

  cart = JSON.parse(localStorage.getItem('cart'))
  if(cart === null) {
    // 없음
    return
  } 
  
  total = 0;
  for(let i = 0; i < cart.length; i++) {
    const tbody = createProductEl(cart[i], i);
    cartTable.appendChild(tbody)
    total += cart[i].pAmount * cart[i].pPrice
  }
  totalPrice.textContent = "총 결제 금액: " + total + "원"
}

function createNav(){
  const tr = _creEl('tr')
  const td = _creEl('td')
  const input = _creEl('input')
  input.type = 'checkbox'
  input.addEventListener('click', checkAll)
  input.id = "allCheck"
  td.appendChild(input)
  const td2 = _creEl('td')
  td2.textContent = '전체선택'
  const td3 = _creEl('td')
  const td4 = _creEl('td')
  const td5 = _creEl('td')
  const button = _creEl('button')
  button.textContent = '선택삭제'
  button.addEventListener('click', deleteCheckedProduct)
  td5.appendChild(button)
  tr.appendChild(td)
  tr.appendChild(td2)
  tr.appendChild(td3)
  tr.appendChild(td4)
  tr.appendChild(td5)
  return tr
}

let allChecked = false

function checkAll(e){
  const checkProducts = document.getElementsByClassName('checkProduct')
  for(let i = 0; i < checkProducts.length; i++){
    if(checkProducts[i].checked === allChecked) checkProducts[i].checked = !allChecked;
  }
  allChecked = !allChecked
}

function changeallCheckedStatus(e) {
  if(!e.target.checked) {
    allChecked = false
    document.getElementById('allCheck').checked = false
  }
}

function deleteCheckedProduct(e) {
  if(cart === null) return location.reload()
  const checkProducts = document.getElementsByClassName('checkProduct')
  for(let i = checkProducts.length - 1; i >= 0; i--){
    if(checkProducts[i].checked) {
      cart.splice(Number(checkProducts[i].value), 1)
    }
  }
  const cartStr = JSON.stringify(cart)
  localStorage.setItem("cart", cartStr)
  showInsideCart()
}

showInsideCart()


</script>

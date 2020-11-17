<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<script>


const _doc = document;
const _el = (el) => _doc.getElementById(el);
const _creEl = (el) => _doc.createElement(el);
let cart = null;

const puchaseTable = document.getElementById('puchaseTable')
const totalPrice = document.getElementById('totalPrice')
const purchaseBtn = document.getElementById('purchaseBtn')
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
  // puchaseTable 모든 자식요소 삭제
  while(puchaseTable.hasChildNodes()){
    puchaseTable.removeChild(puchaseTable.firstChild)
  }
  const tr = createNav()
  puchaseTable.appendChild(tr)

  cart = JSON.parse(localStorage.getItem('cart'))
  if(cart === null) {
    // 없음
    return
  } 
  
  total = 0;
  for(let i = 0; i < cart.length; i++) {
    const tbody = createProductEl(cart[i], i);
    puchaseTable.appendChild(tbody)
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

purchaseBtn.addEventListener('click', async e => {
  cart = JSON.parse(localStorage.getItem('cart'))
  if(cart === null || !cart.length) return alert('구매할 상품이 없습니다.')
  const choose = confirm('정말 구매하시겠습니까?')
  if(!choose) return
  const data = JSON.stringify(cart)
  const res = await fetch('/team3_ShoppingSite/member/purchase.ajax', {
    method:'post',
    headers: {
      'Content-Type': 'application/json'
    },
    body: data
  })
  const status = res.status
  if(status === 200){
    const result = await res.text()
    if(result ==='success'){
      alert('구매 완료')
      cart = []
      const cartStr = JSON.stringify(cart)
      localStorage.setItem("cart", cartStr)
      location.href = '/team3_ShoppingSite/member/purchasedPage.do'
    } else {
      alert('구매 실패. 정보를 올바르게 입력했는지 다시 확인하세요.')
    }
  } else if(status == 404){
    alert('해당 요청을 찾을 수 없습니다.')
  } else {
    alert('서버 에러: 관리자에게 문의하십시오.')
  }
})

</script>

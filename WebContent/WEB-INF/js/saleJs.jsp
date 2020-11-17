<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>

const productOption = document.getElementsByClassName('productOption')
const selectedProducts = document.getElementById('selectedProducts')

const selectedProductsArr = [];
for(let i = 0; i < productOption.length; i++) {
  productOption[i].addEventListener('change', e => {
    const selectedProductsNum = e.target.options[e.target.selectedIndex].value
    if(!selectedProductsNum) return;
    if(~selectedProductsArr.indexOf(selectedProductsNum)) return;
    selectedProductsArr.push(selectedProductsNum)
    const strArr = e.target.options[e.target.selectedIndex].textContent.split('/');
    selectedProducts.insertAdjacentHTML("beforeend", "<div>" + strArr[0] + " <strong>" + strArr[1].substring(0, strArr[1].length - 1)
    + "</strong>원 X <input id='" + selectedProductsNum + "' type='number' value=1 style='width:15%;'>개" + "<button value='" + selectedProductsNum + "' onclick='deleteEl(this)'>삭제</button>" + "</div>");
  })
}

function deleteEl(el){
  const index = selectedProductsArr.indexOf(el.value)
  selectedProductsArr.splice(index, 1)
  const target = el.parentNode
  target.parentNode.removeChild(target)
}


</script>

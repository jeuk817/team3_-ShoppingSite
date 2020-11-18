<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>

let dropdown = document.getElementsByClassName("dropdown-btn");
let i;

for (i = 0; i < dropdown.length; i++) {
  dropdown[i].addEventListener("click", function() {
  this.classList.toggle("active");
  var dropdownContent = this.nextElementSibling;
  if (dropdownContent.style.display === "block") {
  dropdownContent.style.display = "none";
  } else {
  dropdownContent.style.display = "block";
  }
  });
}

const deleteMemberBtn = document.getElementById('deleteMemberBtn')

deleteMemberBtn.addEventListener('click', async e => {
  const choose = confirm('정말 탈퇴하시겠습니까?')
  if(!choose) return
  
  const pwd = prompt('비밀번호를 입력하시오')
  const data = JSON.stringify({ pwd })
  const res = await fetch('/team3_ShoppingSite/member/deleteMember.ajax', {
    method: 'post',
    headers: {
      'Content-Type': 'application/json'
    },
    body: data
  })
  const status = res.status
  if(status === 200){
    const result = await res.text()
    if(result !=='success') alert('실패. 정보를 올바르게 입력했는지 다시 확인하세요.')
  }
  else if(status == 404)alert('해당 요청을 찾을 수 없습니다.')
  else alert('서버 에러: 관리자에게 문의하십시오.')
  location.href = '/team3_ShoppingSite/mainPage.do'
})


</script>

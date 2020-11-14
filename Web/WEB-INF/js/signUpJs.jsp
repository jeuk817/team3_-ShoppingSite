<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
const btnId = document.getElementById("idCheck");
const pwd = document.getElementById("pwdInput");
const pwdConfirm = document.getElementById("pwdConfirm");
const hiddenPwd = document.getElementById("pwd");
const pwdCheckOk = document.getElementById("pwdCheckOk");
const hiddenId = document.getElementById("id");
const id = document.getElementById("idInput");
const idCheckOk = document.getElementById("idCheckOk");

id.addEventListener('input', e=> {
    hiddenId.value =""
    idCheckOk.textContent ="중복확인하시오"
})

btnId.addEventListener("click", async e => {
    const id = document.getElementById("idInput")
    if(!id.value) return idCheckOk.textContent ="아이디를 입력하시오"

    const res = await fetch('idCheck.ajax?id=' + id.value)
    const status = res.status;
    if(status===200){
        const result = await res.text();
        if(result ==="success"){
            idCheckOk.textContent ="사용할 수 있는 아이디입니다."
            document.getElementById("id").value = id.value;
        }else{
            idCheckOk.textContent ="중복된 아이디가 있습니다."
        }
    }else if(status===404){
        alert("존재하지 않는 요청입니다.")
    }
})

pwd.addEventListener('input', e => {
    hiddenPwd.value = ""
    pwdCheckOk.textContent ="비밀번호를 확인하시오"
})

pwdConfirm.addEventListener("input", e => {
    if(pwd.value === e.target.value){
        pwdCheckOk.textContent ="비밀번호 일치"
        hiddenPwd.value = e.target.value;
    }else{
        pwdCheckOk.textContent ="비밀번호 불일치"
    }
})


</script>

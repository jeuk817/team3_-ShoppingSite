<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>


const signBtn = document.getElementById("signBtn");

signBtn.addEventListener("click", async e => {
    console.log('click')
    const id = document.getElementById("inputEmail");
    const pwd = document.getElementById("inputPassword");
    const json = {id: id.value, pwd: pwd.value};
    const data = JSON.stringify(json);
    //signIn.ajax
    const response = await fetch('signIn.ajax', {
        method: 'post',
        headers:{
            'Content-Type': 'application/json'
        },
        body: data
    })
    const status = response.status
    if(status === 200){
        const result = await response.text(); // success/fail
        if(result === "success"){
            console.log('success')
            location.href = 'mainPage.do'
        } else {
            document.getElementById("signIngMsg").textContent = "가입하지 않은 아이디이거나, 잘못된 비밀번호입니다."
            id.value = ""
            pwd.value = ""
        }
    } else if(status === 404){
        // not found
        alert('존재하지 않는 요청입니다.')
    }
})


</script>

<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<form action="signUp.do">
		userID<input type="text" id="id" name="id"><br>
		P  W  D<input type="password" id="pwd" name="pwd"><br>
		N A M E<input type="text" id="name" name="name"><br>
		H     P<input type="text" id="hp" name="hp"><br>
		ADDRESS<input type="text" id="address" name="address"><br>
		C A R D<input type="text" id="card_num" name="card_num"><br>
		<p><input type="submit"></p>
	</form>	
	<form id="SignIn" name="SignIn" action="signIn.ajax" method="POST">
			ID<input type="text" id="id" name="id"><br>
		PWD<input type="password" id="pwd" name="pwd"><br>
		<input type="submit" id="SignIn">
	</form>

<div id="message" >status</div>

index
<input type="button" id="sendAjax" value="send ajax">
</body>
<!-- 
 <script type="text/javascript">
 fetch("SignIn.do",{
		method : "POST",
	   	body : JSON.stringify({
	           id : this.state.id,
	      	   pwd : this.state.password
	        })
	})
	.then(res => res.json())
	.then(res => {
		console.log(res)
	 	localStorage.setItem('access-token', res.access_token);
	})
  </script>
   -->
<script>


$(document).ready(function() {
	$("#SignIn").click(function() {
		var action = $("#SignIn").attr('action');
		var data = {
			id: $("#id").val(),
			pwd: $("#pw").val()			
		};
		console.log('1st');
		$.ajax({
			type: "POST",
			url: "signIn.ajax",
			body: data,
			
			success: function(response) {
				if(response == 'success') {
					$("#message").html("<p style='color:green;font-weight:bold'>로그인 성공!</p>");
					$("#SignIn").slideUp('slow');
				}
				else {
					$("#message").html("<p style='color:red'>아이디 또는 비밀번호가 잘못되었습니다.</p>");	
				}
			}
		});
		return false;
	});
});

</script>
<script>
/*null오류 너무뜸
$(document).ready(function() {
	$("#SignIn").click(function() {
		var action = $("#SignIn").attr('action');
		var data = {
			id: $("#id").val(),
			pwd: $("#pw").val()			
		};
		console.log('1st');
		$.ajax({
			type: "POST",
			url: "signIn.ajax",
			body: data,
			
			success: function(response) {
				if(response == 'success') {
					$("#message").html("<p style='color:green;font-weight:bold'>로그인 성공!</p>");
					$("#SignIn").slideUp('slow');
				}
				else {
					$("#message").html("<p style='color:red'>아이디 또는 비밀번호가 잘못되었습니다.</p>");	
				}
			}
		});
		return false;
	});
});
*/
</script>
<script>
document.getElementById('sendAjax').addEventListener('click', async e => {
	console.log('click')
	const jsonData = { id: 'jack', pwd: '1234' }
//	const jsonData = { products: [{id: 'jack'}, {id: 'jack2'}] }
//	const jsonData = [{pNum:2, pName:'하양모자', pAmount: 3, pPrice:50000, pSize:'F', saleNum:100, saleTitle:'모자, 셔츠, 바지 팝니다.', imageAddr:'test address1'},
//						{pNum:3, pName:'검은셔츠', pAmount: 2, pPrice:50000, pSize:'F', saleNum:100, saleTitle:'모자, 셔츠, 바지 팝니다.', imageAddr:'test address2'}]
	const data = JSON.stringify(jsonData);
//	const data = 'id=jack';
	const res = await fetch('modify.ajax', {
	    method: 'POST',
	    headers: {
	      'Content-Type': 'application/json'
//	      'Content-Type': 'text/parameters'
	    },
	    redirect: 'follow',
	    referrerPolicy: 'no-referrer',
	    body: data
	  });
	console.log(res.status)
});
</script>
</html>
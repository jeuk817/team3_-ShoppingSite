<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
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
	
	<form action="signIn.do">
			ID<input type="text" id="id" name="id"><br>
		PWD<input type="password" id="pwd" name="pwd"><br>
		<input type="submit" id="SignIn">
	</form>

index
<input type="button" id="sendAjax" value="send ajax">
</body>
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
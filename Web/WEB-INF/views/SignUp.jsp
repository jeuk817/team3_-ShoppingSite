<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Insert title here</title>
  	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/SignUp.css">
    <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    
</head>
<body>
<!-- id: string,
pwd: string,
name: string,
hp: string,
address,
cardNum: string -->
<form action="signUp.do" style="border:1px solid #ccc" method="POST">
	<div class="container">
		<h1>회원가입</h1>
		<p>아래의 내용을 작성해주세요</p>
		<hr>
	
		<div>아이디</div>
		<input type="text" placeholder="Enter ID" id="idInput" required>
		<div id="idCheckOk"></div>
		<input type="button" value="중복확인" id="idCheck">
		  
		<div>비밀번호</div>
		<input type="password" placeholder="Enter Password" id="pwdInput" required>
		<div id="pwdCheckOk"></div>
		
		<div>비밀번호 확인</div>
		<input type="password" placeholder="Repeat Password" id="pwdConfirm"required>
		<div>이름</div>
		<input type="text" placeholder="Name" name="name" required>
		
		<div>핸드폰 번호</div>
		<input type="text" placeholder="PhoneNumber" name="hp" required>
		
		<div>카드번호</div>
		<input type=text placeholder="카드번호 16자리" name="cardNum" required>
		
		<div>주소</div>
		<input type="text" placeholder="Address" name="address" required>
		
		<div class="clearfix">
			<button type="button" class="cancelbtn">Cancel</button>
			<button type="submit" class="signupbtn" >
				Sign Up
			</button>
		</div>
		<input type="hidden" name="pwd" id="pwd" value="">
		<input type="hidden" name="id" id="id" value="">
	</div>
</form>
</body>
<jsp:include page="../js/signUpJs.jsp"></jsp:include>
</html>
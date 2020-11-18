<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/Purchased.css">
    <title>Document</title>
</head>
<body>

<div class="top_container">
<jsp:include page="./common/MyPageTop.jsp"></jsp:include>
</div>
<div class="sideBar_content_container">
<%-- <jsp:include page="./common/MyPageMenu.jsp"></jsp:include> --%>
	<div class="sideBar_container">
		<jsp:include page="./common/MyPageMenu.jsp"></jsp:include>
	</div>
	<c:set var="member" value='<%= request.getAttribute("member") %>'/>
	<div class="container">
		<h1>회원정보</h1>
		<hr>
		<h3>아이디 : ${ member.id }</h3><br>
		<h3>이름 : ${ member.name }</h3><br>
		<h3>핸드폰 번호 : ${ member.hp }</h3><br>
		<h3>카드번호 : ${ member.cardNum }</h3><br>
		<h3>주소 : ${ member.address }</h3>
	</div>
</div>
   
</body>

</html>
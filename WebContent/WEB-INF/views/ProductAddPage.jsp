<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
	
</script>
<title>요기는 상품등록하는 곳</title>
</head>
<body>
<!--  
	<jsp:include page="/WEB-INF/common/TopMenu.jsp"/>
-->	
	<c:choose>
			<c:when test="${id == null}">
				<div>
					<form action="SignIn.do" method="post">
						<button type="button">SignIn</button>						
					</form>
					<form action="SignUp.do" method="post">
						<button type="button">SignUp</button>						
					</form>				
				</div>
			</c:when>
			<c:otherwise>
				<div>
					<a>"${id}"님 환영</a>
					<form action="/seller/productAdd.ajax">
						<h2>상품명</h2><input type="text" name="pName"><br>
						<h2>상품가격</h2><input type="text" name="pPrice"><br>
						<h2>상품크기</h2><input type="text" name="pSize"><br>
						<h2>상품수량</h2><input type="text" name="pAmount"><br>
						<h2>상품설명</h2><input type="text" name="pDesc"><br>
						<input type="submit"/>

					</form>
				</div>
			</c:otherwise>
	</c:choose>

</body>
<script type="text/javascript">

</script>

</body>

</html>
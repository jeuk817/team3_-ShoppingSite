<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/MyPage.css">
</head>
<body>
  
<!-- 사이드메뉴 -->
	<div class="sidenav">
		<a href="#about">회원정보</a><br>
		<a href="InqPostListPage.do">문의글 관리</a><br>
		<a href="/team3_ShoppingSite/member/purchasedPage.do">구매내역</a><br>
		<a href="#" id="deleteMemberBtn">회원탈퇴</a><br>
		<button class="dropdown-btn">판매자메뉴 
			<i class="fa fa-caret-down"></i>
		</button>
		<c:set var="sellerNum" value='<%= session.getAttribute("sellerNum") %>'/>
		<div class="dropdown-container">
			<c:choose>
	       		<c:when test="${ sellerNum == null }">
					<a href="/team3_ShoppingSite/member/registSellerPage.do">판매자 등록</a>
	            </c:when>
	            <c:otherwise>
					<a href="/team3_ShoppingSite/seller/productList.do">상품관리</a>
					<a href="/team3_ShoppingSite/seller/saleListPage.do">판매글관리</a>
	            </c:otherwise>
	        </c:choose>
		</div>
	</div>
    
    
</body>
<jsp:include page="../../js/myPageJs.jsp"></jsp:include>
</html>
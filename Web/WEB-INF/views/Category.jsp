<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/Main.css">
</head>
<body>
<jsp:include page="./common/Top.jsp"></jsp:include>
<button id="btn">aaaaaaa</button>
<div id="categoryContent" class="item-list-container">
	<%-- <div class="item">
		<img src="<%=request.getContextPath() %>/images/19309420_1598777941_image1_M.jpg" alt="">
		<div class="store-title">
			<p>세미브랜드</p>
		</div>
		<div class="product-description">
			<p>세련끝판왕 슬렉스..</p>
		</div>
		<div class="product-price">
			<p>13,900</p>
		</div>
		<div class="hit-number">
			<p>400개 구매중</p>
		</div>
	</div>
	<div class="item">
		<img src="<%=request.getContextPath() %>/images/19309420_1598777941_image1_M.jpg" alt="">
		<div class="store-title">
			<p>세미브랜드</p>
		</div>
		<div class="product-description">
			<p>세련끝판왕 슬렉스..</p>
		</div>
		<div class="product-price">
			<p>13,900</p>
		</div>
		<div class="hit-number">
			<p>400개 구매중</p>
		</div>
	</div>
	<div class="item">
		<img src="<%=request.getContextPath() %>/images/19309420_1598777941_image1_M.jpg" alt="">
		<div class="store-title">
			<p>세미브랜드</p>
		</div>
		<div class="product-description">
			<p>세련끝판왕 슬렉스..</p>
		</div>
		<div class="product-price">
			<p>13,900</p>
		</div>
		<div class="hit-number">
			<p>400개 구매중</p>
		</div>
	</div> --%>

        
</div>

</body>
<jsp:include page="../js/categoryJs.jsp"></jsp:include>
</html>
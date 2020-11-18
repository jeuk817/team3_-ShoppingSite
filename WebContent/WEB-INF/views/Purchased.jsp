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
   <div class="sideBar_container">
      <jsp:include page="./common/MyPageMenu.jsp"></jsp:include>
   </div>
    <div class="purchased_container">
        <div class="purchased_title">
            <h2>구매내역</h2>
        </div>
        <c:set var="purchaseList" value='<%= request.getAttribute("purchaseList") %>'/>
        <table class="table">
            <thead class="thead-dark">
              <tr>
                <th scope="col">
                       상품이미지
                </th>
                <th scope="col">상품명</th>
                <th scope="col">수량</th>
                <th scope="col">가격</th>
                <th scope="col">구매날짜</th>
              </tr>
            </thead>
            <tbody>
	            <c:forEach var="purchase" items="${ purchaseList }" varStatus="index">
					<c:if test="${purchase != null}">
						<tr>
			                <td scope="row">
			                    <img src="/team3_ShoppingSite${ purchase.imageAddr }" alt="상품이미지">
			                </td>
			                <td>${ purchase.pName }</td>
			                <td>${ purchase.oAmount }</td>
			                <td>${ purchase.pPrice * purchase.oAmount }</td>
			                <td>${ purchase.oCreatedAt }</td>
		              	</tr>
					</c:if> 
				</c:forEach>
            </tbody>
          </table>
       </div>
    </div>
</body>
</html>
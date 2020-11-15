<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>구매상품확인 페이지</title>
</head>
<body>
<jsp:include url="top.jsp"/>
<tbody>
<c:set var="list" value="${requestScope.list}"/>
<c:forEach var="list2" items="${list}">
<tr onmouseover="this.style.backgroundColor='gray'" onmouseout="this.style.backgroundColor='white'">
<td align = "center">
<img src="upload/${list2.filename}" style="width:30px, height=30px;">
</td>
<!--
p_num, sel_num, p_name,p_price,p_amount
-->
<td>${list2.P_NUM}</td>
<td>${list2.p_NAME}</td>
<td>${list2.p_PRICE}</td>
<td>${list2.P_AMOUNT}</td>
</tr>
</c:forEach>
</tbody>

</body>
</html>
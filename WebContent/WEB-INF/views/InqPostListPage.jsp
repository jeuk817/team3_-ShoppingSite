<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의글 목록확인 페이지</title>
</head>
<body>
<jsp:include url="top.jsp"/>

<div class="inqlist" max-width="1200px", max-height="960px">
<tbody>
<c:set var="list" value="${requestScope.list}"/>
<c:forEach var="list2" items="${list}">
<tr onmouseover="this.style.backgroundColor='gray'" onmouseout="this.style.backgroundColor='white'">
<!--
Inqnum salenum inqtitle inqcontent inqcreatedat
-->
<td>${list2.inqnum}</td>
<td>${list2.salenum}</td>
<td>${list2.inqtitle}</td>
<td>${list2.inqcreatedat}</td>
<td><input type="button" id="edit"></td>
<td><input type="button" id="delete"></td>
</tr>
</c:forEach>
</tbody>
<div>
</body>
</html>
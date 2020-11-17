<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="/team3_ShoppingSite/css/Cart.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
<body>
<jsp:include page="./common/Top.jsp"></jsp:include>
    <div class="cart_container">
        <div class="productList_title">
            <h1>장바구니</h1>
        </div>
    
        <table id="cartTable" class="table">
            <tr>
                <td><input type="checkbox"></td>
                <td>전체선택</td>
                <td></td>
                <td></td>
                <td><button>선택삭제</button></td>
            </tr>
        </table>

        <h3>총 결제 금액</h3>
        <div class="total_price">
            <div></div>
            <div id="totalPrice">
            </div>
        </div>
        <div class="purchaseBtn">
            <a href="/team3_ShoppingSite/member/purchasePage.do">
	            <button type="button" id="purchaseBtn" class="btn btn-dark btn-lg">구매하기</button>
            </a>
        </div>
       
    </div>
</body>
<jsp:include page="../js/cartJs.jsp"></jsp:include>
</html>
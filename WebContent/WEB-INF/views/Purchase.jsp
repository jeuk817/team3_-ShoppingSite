<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/PurchasePage.css">
    <style>
    .pd_info_img img{
	  width: 100px;
	  height: 100px;
	  line-height: 200px;
	}
    </style>
</head>
<body>
<jsp:include page="./common/Top.jsp"></jsp:include>
    <div class="purchase_title">
        <h1>주문하기</h1>
    </div>

    <div class="pd_info_container">
        <div class="pd_info_title">
            <h2>주문상품정보</h2>
        </div>
        
        <div class="pd_info_table">
            <table id="puchaseTable">
                <!-- <tr>
                    <th>판매자이름</th>
                    <th>쿠폰적용</th>
                    <th>주문금액</th>
                </tr>
                <tr>
                    <td>
                        <div class="pd_info_wrap">
                            <div class="pd_info_img">
                                <img src="/images/다운로드 (1).jpg" alt="">
                            </div>
                            <div class="pd_info_detail">
                                <ul>
                                    <li>상품명</li>
                                    <li>사이즈</li>
                                    <li>수량</li>
                                </ul>
                            </div>
                        </div>
                    </td>
                    <td>쿠폰적용</td>
                    <td>30000</td>
                </tr> -->
            </table>
        </div>
        <div class="total_price">
            <h1 id="totalPrice">총주문금액: 0원</h1>
        </div>

    </div>
	<c:set var="member" value='<%= request.getAttribute("member") %>'/>
    <div class="pd_info_container">
        <div class="pd_info_title">
            <h2>주문자정보</h2>
        </div>
        <table>
            <tr>
                <td>이름</td>
                <td>${ member.name }</td>
            </tr>
            <tr>
                <td>휴대폰</td>
                <td>${ member.hp }</td>
            </tr>
            <tr>
                <td>카드번호</td>
                <td>${ member.cardNum }</td>
            </tr>
        </table>
    </div>

    <div class="pd_info_container">
        <div class="pd_info_title">
            <h2>배송지정보</h2>
        </div>
        <div class="address_info_table">
            <table>
                <tr>
                    <td>수령인</td>
                    <td>${ member.name }</td>
                </tr>

                <tr>
                    <td>핸드폰</td>
                    <td>${ member.hp }</td>
                </tr>

                <tr>
                    <td>배송주소</td>
                    <td>${ member.address }</td>
                </tr>
            </table>
        </div>
    </div>
    
    <div class="pay_btn">
        <a href="">결제하기</a>
    </div>
</body>
<jsp:include page="../js/purchaseJs.jsp"></jsp:include>
</html>
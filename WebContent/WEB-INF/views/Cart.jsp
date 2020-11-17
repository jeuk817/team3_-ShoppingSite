<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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


    
        <table class="table">
            <tr>
                <td><input type="checkbox"></td>
                <td>전체선택</td>
                <td></td>
                <td></td>
                <td><button>선택삭제</button></td>
            </tr>
            
            <tr>
                <th scope="col"></th>
                <th scope="col">판매자이름</th>
                <th scope="col"></th>
                <th scope="col">수량</th>
                <th scope="col">주문금액</th>
            </tr>
            
            <tbody>
            <tr>
                <th scope="row" style="line-height: 200px";><input type="checkbox"></th>
                <td>
                
                    <div class="pd_info_img" style="line-height: 200px";>
                        <img src="/images/다운로드 (1).jpg" alt="">
                    </div>
                </td>
                <td style="line-height: 200px";>제품명</td>
                <td style="line-height: 200px";>수량</td>
                <td style="line-height: 200px";>주문금액</td>
            </tr>

            <tr>
                <th scope="col"></th>
                <th scope="col">판매자이름</th>
                <th scope="col"></th>
                <th scope="col">수량</th>
                <th scope="col">주문금액</th>
            </tr>

            <tr>
                <th scope="row" style="line-height: 200px";><input type="checkbox"></th>
                <td>
                
                    <div class="pd_info_img" style="line-height: 200px";>
                        <img src="/images/다운로드 (1).jpg" alt="">
                    </div>
                </td>
                <td style="line-height: 200px";>제품명</td>
                <td style="line-height: 200px";>수량</td>
                <td style="line-height: 200px";>주문금액</td>
            </tr>
         
            </tbody>
        </table>

        <h3>총 결제예상 금액</h3>

        <div class="total_price">
            <div class="">
                총상품금액 원 + 총 배송비
            </div>
            <div class="">
                총결제 예상금액 000원
            </div>
        </div>
        <div class="purchaseBtn">
            <button type="button" id="purchaseBtn" class="btn btn-dark btn-lg">구매하기</button>
        </div>
       
    </div>
</body>
</html>
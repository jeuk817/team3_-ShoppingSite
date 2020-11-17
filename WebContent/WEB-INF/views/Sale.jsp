<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<link rel="stylesheet" href="/team3_ShoppingSite/css/SalePage.css">
	<jsp:include page="../js/mainJs.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="./common/Top.jsp"></jsp:include>
	<c:set var="selMember" value='<%= request.getAttribute("selMember") %>'/>
	<c:set var="salePost" value='<%= request.getAttribute("salePost") %>'/>
	<c:set var="productList" value='<%= request.getAttribute("productList") %>'/>
	<c:if test="${salePost != null}">
	    <div class="product-detail-container">
	        <div class="product-image">
	        	<img src="/team3_ShoppingSite${ salePost.imageAddrs[0] }" alt="판매글 이미지">
	        </div>
	        <div class="product-info-container">
	            <div class="seller-info">
	                <div class="seller-title">판매자: ${ selMember.name }</div>
	            </div>
	            <div class="detail-title">
	                <h1>${ salePost.saleTitle }</h1>
	            </div>
	            <div class="detail-price">
	                <h1>${ productList[0].pPrice }원</h1>
	            </div>
	            <div class="review-info">
	                <div class="score">
	                    <p>❤❤❤❤❤</p> 
	                </div>
	                <div class="total-reviews">
	                    <a href="#"><p>406개 리뷰 보기</p></a>
	                </div>
	            </div>
	            <div class="goods-options-container">
	                <select class="productOption" name="colorSelect" id="productOption">
	                    <option value="">상품을 선택하세요.</option>
	                    <c:forEach var="product" items="${ productList }" varStatus="index">
							<c:if test="${product != null}">
								<option class="productOption" value="${ product.pNum }">[ ${ product.pName } ] [사이즈: ${ product.pSize } ] / ${ product.pPrice }원</option>
							</c:if> 
						</c:forEach>
	                </select>
	            </div>
	            <div id="selectedProducts">
	            </div>
	            <div class="price-total-container">
	                <div class="total-price-ko">
	                    <p>총상품금액</p>	                </div>
	                <div class="total-price" id="totalPriceEl">
	                	
	                </div>
	            </div>
	            
	            <div class="btn-purchase-container">
	                <ul class="btn-purchase-items">
	                    <li class="btn-purchase-1">
	                        <a href="/team3_ShoppingSite/member/purchasePage.do">
		                        <button>바로구매</button>
	                        </a>
	                    </li>
	                    <li class="btn-purchase-1">
	                        <button id="addCartBtn">장바구니 추가</button>
	                    </li>
	                     <a href="member/writeInquPage.do"><li class="btn-purchase-1">
	                        <button>문의하기</button>
	                    </li></a>
	                </ul>
	            </div>
	        </div>
	    </div>
	    
	    <div class="tab-detail-info">
	        <ul class="tab-details">
	            <li class="tab-details-item">
	                <a href="#">
	                    상품정보
	                </a>
	            </li>
	            <li class="tab-details-item">
	                 <a href="#">
	                    리뷰(423)
	                </a>
	            </li>
	            <li class="tab-details-item">
	                <a href="#">
	                    Q&A(615)
	                </a>
	            </li>
	            <li class="tab-details-item">
	                <a href="#">
	                    주문정보
	                </a>
	            </li>
	        </ul>
	    </div>
	    
	    <div class="product-description">
	        ${ salePost.saleContent }
	        <div class="product-img">
		        <c:forEach var="imageAddr" items="${ salePost.imageAddrs }" varStatus="index">
					<c:if test="${imageAddr != null}">
						<img src="/team3_ShoppingSite${ imageAddr }" alt="상품 이미지" style="width:100%;">
					</c:if> 
				</c:forEach>
	        </div>
	    </div>
	</c:if>

    <!-- review -->
	<form>
		<div id="review" class="review-container">
	
		    <div class="tab-review">
		        리뷰
		    </div>
		    
		    <div class="score-review">
		        <div class="score-letter">
		           <input type ="text" id ="reviewInput" >
		           <ul class="btn-reivew-items">
		               <li class="btn-reivew-1">
		                   <button id="reviewbtn" name="reviewbtn">리뷰등록</button>
		               </li>
		          </ul>
		       </div>
		   </div>
	   </div>
	</form>
    <div>
        <div class="review-content-contaier">
            
            <div class="user-score-id">
                <div class="user-score">
                    ❤❤❤❤❤
                </div>
                <div class="user-id">
                    pg5425
                </div>
    
            </div>
    
            <div class="write--time">
                2020.11.17
            </div>
    
        </div>
        <div class="user-review">
            <p>사진보다 예뻐용 부드럽고 좋습니다.</p>
        </div>

        <div class="review-content-contaier">
            
            <div class="user-score-id">
                <div class="user-score">
                    ❤❤❤❤❤
                </div>
                <div class="user-id">
                    pg5425
                </div>
    
            </div>
    
            <div class="write--time">
                2020.11.17
            </div>
    
        </div>
        <div class="user-review">
            <p>사진보다 예뻐용 부드럽고 좋습니다.</p>
        </div>

        <div class="review-content-contaier">
            
            <div class="user-score-id">
                <div class="user-score">
                    ❤❤❤❤❤
                </div>
                <div class="user-id">
                    pg5425
                </div>
    
            </div>
    
            <div class="write--time">
                2020.11.17
            </div>
    
        </div>
        <div class="user-review">
            <p>사진보다 예뻐용 부드럽고 좋습니다.</p>
        </div>
    </div>

    <!-- review paging -->
    <div class="review-page-container">
        <a href="#"><</a>
        <a href="#">1</a>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
        <a href="#">5</a>
        <a href="#">></a>
    </div>


    <!-- Q&A 테이블 -->

    <div class="qna-list-container">
        <table class="qun-list">
            <colgroup>
                <col style="width: 120px;">
                <col style="width: 90px;">
                <col>
                <col style="width: 120px;">
                <col style="width: 120px;">
            </colgroup>
            <thead>
                <tr class = "qna-list-topBar"> 
                    <th><h3>분류</h3></th>
                    <th><h3>처리상태</h3></th>
                    <th style ="text-align: center;"><h3>내용</h3></th>
                    <th><h3>작성자</h3></th>
                    <th><h3>작성일</h3></th>
                </tr>
            </thead>
            <tbody>
                <tr class = "qna-list-bottom">
                    <td><h3>상품문의</h3></td>
                    <td><h3>답변완료</h3></td>
                    <td class="qna-content">
                        <div class="wrap">
                            <i class="fas fa-lock"></i>
                            <h3>비밀글입니다.</h3>
                        </div>
                    </td>
                    <td><h3>jjj***</h3></td>
                    <td><h3>2020.09.19</h3></td>
                </tr>

                <tr>
                    <td><h3>상품문의</h3></td>
                    <td><h3>답변완료</h3></td>
                    <td class="qna-content">
                        <div class="wrap">
                            <i class="fas fa-lock"></i>
                            <h3>비밀글입니다.</h3>
                        </div>
                    </td>
                    <td><h3>jjj***</h3></td>
                    <td><h3>2020.09.19</h3></td>
                </tr>

                <tr>
                    <td><h3>상품문의</h3></td>
                    <td><h3>답변완료</h3></td>
                    <td class="qna-content">
                        <div class="wrap">
                            <i class="fas fa-lock"></i>
                            <h3>비밀글입니다.</h3>
                        </div>
                    </td>
                    <td><h3>jjj***</h3></td>
                    <td><h3>2020.09.19</h3></td>
                </tr>
            </tbody>
        </table>
    </div>

    <!-- qna paging -->
    <div class="qna-page-container">
        <a href="#"><</a>
        <a href="#">1</a>
        <a href="#">2</a>
        <a href="#">3</a>
        <a href="#">4</a>
        <a href="#">5</a>
        <a href="#">></a>
    </div>
</body>
<script>
	const productListObj = []
    //pName:string,
    //pSize:string,
    //saleTitle:string,
    //imageAddr:string
    
</script>
<c:forEach var="product" items="${ productList }" varStatus="index">
	<c:if test="${product != null}">
		<script>
			productListObj.push({
				pNum: ${product.pNum} +"",
				pPrice: ${product.pPrice},
				pAmount: 0,
			    saleNum: ${ salePost.saleNum },
			    pName: '<c:out value="${ product.pName }" />',
			    pSize: '<c:out value="${ product.pSize }" />',
			    saleTitle: '<c:out value="${ salePost.saleTitle }" />',
			    imageAddr: '<c:out value="${ salePost.imageAddrs[0] }" />'
			})
		</script>
	</c:if> 
</c:forEach>
<jsp:include page="../js/saleJs.jsp"></jsp:include>
</html>

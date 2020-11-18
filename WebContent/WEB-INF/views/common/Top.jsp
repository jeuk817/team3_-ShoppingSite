<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<link rel="stylesheet" href="/team3_ShoppingSite/css/Top.css">
<!-- 배너 -->
<div class="banner">
    <img src="/team3_ShoppingSite/images/bannerImage_199507_1602477265.jpg" alt="배너이미지">
</div>
	
<div class="topnav-container">
    <div class="logo">
        <a href="/team3_ShoppingSite/mainPage.do"><img src="/team3_ShoppingSite/images/logo@3x.png"></a>
    </div>
</div>

   <!-- 슬라이드쇼 -->
<div class="slideshow-container">
    <div class="mySlides fade">
      <div class="numbertext">1 / 3</div>
      <img src="/team3_ShoppingSite/images/img_mountains_wide.jpg" style="width:100%">
      <div class="text">Caption Text</div>
    </div>
  
    <div class="mySlides fade">
      <div class="numbertext">2 / 3</div>
      <img src="/team3_ShoppingSite/images/img_nature_wide.jpg" style="width:100%">
      <div class="text">Caption Two</div>
    </div>
  
    <div class="mySlides fade">
      <div class="numbertext">3 / 3</div>
      <img src="/team3_ShoppingSite/images/img_snow_wide.jpg" style="width:100%">
      <div class="text">Caption Three</div>
    </div>
  
    <!-- Next and previous buttons -->
    <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
    <a class="next" onclick="plusSlides(1)">&#10095;</a>
</div>
 
<!-- The dots/circles -->
<div style="text-align:center">
  <span class="dot" onclick="currentSlide(1)"></span>
  <span class="dot" onclick="currentSlide(2)"></span>
  <span class="dot" onclick="currentSlide(3)"></span>
</div>

   <!-- category menu -->
<c:set var="id" value='<%= (String)session.getAttribute("memberId") %>'/>
<div class="category-container">
    <ul class="category-menu">
        <li class="category-menu-item"><a href ="/team3_ShoppingSite/mainPage.do">HOME</a></li>
        
        <li class="category-menu-item"><a href ="/team3_ShoppingSite/category.do?categoryNum=30">TOP</a></li>
        <li class="category-menu-item"><a href ="/team3_ShoppingSite/category.do?categoryNum=31">PANTS</a></li>
        <li class="category-menu-item"><a href ="/team3_ShoppingSite/category.do?categoryNum=32">OUTER</a></li>
        <li class="category-menu-item"><a href ="/team3_ShoppingSite/category.do?categoryNum=33">ETC</a></li>
        <li class="category-menu-item"><a href="/team3_ShoppingSite/member/cart.do">CART</a></li>
        <li class="category-menu-item"><a href ="/team3_ShoppingSite/member/myPage.do">MYPAGE</a></li>
        <li class="category-menu-item">
        <c:choose>
       		<c:when test="${id == null}">
            	<a href="/team3_ShoppingSite/signInPage.do">SIGN IN</a></li>
            </c:when>
            <c:otherwise>
            	<a href="/team3_ShoppingSite/signOut.do">SIGN OUT</a></li>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
   
   
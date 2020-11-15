<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>
</head>
<body>
<jsp:include url="top.jsp"/>
<tbody>

<form id="info" name="info" action="memberEdit.ajax">
  <tbody>
  <td><input type="text" id="id' name="id" read-only value='${"memberid"}'></td>
  <td><input type="password" id="pwd" name="pwd" value='${"pwd"}'></td>
  <td><input type="password" id="checkpwd" name="checkpwd" value="비밀번호확인입력"></td>
  <td><input type="text" id="username" name="username" value='${"name"}'></td>
  <td><input type="text" id="hp" name="hp" value='${"hp"}'></td>
  <td><input type="text" id="address" name="address" value='${"address"}'></td>
  <td><input type="text" id="cardnum" name="cardnum" value='${"card_num"}'</td>
  </tbody>
  <button id="btn" name="btn">수정</button>
  <div id="message"></div>
<form>

</body>
<script type="text/javascript">
$(document).ready(function() {
	
	$("#btn").click(function() {
		var form_data = {
			id : $("#id").val(),
			pwd : $("#pwd").val(),
			name : $("#username").val(),
			hp : $("#hp").val(),
			address : $("#address").val(),
			card_num : $("#cardnum").val(),
			is_ajax: 1
		};
		$.ajax({
			type: "POST",
			url: "memberEdit.ajax",
			data: form_data,
			success: function(response) {
				if(response == 'success') {
					$("#message").html("<p style='color:green;font-weight:bold'>수정성공</p>");
					$("#form1").slideUp('slow');
				}
				else {
					$("#message").html("<p style='color:red'수정실패</p>");	
				}
			}
		});
		return false;
	});
});
</script

</html>
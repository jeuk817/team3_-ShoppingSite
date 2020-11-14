<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>WriteInquiry</title>
</head>
<body>
<form action="/member/writeInqu.ajax" method="post">
write : <input type="text" id="name" name="name"><br>
title : <input type="text" name="title"><br>
    Private : <input type="checkbox" id="PrivateCheck" name="Private" value="checked"><br>

</form>

</body>

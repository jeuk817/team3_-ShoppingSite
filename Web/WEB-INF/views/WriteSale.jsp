<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

		write: <input type="text" id="name" name="name"><br>
		title: <input type="text" name="title"><br>
			   <input type="hidden" name="hid" value="hello"><br>
		file1 : <input type="file" id="file1" name="filename1"><br>	   
		file2 : <input type="file" name="filename2"><br>
				<textarea rows="10" cols="10" name="content"></textarea>
				<input type="button" id="fileUploadBtn" value="파일 업로드">

</body>
<script>
document.getElementById("fileUploadBtn").addEventListener('click', async e => {
	console.log('click');
	const name = document.getElementById("name");
	//const inputs = document.getElementById("file1");
	const inputs = document.querySelectorAll('input[type="file"]');
	//const inputs = document.querySelector('#file1');
	const data = new FormData();
	console.log(inputs[0].files[0].name)
	console.log(inputs[1].files[0].name)
	data.append('file', inputs[0].files[0], inputs[0].files[0].name);
	data.append('file', inputs[1].files[0], inputs[1].files[0].name);
	data.append('categoryNum', 1234)
	data.append('title', "title value")
	data.append('content', "content values")
	data.append('pNums', [1,2,3])
	const response = await fetch('writeSale.ajax', {
		method: 'post',
		headers: {
		      //'Content-Type': 'multipart/form-data'
		    },
		body: data
	});
	const text = await response.text();
	console.log(text)
	console.log(response.status)
});

</script>
</html>
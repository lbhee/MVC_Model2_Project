<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


	<form action="login.go" method="post">
		<label for="EMAIL">EMAIL&nbsp;&nbsp;&nbsp;</label>
		<input type="text" placeholder="email을 입력하세요" name="EMAIL" id="EMAIL"><br>
		<label for="PWD">비밀번호</label>
		<input type="password" placeholder="비밀번호를 입력하세요" name="PWD" id="PWD"><br>	
		<input type="submit" value="로그인">
		<input type="submit" value="회원가입">
	</form>


</body>
</html>
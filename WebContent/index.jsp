<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>首页</title>
</head>
<body>
    <h2>欢迎</h2>
    <form name="form1" method="post" action="login">
        用户 <input name="userName" type="text"><br>
        密码 <input name="password" type="password"><br>
        <input type="reset" value="取消">
        <input type="submit" value="登录">
    </form>
    <a href="register.jsp">注册</a>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<script>
    function validate() {
        var reason = "";
        reason += validateUsername(document.userForm.userName);
        reason += validateAge(document.userForm.age);
        if (reason != "") {
            alert("输入有误:\n + reason");
            return false;
        } else {
            return true;
        }
    }
    function validateUsername(fld) {
        var error = "";
        if (fld.value == null || fld.value == "") {
            error = "用户名不能为空\n";
        }
        return error;
    }
    function validateAge(fld) {
        var error = "";
        if (isNaN(fld.value)) {
            error = "年龄必须为数!\n";
        }
        else if (parseInt(fld.value) < 1 || parseInt(fld.value) > 100) {
            error = "年龄必须为1-100之间的数!\n";
        }
        return error;
    }
</script>

<head>
    <title>注册</title>
</head>

<body>
    <form onsubmit="return validateform();" name="userForm" methos="post" action="register">
        用户名<input name="userName" type="text" size="14"><br>
        密码<input name="password" type="password" size="12"><br>
        性别<input name="gender" type="radio" value="男" checked>男
        <input name="gender" type="radio" value="女">女<br>
        Email <input type="text" name="email"><br>
        年龄 <input name="age" type="text" size="8" value="0"><br>
        <input type="reset" value="重置">
        <input type="submit" value="提交">
    </form>
</body>

</html>
<%--
  Created by IntelliJ IDEA.
  User: tubac
  Date: 17/03/2022
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head
  <title>Email validate</title>
</head>
<body>
<h1>Email validate</h1>
<h3 style="color: red">${message}</h3>
<form action="validate" method="post">
  <input type="text" name="email"><br>
  <input type="submit" value="Validate">

</form>
</body>
</html>

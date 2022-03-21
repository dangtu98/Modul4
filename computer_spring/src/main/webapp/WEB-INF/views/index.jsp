<%--
  Created by IntelliJ IDEA.
  User: tubac
  Date: 18/03/2022
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
  </head>
  <body>
  <form method="get">
    <input type="text" name="number1" value="${number1}">
    <input type="text" name="number2" value="${number2}">
    <input type="submit" name="caLcuL" value="cong">
    <input type="submit" name="caLcuL" value="tru">
    <input type="submit" name="caLcuL" value="nhan">
    <input type="submit" name="caLcuL" value="chia">
    <h2>Result: ${result}</h2>
  </form>
  </body>
</html>

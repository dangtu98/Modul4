<%--
  Created by IntelliJ IDEA.
  User: tubac
  Date: 19/03/2022
  Time: 01:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <label> Sửa Tên:
        <input name ="name" value="${product.name}">
    </label>
    <br>
    <label> Sửa Giá:
        <input name ="price" value="${product.price}">
    </label>
    <br>
    <label> Sửa Mô Tả:
        <input name ="description" value="${product.description}">
    </label>
    <br>
    <label> Sửa Link Img:
        <input name ="img" value="${product.img}">
    </label>
    <button type="submit">SAVE</button>
</form>
</body>
</html>

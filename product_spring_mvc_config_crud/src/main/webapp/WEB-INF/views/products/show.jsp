<%--
  Created by IntelliJ IDEA.
  User: tubac
  Date: 19/03/2022
  Time: 01:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Danh Sách Sản Phẩm</h1>
<button><a href="createProduct">tạo thêm sản phẩm</a></button>
<table border="1" cellpadding="5">
    <tr>
        <td>ID</td>
        <td>NAME</td>
        <td>PRICE</td>
        <td>DESCRIPTION</td>
        <td>IMG</td>
        <td colspan="2">LỰA CHỌN</td>
    </tr>
    <c:forEach items="${products}" var="product">
        <tr>
            <td>${product.id}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
            <td>${product.description}</td>
            <td><img src="${product.img}" width="100" height="100"></td>
            <td><button><a href="/edits/${product.id}">EDIT</a></button></td>
            <td><button><a href="#">DELETE</a></button></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

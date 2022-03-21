<%--
  Created by IntelliJ IDEA.
  User: tubac
  Date: 19/03/2022
  Time: 01:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="createProduct">
    <label> Nhập Tên :
        <input name="name" placeholder="nhap ten">
    </label>
    <br>
    <label> Nhập Giá :
        <input name="price" placeholder="nhap gia">
    </label>
    <br>
    <label> Nhập Mô Tả:
        <input name="description" placeholder="nhap mota">
    </label>
    <br>
    <label> Nhập Link Ảnh :
        <input name="img" placeholder="nhap img">
    </label>
    <br>
    <button type="submit">Save</button>
</form>
</body>
</html>

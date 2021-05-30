<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: STO
  Date: 3/17/2021
  Time: 9:23 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of Music</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
</head>
<body>

<table class="table">
    <thead class="thead-dark">
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Title</th>
        <th scope="col">Genre</th>
        <th scope="col">Artist</th>
        <th scope="col">Source</th>

    </tr>
    </thead>
    <tbody>
    <c:forEach var="MusicList" items="${MusicLists}">
    <tr>
        <td><c:out value="${MusicLists.idMusic}"/></td>
        <td><c:out value="${MusicLists.songTitle}"/></td>
        <td><c:out value="${MusicLists.songGenre}"/></td>
        <td><c:out value="${MusicLists.artistName}"/></td>
        <td><c:out value="${MusicLists.songSource}"/></td>
    </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>

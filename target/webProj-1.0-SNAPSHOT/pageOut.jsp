<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="com.example.webproj.loginDbEjb"%>
<html>
<link rel="stylesheet" href="styles/loginstyle.css">
<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@200&family=Pacifico&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<body>
<script src="https://kit.fontawesome.com/384a5d9dc2.js" crossorigin="anonymous"></script>
<div class = "front-panel">
    <table style="border-spacing: 15px;">
        <td><p><a id="mn" href = "main.jsp"><span class="fas fa-home"> Главная</span></a></p></td>
        <td><a id="news" onclick="location.href='getNewsServlet'"><i class="fas fa-newspaper"> Новости</i></a></td>
        <td><a id="sel" onclick="location.href='getThemesServlet'"><i class="fas fa-scroll"> Темы</i></a></td>
        <td><a id="profile" href="profile.jsp"><i class="fas fa-address-card"> Профиль</i></a></td>
        <c:choose>
            <c:when test="${loginDbEjb.authorized==true}">
                <td><form class="formasd" action ="logServ" method="post">
                    <button style="
    font-family: 'Press Start 2P', cursive;
    color: black !important;
    text-decoration: none;
    padding: 10px;
    background-color: white;
    border-top-left-radius: 15px;
    border-top-right-radius: 15px;
    border: outset 2px;
    border-color: black;
    margin-top: 15px;
    width: 150px;
    font-size: 15pt;
    margin-left: -15px;" id="logOut"><i class="fas fa-sign-out-alt"> Выйти</i></button>
                </form></td>
            </c:when>
            <c:otherwise>
                <td><a id="login" href="login.jsp"><i class="fas fa-sign-in-alt"> Вход/Регистрация</i></a></td>
            </c:otherwise>
        </c:choose>

        <td><img id="fw1" src="styles\\images\\fw2.png"></img></td>
        <td><a class = "notb" id ="welcome">Welcome, friend!</a></td>
        <td><img id="fw2" src="styles\\images\\fw2.png"></img></td>
    </table>
</div>
<hr color="white"/>
<div class="out">
<c:forEach items="${announcements}" var="item" varStatus="loop">
    <div class="header">
        ${item.header}
        <p/>
        <p/>
    </div>
    <div class="bodyT">
        ${item.textInfo}
    </div>
</c:forEach>
</div>
<p/>
<p/>
<label style="font-size: 24px; color: white; text-decoration: underline; margin-left: 100px;">Комментарии (${commentCount}): </label>
<hr color="white"/>
    <p/>
    <p/>
    <c:forEach items="${comments}" var="item" varStatus="loop">
        <div class="commentSec">
        <div class="header">
            <i class="fas fa-user"> Пользователь: ${item.username}</i>
            <c:choose>
                <c:when test="${loginDbEjb.getUsername().toLowerCase() == item.username.toLowerCase()}">
                        <button onclick="location.href='deleteCommentServlet?id=${item.id}&try=${requestScope['jakarta.servlet.forward.query_string']}'" style="margin-left: 100px;"><i class="fas fa-trash-alt"></i> Удалить</button>
                </c:when>
                <c:when test="${loginDbEjb.admin==true}">
                        <button onclick="location.href='deleteCommentServlet?id=${item.id}&try=${requestScope['jakarta.servlet.forward.query_string']}'" style="margin-left: 100px;"><i class="fas fa-trash-alt"></i> Удалить</button>
                </c:when>
            </c:choose>
        </div>
            <c:choose>
                <c:when test="${item.admin==true}">
                    <label style="color: yellow; margin-left: 35px; font-size: 16px; !important; font-style: italic; !important;"> Администратор</label>
                </c:when>
            </c:choose>
            <hr color="white"/>
            <p/>
        <div class="bodyT">
            ${item.commentBody}
        </div>
            <div style="text-align: right;">
                <label style="text-decoration: none; color: rgba(255,255,255,0.46);">${item.commDate}</label>
            </div>
</div>
    </c:forEach>
<c:choose>
    <c:when test="${loginDbEjb.authorized==true}">
        <div class="newComm">
            <form action="addCommentServlet" method="post">
                <input name="idAnn" hidden value="${requestScope['jakarta.servlet.forward.query_string']}"/>
                <textarea wrap="hard" cols="100" style="width: 935px; height: 150px;resize: none; text-align: left;" id="altT" name="alteredText" placeholder="Текст комментария..."></textarea>
                <button>Оставить комментарий</button>
            </form>
        </div>
    </c:when>
</c:choose>


</body>
</html>

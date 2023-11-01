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
<ul>
    <c:choose>
        <c:when test="${loginDbEjb.admin==true}">
            <button style="font-size: 20px;" onclick="location.href='newAnn.jsp'"><i class="far fa-plus-square"></i> Добавить новость</button>
            <p/>
        </c:when>
    </c:choose>
    <c:forEach items="${announcements}" var="item" varStatus="loop">
        <li><img id="img1" style="height: 10%; width: 10%; float: left;" src="${item.image}">
            <div class="mText">
                <span id="spn1" style="display:inline-block; height: 90px; width: 70px; float: left;"></span>
            <label class="mText" style="color: white; font-size: 20px; text-decoration: underline;" onclick="location.href='pageOutServlet?id=${item.id}'">${item.header} </label>
                <c:choose>
                    <c:when test="${loginDbEjb.admin==true}">
                        <button onclick="location.href='textOutServlet?id=${item.id}'"> Изменить</button>
                        <button onclick="location.href='deleteAnnServlet?id=${item.id}&try=${requestScope['jakarta.servlet.forward.query_string']}'"><i class="fas fa-trash-alt"></i> Удалить</button>
                    </c:when>
                </c:choose>
            <p><label class="mText" style="color: white; font-size: 16px;">${item.content}</label></p>
        </div>
            <p style="margin-top: 50px;"><label style="color: white; font-size: 16px;">Дата: ${item.date}</label></p>
            <hr color="white"/>
        </li>
    </c:forEach>
</ul>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" import="com.example.webproj.loginDbEjb" %>
<html>
<link rel="stylesheet" href="styles/profilestyle.css">
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
<hr color="white"></hr>
<c:choose>
    <c:when test="${loginDbEjb.authorized==true}">
        <div class="profileOpen">
            <img src="styles\\images\\empty_staff.png" style="width: 100px; height: 100px;"/>
            <p><label>Имя пользователя: ${loginDbEjb.getUsername()}</label></p>
            <c:choose>
                <c:when test="${loginDbEjb.admin==true}">
                    <p><label style="color: yellow;">Администратор</label></p>
                    <form action="getUsersServlet" method="post">
                        <button>Список пользователей</button>
                    </form>
                        <table id="userTable" border="1"  class="userList" style="border: 1px white;">
                            <tr>
                            <th>id</th>
                            <th>username</th>
                            <th>admin</th>
                            <th>Статус блокировки</th>
                            <th>Действие</th>
                            </tr>
                            <c:forEach items="${users}" var="user" varStatus="loop">
                                <tr>
                                    <td>${user.id}</td>
                                    <td>${user.username}</td>
                                    <td>${user.admin}</td>
                                    <td>${user.blocked}</td>
                                    <c:choose>
                                        <c:when test="${user.username==loginDbEjb.getUsername()}">
                                            <td><label>Это вы!</label></td>
                                        </c:when>
                                        <c:otherwise>
                                            <c:choose>
                                                <c:when test="${user.blocked==true}">
                                                    <td><button onclick="location.href='getUsersServlet?id=${user.id}&blocked=${user.blocked}'"}><i class="fas fa-user-check"></i> Разблокировать</button></td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td><button onclick="location.href='getUsersServlet?id=${user.id}&blocked=${user.blocked}'"}><i class="fas fa-user-times"></i> Заблокировать</button></td>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </c:forEach>
                        </table>
                </c:when>
            </c:choose>
            <style>
                .userList td, th{
                    color: white; !important;
                }
            </style>
            <c:choose>
                <c:when test="${loginDbEjb.showUsers==true}">
                    <style>
                        .userList{
                            display: flex;
                        }
                    </style>
                </c:when>
                <c:otherwise>
                    <style>
                        .userList{
                            display: none;
                        }
                    </style>
                </c:otherwise>
            </c:choose>
            <p><label style="font-size: 24pt;text-decoration: underline;">Активность:</label></p>
            <hr color="white"></hr>
            <p><label>Количество оставленных комментариев: ${loginDbEjb.commentCount()}</label></p>
        </div>
    </c:when>
    <c:otherwise>
        <div class="profileClosed">
            <label id="ahiff">Вы не авторизованы!</label>
        </div>
    </c:otherwise>
</c:choose>

</body>
</html>

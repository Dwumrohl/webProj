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
  <hr color="white"></hr>
  <script>	
	function openReg(evt, value){
		var i, tabcontent, tablinks;
		tabcontent=document.getElementsByClassName("tabcontent");
		for (i=0; i < tabcontent.length;i++){
			tabcontent[i].style.display = "none";
		}
		
		tablinks=document.getElementsByClassName("tablinks");
		for (i=0;i<tablinks.length;i++){
			tablinks[i].className=tablinks[i].className.replace(" active", "");		
		}
		if(value === 'log')
            document.getElementById("flag").value=0;
        else document.getElementById("flag").value=1;
		document.getElementById(value).style.display="block";
		evt.currentTarget.className += " active";
	}
  </script>
 <div class = "main">
     <button class="tablinks" id="loginBtn" onclick="openReg(Event, 'log')">Вход</button>
	 <button class="tablinks" id="register" onclick="openReg(Event, 'reg')">Регистрация</button>
 </div>
  <form name="logForm" method = "get" action = "logServ">
 <div id="log" class="tabcontent">
	<p><input placeholder="Логин" type="text" name="loginText"/></p>
	<p><input class = "psw" placeholder="Пароль" type="text" name="pswText"/></p>
  <p><button>Войти</button></p>
 </div>
      <input type="hidden" name="flag" id="flag"/>
 <div id="reg" class="tabcontent">
	<p><input placeholder="Логин" type="text" name="loginTextReg"/></p>
	<p><input class = "psw" placeholder="Пароль" name="pswTextReg" type="text"/></p>
  <p><button>Зарегестрироваться</button></p>
 </div>
  </form>
 </body>
</html>
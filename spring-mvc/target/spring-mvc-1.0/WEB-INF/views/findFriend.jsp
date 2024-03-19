<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.demo.dao.UserDAO" %>
<%@ page import="com.demo.model.User" %>
<%@ page import="com.demo.controller.UserController" %>
<%@ page import="com.demo.service.UserService" %>

<%
	// Assuming you have a UserDAO instance available
	UserDAO userDAO = new UserDAO();
	
	// Replace 123 with the actual user ID you want to retrieve
	int userID = Integer.parseInt(session.getAttribute("userID").toString());
	User user = userDAO.getUserByID(userID);
	
	// Set the retrieved values to JSP variables
	String username = user.getUsername();
	String firstname = user.getFirstname();
	String lastname = user.getLastname();
	int elo = user.getElo();
	String email = user.getEmail();
	String location = user.getCountry();
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CHESS ONLINE - INFOMATION</title>
</head>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/template/web/CSS/styleFindFriend.css'/>" />
<script src="https://kit.fontawesome.com/5175756225.js" crossorigin="anonymous"></script>
<script src="<c:url value='/template/web/JS/websocket.js'/>"></script>
<link rel="icon"
	href="<c:url value='/template/web/QuanCo/Ma_Trang.png'/>" />

<body>
<div id="divMain">
		<div id="left">
			<div id="left-top">
				<button id="home" class="buttonTop" onclick="redirectTo('<c:url value='/home'/>')">
					<i style="color: rgb(104, 169, 243);" class="fa-solid fa-house"></i>CHESS
					ONLINE
				</button>
				<button id="play" class="buttonTop">
					<i class="fa-solid fa-chess"></i>PLAY
				</button>
				<button id="analysis" class="buttonTop">
					<i class="fa-solid fa-chess-board"></i>ANALYSIS
				</button>
				<button id="history" class="buttonTop" onclick="redirectTo('<c:url value='/history'/>')">
					<i class="fa-solid fa-clock"></i>HISTORY
				</button>
			</div>

			<div id="left-bot">
				<button class="buttonBot">
					<i class="fa-solid fa-circle-half-stroke"></i>Dark UI
				</button>
				<button class="buttonBot">
					<i class="fa-solid fa-arrow-left"></i>Collapse
				</button>
				<button id="settingsButton" class="buttonBot">
					<i class="fa-solid fa-gear"></i>Settings
				</button>
				<button class="buttonBot">
					<i class="fa-solid fa-circle-info"></i>Helps
				</button>
			</div>
		</div>

		<div id="settingsChoose">
			<button id="logout" onclick="redirectTo('<c:url value='/logout'/>')"
				class="buttonSettingsChoose">Log out</button>
			<br/>
				<button id="profile"
					onclick="redirectTo('<c:url value='/profile'/>')"
					class="buttonSettingsChoose">Profie</button>
		</div>

		<script>
			  // Lấy tham chiếu đến button và đối tượng cần thay đổi
			  const button = document.getElementById('settingsButton');
			  const settingsChoose = document.getElementById('settingsChoose');
			  let timeoutId;
			
			  // Thêm sự kiện hover
			  button.addEventListener('mouseover', () => {
			    settingsChoose.style.display = 'block';
			    clearTimeout(timeoutId); // Xóa bất kỳ timeout nào đang đợi
			  });
			
			  button.addEventListener('mouseout', () => {
			    // Sử dụng setTimeout để chờ 2 giây trước khi ẩn
			    timeoutId = setTimeout(() => {
			      settingsChoose.style.display = 'none';
			    }, 2000);
			  });
			</script>

    <div id="contentSettings">
        <div id="settings">
            <i style="padding-top: 17%; padding-right: 3%;" class="fa-solid fa-gear"></i><p>SETTINGS</p>
        </div>
        <div id="content">
            <div id="contentLeft">
                <button class="buttonContentLeft" id="profile" onclick="redirectTo('<c:url value='/profile'/>')"><i class="fa-regular fa-user"></i>Profile</button>
                <button class="buttonContentLeft" id="password"><i class="fa-solid fa-key"></i>Password</button>
                <button class="buttonContentLeft" id="friend"><i class="fa-solid fa-user-group"></i>Friend</button>
                <button class="buttonContentLeft" id="more">... More</button>
            </div>
            <div id="contentRight">
                <div id="contentRightTop">
                    <div style="display: flex;">
                        <img id="user" src="./template/web/Icon/385495544_283837477747988_1133442171291101487_n.png" alt="">
                    <div id="info1st">
                        <p id="you"><%= username %></p>
                        <p id="elo">Elo: <%= elo %></p>
                    </div>
                    </div>
						<form action="/spring-mvc/findFriend" method="get">
						    <div>
						        <input type="text" class="findFriend" id="userIDInput" name="friendID" placeholder="nhập username bạn bè">
						        <button class="buttonFind" type="submit">Find</button>
						    </div>
						</form>    
					<%
					    Object userObject = request.getAttribute("user");
					
					    // Check if the "user" object is not null
					    if (userObject != null) {
					        // Cast the "user" object to your User class
					        User user1 = (User) userObject;
					%>
					</div>
					<div id="contentRightBot">
					    <div class="infoFriendContainer">
					        <div style="display: flex;">
					            <p class="username" name="friendUsername">Username: <%= (user1 != null) ? user1.getUsername() : "" %></p>
					            <p class="name" name="friendName">Name: <%= (user1 != null) ? (user1.getFirstname() + " " + user1.getLastname()) : "" %></p>
					            <p class="elo" name="friendElo">Elo: <%= (user1 != null) ? Integer.toString(user1.getElo()) : "" %></p>           
					        </div>
					        <button class="addFriend">Add</button>
					    </div>
					</div>
					
					<%
					    }
					%>
            
        </div>
    </div>
</div> 
</body>
</html>

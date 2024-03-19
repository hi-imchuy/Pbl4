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
	href="<c:url value='/template/web/CSS/styleInfomation.css'/>" />
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
                <button class="buttonContentLeft" id="friend" onclick="redirectTo('<c:url value='/findFriend?friendID=-1'/>')"><i class="fa-solid fa-user-group"></i>Friend</button>
                <button class="buttonContentLeft" id="more">... More</button>
            </div>
            <div id="contentRight">
                <div id="contentRightTop">
                    <div id="avtUser">
                        <img id="user" src="./template/web/avt/<%= userID %>.jpg" alt="your image" />
						<form id="formAvt" method="post" action="fileuploadservlet" enctype="multipart/form-data">
    						<input id="chooseFile" type="file" name="file" accept="image/*" required/>
    						<input type="hidden" name="user" value="<%= userID %>"/>
    						<button class="btn-img" type="submit">Change Avatar</button> 
						</form>
                     
                    </div>
                    <div id="info1st">
                        <p id="you"><%= username %></p>
                        <p id="elo">Elo: <%= elo %></p>
                        
                    </div>
                </div>

                <div id="contentRightBot">
                    <div id="contentRightBotInf">
                        <div class="rightBotInf" id="userName">
                            <p class="nameInfUser">Username</p>
                            <p style="margin-left: 108px;" class="infoUser"><%= username %></p>
                        </div>
						<form action="/spring-mvc/profile" method="post">
							<input type="hidden" name="userID" value="<%= Integer.parseInt(session.getAttribute("userID").toString()) %>" />
						    <div class="rightBotInf" id="firstName"> 
                            <p class="nameInfUser">First Name</p>                           
                            <input style="margin: 0 0 10px 100px; width: 250px;" class="infoUser" type="text" name="firstname" value="<%= firstname %>">
                        </div>
	
                        <div class="rightBotInf" id="lastName">
                            <p class="nameInfUser">Last Name</p>
                            <input style="margin: 0 0 10px 102px; width: 250px;" class="infoUser" class="infoUser" type="text" name="lastname" value="<%= lastname%>">
                        </div>

                        <div class="rightBotInf" id="email">
                            <p class="nameInfUser">Email</p>
                            <p style="margin-left: 135px;" class="infoUser"><%= email %></p>
                           <!-- <a id="change" href="">Change</a>  -->
                        </div>

                        <div class="rightBotInf" id="location">
                            <p class="nameInfUser">Location</p>
                            <select name="" id="country" class="infoUser">
                                <option value="">VietNam</option>
                                <option value="">ThaiLand</option>
                                <option value="">China</option>
                                <option value="">Laos</option>
                            </select>
                        </div>

                        <button type="submit" id="save">SAVE</button>
						</form>

                    </div>                    
                </div>
            </div>
        </div>
    </div>
</div> 
</body>
<script>
    function chooseFile() {
        // Tạo một thẻ input để chọn file
        const fileInput = document.createElement("input");
        fileInput.type = "file";
        fileInput.accept = "image/*";
        
        // Khi người dùng chọn file, thực hiện hàm xử lý
        fileInput.onchange = function (evt) {
            const [file] = fileInput.files;
            if (file) {
                // Hiển thị hình ảnh đã chọn
                user.src = URL.createObjectURL(file);
            }
        };

        // Kích hoạt sự kiện click trên input để chọn file
        fileInput.click();
    }
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>CHESS ONLINE</title>

<link rel="icon"
	href="<c:url value='/template/web/QuanCo/Ma_Trang.png'/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value='/template/web/CSS/styleHomeLogged.css'/>" />
<script src="https://kit.fontawesome.com/5175756225.js"
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="<c:url value='/template/web/JS/Run.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/template/web/JS/Sources.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/template/web/JS/KhoiTao.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/template/web/JS/Function.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/template/web/JS/XacDinhDuongDi/Tot.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/template/web/JS/XacDinhDuongDi/Vua.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/template/web/JS/XacDinhDuongDi/Xe.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/template/web/JS/XacDinhDuongDi/Tuong.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/template/web/JS/XacDinhDuongDi/Ma.js'/>"></script>
<script type="text/javascript"
	src="<c:url value='/template/web/JS/XacDinhDuongDi/Hau.js'/>"></script>
<!-- 
<script src="path_to_your_js_directory/websocket.js"></script> -->
<script src="<c:url value='/template/web/JS/websocket.js'/>"></script>



</head>
<body onload="KhoiTao()">
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
			<br>
			<button id="profile"
				onclick="redirectTo('<c:url value='/profile'/>')"
				class="buttonSettingsChoose">Profie</button>
		</div>

		<script>
				  const button = document.getElementById('settingsButton');
				  const settingsChoose = document.getElementById('settingsChoose');
				  let timeoutId;
			
				  button.addEventListener('mouseover', () => {
				    settingsChoose.style.display = 'block';
				    clearTimeout(timeoutId);
				  });
				
				  button.addEventListener('mouseout', () => {
				    timeoutId = setTimeout(() => {
				      settingsChoose.style.display = 'none';
				    }, 2000);
				  });
				</script>

		<div id="mid">
			<div id="divNguoiChoiCoDen">
				<table id="tblNguoiCoDen">
					<tr>
						<td><img id="iCoDen"
							src="<c:url value='/template/web/User/User_Enb.png'/>" alt="" /></td>

					</tr>
					<tr>
						<td id="PointCoDen">Point: 0</td>
					</tr>
				</table>

			</div>

			<div id="divBanCo">
				<table id="BanCo">
					<tr>
						<td id="a8"><img id="ia8" /></td>
						<td id="b8"><img id="ib8" /></td>
						<td id="c8"><img id="ic8" /></td>
						<td id="d8"><img id="id8" /></td>
						<td id="e8"><img id="ie8" /></td>
						<td id="f8"><img id="if8" /></td>
						<td id="g8" ><img id="ig8" /></td>
						<td id="h8" ><img id="ih8" /></td>
					</tr>
					<tr>
						<td id="a7" ><img id="ia7" /></td>
						<td id="b7" ><img id="ib7" /></td>
						<td id="c7" ><img id="ic7" /></td>
						<td id="d7" ><img id="id7" /></td>
						<td id="e7" ><img id="ie7" /></td>
						<td id="f7" ><img id="if7" /></td>
						<td id="g7" ><img id="ig7" /></td>
						<td id="h7" ><img id="ih7" /></td>
					</tr>
					<tr>
						<td id="a6" ><img id="ia6" /></td>
						<td id="b6" ><img id="ib6" /></td>
						<td id="c6" ><img id="ic6" /></td>
						<td id="d6" ><img id="id6" /></td>
						<td id="e6" ><img id="ie6" /></td>
						<td id="f6" ><img id="if6" /></td>
						<td id="g6" ><img id="ig6" /></td>
						<td id="h6" ><img id="ih6" /></td>
					</tr>
					<tr>
						<td id="a5" ><img id="ia5" /></td>
						<td id="b5" ><img id="ib5" /></td>
						<td id="c5" ><img id="ic5" /></td>
						<td id="d5" ><img id="id5" /></td>
						<td id="e5" ><img id="ie5" /></td>
						<td id="f5" ><img id="if5" /></td>
						<td id="g5" ><img id="ig5" /></td>
						<td id="h5" ><img id="ih5" /></td>
					</tr>
					<tr>
						<td id="a4" ><img id="ia4" /></td>
						<td id="b4" ><img id="ib4" /></td>
						<td id="c4" ><img id="ic4" /></td>
						<td id="d4" ><img id="id4" /></td>
						<td id="e4" ><img id="ie4" /></td>
						<td id="f4" ><img id="if4" /></td>
						<td id="g4" ><img id="ig4" /></td>
						<td id="h4" ><img id="ih4" /></td>
					</tr>
					<tr>
						<td id="a3" ><img id="ia3" /></td>
						<td id="b3" ><img id="ib3" /></td>
						<td id="c3" ><img id="ic3" /></td>
						<td id="d3" ><img id="id3" /></td>
						<td id="e3" ><img id="ie3" /></td>
						<td id="f3" ><img id="if3" /></td>
						<td id="g3" ><img id="ig3" /></td>
						<td id="h3" ><img id="ih3" /></td>
					</tr>
					<tr>
						<td id="a2" ><img id="ia2" /></td>
						<td id="b2" ><img id="ib2" /></td>
						<td id="c2" ><img id="ic2" /></td>
						<td id="d2" ><img id="id2" /></td>
						<td id="e2" ><img id="ie2" /></td>
						<td id="f2" ><img id="if2" /></td>
						<td id="g2" ><img id="ig2" /></td>
						<td id="h2" ><img id="ih2" /></td>
					</tr>
					<tr>
						<td id="a1" ><img id="ia1" /></td>
						<td id="b1" ><img id="ib1" /></td>
						<td id="c1" ><img id="ic1" /></td>
						<td id="d1" ><img id="id1" /></td>
						<td id="e1" ><img id="ie1" /></td>
						<td id="f1" ><img id="if1" /></td>
						<td id="g1" ><img id="ig1" /></td>
						<td id="h1" ><img id="ih1" /></td>
					</tr>
				</table>

			</div>

			<div id="divNguoiChoiCoDo">
				<table id="tblNguoiChoiCoDo">
					<tr>
						<td><img id="iCoDo"
							src="<c:url value='/template/web/User/User_Dis.png'/>" alt="" /></td>
						<!-- <td><img id="iCoDo" src="User/User_Dis.png" /></td> -->
					</tr>
					<tr>
						<td id="PointCoDo">Point: 0</td>
					</tr>
				</table>
			</div>
		</div>

		<div id="right">
			<div id="edit-Right">
				<p class="inRight" id="playChess">
					PLAY CHESS <br /> <i style="color: black;"
						class="fa-solid fa-chess"></i>
				</p>
				<button class="inRight" id="playOnline"
					onclick="redirectToAction('<c:url value='/play'/>', 'online')">
					<i class="fa-solid fa-bolt"></i>Play Online
				</button>
				<button class="inRight" id="playComputer"
					onclick="redirectToAction('<c:url value='/play'/>', 'computer')">
					<i class="fa-solid fa-computer"></i>Computer
				</button>
				<button class="inRight" id="playFriend"
					onclick="redirectToAction('<c:url value='/play'/>', 'friend')">
					<i class="fa-solid fa-user-group"></i>Play with Friend
				</button>

			</div>
		</div>

	</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>CHESS ONLINE - HISTORY</title>
</head>
<link rel="stylesheet" type="text/css"
	href="<c:url value='/template/web/CSS/styleHistory.css'/>" />
<link rel="icon"
	href="<c:url value='/template/web/QuanCo/Ma_Trang.png'/>" />
<script src="https://kit.fontawesome.com/5175756225.js"
	crossorigin="anonymous"></script>
<script src="<c:url value='/template/web/JS/websocket.js'/>"></script>
<body>
	<div id="divMain">
		<div id="left">
			<div id="left-top">
				<button id="home" class="buttonTop"
					onclick="redirectTo('<c:url value='/home'/>')">
					<i style="color: rgb(104, 169, 243);" class="fa-solid fa-house"></i>CHESS
					ONLINE
				</button>
				<button id="play" class="buttonTop">
					<i class="fa-solid fa-chess"></i>PLAY
				</button>
				<button id="analysis" class="buttonTop">
					<i class="fa-solid fa-chess-board"></i>ANALYSIS
				</button>
				<button id="history" class="buttonTop"
					onclick="redirectTo('<c:url value='/history'/>')">
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

		<div id="right">
			<div id="historyTitle">
				<i style="padding-top: 17%; padding-right: 3%;"
					class="fa-solid fa-clock"></i>
				<p>HISTORY</p>
			</div>
			<div id="historyContainer"></div>
		</div>
	</div>
	<script>
    document.addEventListener('DOMContentLoaded', function() {
        fetch('./api/history')
            .then(response => response.json())
            .then(histories => {
                console.log("Lịch sử trận đấu:", histories);

                const historyContainer = document.getElementById('historyContainer');

                histories.forEach(game => {
                    // Tạo các thẻ HTML
                    const historyElement = document.createElement('div');
                    historyElement.className = 'boxHistoryContent';
                    historyElement.innerHTML = `
                        <div class="userA">
                            <img class="colorUser" alt="Player A">
                            <div class="infoUser">
                                <div class="infoUserName"></div>
                                <div class="infoUserElo"></div>
                            </div>
                            <img style="width: 50px;" alt="Flag A">
                        </div>
                        <div class="result">
                            <h1 class=""></h1>
                        </div>
                        <div class="userB">
                            <img style="width: 50px;" alt="Flag B">     
                            <div class="infoUser">
                                <div class="infoUserName"></div>
                                <div class="infoUserElo"></div>
                            </div>
                            <img class="colorUser" alt="Player B">
                        </div>
                        <div class="analysisIcon">
	                        <i class="fa-solid fa-magnifying-glass"></i>
	                    </div>
                    `;

                    // Gán dữ liệu vào các thẻ
                    historyElement.querySelector('.userA .colorUser').src = game.playerAIcon;
                    historyElement.querySelector('.userA .infoUserName').textContent = game.playerAName;
                    historyElement.querySelector('.userA .infoUserElo').textContent = 'Elo: ' + game.playerAEloChange;
                    historyElement.querySelector('.userA img:nth-of-type(2)').src = game.playerAFlag;

                    const resultClass = game.result === 'WIN' ? 'resultTitle' : 'resultTitleLose';
                    historyElement.querySelector('.result h1').className = resultClass;
                    historyElement.querySelector('.result h1').textContent = game.result;

                    historyElement.querySelector('.userB .colorUser').src = game.playerBIcon;
                    historyElement.querySelector('.userB .infoUserName').textContent = game.playerBName;
                    historyElement.querySelector('.userB .infoUserElo').textContent = 'Elo: ' + game.playerBEloChange;
                    historyElement.querySelector('.userB img:nth-of-type(1)').src = game.playerBFlag;
                    
                    historyElement.querySelector('.analysisIcon').id = game.id;
                    historyElement.querySelector('.analysisIcon').onclick = function() {
                        redirectToAnalysis(game.id);
                    };
                    // Thêm historyElement vào historyContainer	
                    historyContainer.appendChild(historyElement);
                });
            })
            .catch(error => {
                console.error('Error fetching history:', error);
                historyContainer.innerHTML = '<p>Không thể tải lịch sử trận đấu.</p>';
            });
    });

</script>


</body>
</html>
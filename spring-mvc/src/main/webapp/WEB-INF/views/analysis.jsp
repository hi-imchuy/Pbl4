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
	href="<c:url value='/template/web/CSS/styleAnalys.css'/>" />
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
<!-- <script src="path_to_your_js_directory/websocket.js"></script> -->
<script src="<c:url value='/template/web/JS/websocket.js'/>"></script>
<script src="<c:url value='/template/web/JS/websocket-connection.js'/>"></script>
<script type="text/javascript">
	var movesData = JSON.parse('${movesJson}');
	console.log(movesData);
    window.onload = function() {
        KhoiTao();
        Wait();
    };
    //showCurrentMove();
</script>




</head>
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
			<br />
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



		<div id="mid">
			<div id="divNguoiChoiCoDen"></div>

			<div id="divBanCo">
				<div class="competitor">
					<img style="margin: 0px;" src="./template/web/Icon/user_icon.png"
						alt="User Avatar" />
					<div class="contentCompetitor">
						<p id="username_top">Computer</p>
						<p id="elo_top" style="color: red; margin-top: -15px;">Elo:
							2000</p>
					</div>
					<img class="flag" src="./template/web/Icon/flag_icon.png" />
				</div>
				<table id="BanCo">
					<tr>
						<td id="a8" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ia8" /></td>
						<td id="b8" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ib8" /></td>
						<td id="c8" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ic8" /></td>
						<td id="d8" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="id8" /></td>
						<td id="e8" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ie8" /></td>
						<td id="f8" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="if8" /></td>
						<td id="g8" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ig8" /></td>
						<td id="h8" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ih8" /></td>
					</tr>
					<tr>
						<td id="a7" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ia7" /></td>
						<td id="b7" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ib7" /></td>
						<td id="c7" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ic7" /></td>
						<td id="d7" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="id7" /></td>
						<td id="e7" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ie7" /></td>
						<td id="f7" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="if7" /></td>
						<td id="g7" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ig7" /></td>
						<td id="h7" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ih7" /></td>
					</tr>
					<tr>
						<td id="a6" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ia6" /></td>
						<td id="b6" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ib6" /></td>
						<td id="c6" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ic6" /></td>
						<td id="d6" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="id6" /></td>
						<td id="e6" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ie6" /></td>
						<td id="f6" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="if6" /></td>
						<td id="g6" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ig6" /></td>
						<td id="h6" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ih6" /></td>
					</tr>
					<tr>
						<td id="a5" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ia5" /></td>
						<td id="b5" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ib5" /></td>
						<td id="c5" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ic5" /></td>
						<td id="d5" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="id5" /></td>
						<td id="e5" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ie5" /></td>
						<td id="f5" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="if5" /></td>
						<td id="g5" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ig5" /></td>
						<td id="h5" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ih5" /></td>
					</tr>
					<tr>
						<td id="a4" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ia4" /></td>
						<td id="b4" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ib4" /></td>
						<td id="c4" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ic4" /></td>
						<td id="d4" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="id4" /></td>
						<td id="e4" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ie4" /></td>
						<td id="f4" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="if4" /></td>
						<td id="g4" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ig4" /></td>
						<td id="h4" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ih4" /></td>
					</tr>
					<tr>
						<td id="a3" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ia3" /></td>
						<td id="b3" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ib3" /></td>
						<td id="c3" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ic3" /></td>
						<td id="d3" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="id3" /></td>
						<td id="e3" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ie3" /></td>
						<td id="f3" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="if3" /></td>
						<td id="g3" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ig3" /></td>
						<td id="h3" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ih3" /></td>
					</tr>
					<tr>
						<td id="a2" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ia2" /></td>
						<td id="b2" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ib2" /></td>
						<td id="c2" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ic2" /></td>
						<td id="d2" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="id2" /></td>
						<td id="e2" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ie2" /></td>
						<td id="f2" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="if2" /></td>
						<td id="g2" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ig2" /></td>
						<td id="h2" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ih2" /></td>
					</tr>
					<tr>
						<td id="a1" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ia1" /></td>
						<td id="b1" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ib1" /></td>
						<td id="c1" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ic1" /></td>
						<td id="d1" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="id1" /></td>
						<td id="e1" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ie1" /></td>
						<td id="f1" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="if1" /></td>
						<td id="g1" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ig1" /></td>
						<td id="h1" onclick="Click(this.id)"><img class="analys"
							src="" /><img id="ih1" /></td>
					</tr>
				</table>
				<div class="competitor" style="margin-top: -10px;">
					<img style="margin: 0px;" src="./template/web/Icon/user_icon.png"
						alt="User Avatar" />
					<div class="contentCompetitor">
						<p id="username_bottom">Competitor</p>
						<p id="elo_bottom" style="color: red; margin-top: -15px;">Elo:
							0</p>
					</div>
					<img class="flag" src="./template/web/Icon/flag_icon.png" />
				</div>
			</div>

			<div id="divNguoiChoiCoDo"></div>
		</div>

		<div id="right">
			<p id="titleRight">ANALYSIS</p>
			<div id="chessStep">
				<div id="stepWhite">
					<h3>Nước đã đi</h3>
					<ol>

					</ol>
				</div>
				<div id="stepBlack">
					<h3>Nước đi tốt hơn</h3>
					<ol>

					</ol>
				</div>
			</div>
			<div id="buttonMove">
				<button class="btnMove" id="back"><</button>
				<button class="btnMove" id="pause">Continue</button>
				<button class="btnMove" id="next">></button>
			</div>
		</div>

	</div>
</body>

<script>
  document.addEventListener("DOMContentLoaded", function() {
    setChessHeight("blackChess");
    setChessHeight("whiteChess");
});

function setChessHeight(id) {
    var chessDiv = document.getElementById(id);
    if (chessDiv) {
        var chessValue = chessDiv.innerText.trim();
        chessDiv.style.height = chessValue;
    }
}


// 
var currentMoveIndex = 0;
var isPaused = true;
var intervalId;
var moveHistory = []; // Mảng này sẽ lưu trữ lịch sử các nước đi


function isNhapThanh(id, idnew) {
    if (id === "e1" && (idnew === "g1" || idnew === "c1")) {
        return true;
    } else if (id === "e8" && (idnew === "g8" || idnew === "c8")) {
        return true;
    }
    return false;
}

function DiChuyen(id, idMoi) {
    var tenQuanCo = GetName(id);
    //
    var trangThaiTruoc = { id: id, src: document.getElementById("i" + id).src };
    var trangThaiSau = { id: idMoi, src: document.getElementById("i" + idMoi).src };
    var trangThaiNhapThanh = [];
    //
    if (tenQuanCo === "Vua" && isNhapThanh(id, idMoi)) {
    	// his
    	 if (idMoi === "g1") {
             trangThaiNhapThanh.push({ id: "h1", src: document.getElementById("i" + "h1").src });
             trangThaiNhapThanh.push({ id: "f1", src: document.getElementById("i" + "f1").src });
         } else if (idMoi === "c1") {
             trangThaiNhapThanh.push({ id: "a1", src: document.getElementById("i" + "a1").src });
             trangThaiNhapThanh.push({ id: "d1", src: document.getElementById("i" + "d1").src });
         } else if (idMoi === "g8") {
             trangThaiNhapThanh.push({ id: "h8", src: document.getElementById("i" + "h8").src });
             trangThaiNhapThanh.push({ id: "f8", src: document.getElementById("i" + "f8").src });
         } else if (idMoi === "c8") {
             trangThaiNhapThanh.push({ id: "a8", src: document.getElementById("i" + "a8").src });
             trangThaiNhapThanh.push({ id: "d8", src: document.getElementById("i" + "d8").src });
         }
    	// 
        if (idMoi === "g1") { // Nhập thành kingside cho trắng
            document.getElementById("i" + "f1").src = document.getElementById("i" + "h1").src; // Di chuyển Xe
            document.getElementById("i" + "h1").src = CoTrang.Rong;
        } else if (idMoi === "c1") { // Nhập thành queenside cho trắng
            document.getElementById("i" + "d1").src = document.getElementById("i" + "a1").src; // Di chuyển Xe
            document.getElementById("i" + "a1").src = CoTrang.Rong;
        } else if (idMoi === "g8") { // Nhập thành kingside cho đen
            document.getElementById("i" + "f8").src = document.getElementById("i" + "h8").src; // Di chuyển Xe
            document.getElementById("i" + "h8").src = CoDen.Rong;
        } else if (idMoi === "c8") { // Nhập thành queenside cho đen
            document.getElementById("i" + "d8").src = document.getElementById("i" + "a8").src; // Di chuyển Xe
            document.getElementById("i" + "a8").src = CoDen.Rong;
        }
    }

    // Thực hiện di chuyển quân cờ thông thường
    document.getElementById("i" + idMoi).src = document.getElementById("i" + id).src;
    document.getElementById("i" + id).src = CoTrang.Rong; // Sử dụng CoTrang.Rong nếu đó là quân trắng
    moveHistory.push({ trangThaiTruoc, trangThaiSau, trangThaiNhapThanh });
    return true;
}

function showCurrentMove() {
    var move = movesData[currentMoveIndex];
    var moveNotation = move.moveNotation;
    var betterMove = move.betterMove || "Không có";
    var quality = move.moveQuality;
    // Thêm nước đi hiện tại vào danh sách "Nước đã đi"
    var currentMoveList = document.getElementById("stepWhite").getElementsByTagName("ol")[0];
    var moveItem = document.createElement("li");
    moveItem.innerText = moveNotation;
    currentMoveList.appendChild(moveItem);

    // Thêm nước đi tốt hơn vào danh sách "Nước đi tốt hơn"
    var betterMoveList = document.getElementById("stepBlack").getElementsByTagName("ol")[0];
    var betterMoveItem = document.createElement("li");
    betterMoveItem.innerText = betterMove;
    betterMoveList.appendChild(betterMoveItem);
    
    var qualityImagePath = "./template/web/Icon/" + quality.toLowerCase() + ".png"; // Ví dụ: "good.png", "bad.png", "neutral.png"
	console.log(qualityImagePath);
    // Thực hiện nước đi và thêm hình ảnh đánh giá
    clearQualityImages();
    
    DiChuyen(moveNotation[0] + moveNotation[1], moveNotation[2] + moveNotation[3]);
    var cell = document.getElementById(moveNotation[2] + moveNotation[3]);
    var analysImg = cell.getElementsByClassName("analys")[0];
    if (analysImg) {
        analysImg.src = qualityImagePath;
    }
   

    if (!isPaused && currentMoveIndex < movesData.length - 1) {
        currentMoveIndex++;
    } else {
    	isPaused = !isPaused;
        document.getElementById('pause').innerText = isPaused ? 'Continue' : 'Pause';
        clearInterval(intervalId); // Dừng chu kỳ nếu hết nước đi hoặc tạm dừng
    }
}

function pauseContinue() {
	if(currentMoveIndex >= movesData.length - 1){
		KhoiTao();
		Wait();
		currentMoveIndex = 0;
	}
    isPaused = !isPaused;
    document.getElementById('pause').innerText = isPaused ? 'Continue' : 'Pause';

    if (!isPaused) {
        // Bắt đầu hoặc tiếp tục mô phỏng ván cờ
        intervalId = setInterval(showCurrentMove, 2000); // 2 giây cho mỗi nước
    } else {
        // Dừng mô phỏng
        clearInterval(intervalId);
    }
}

function nextMove() {
    // Đảm bảo rằng mô phỏng đang dừng
    clearInterval(intervalId);
    isPaused = true;
    document.getElementById('pause').innerText = 'Continue';

    if (currentMoveIndex < movesData.length) {
    	var move = movesData[currentMoveIndex];
    	var moveNotation = move.moveNotation;
    	var betterMove = move.betterMove || "Không có";
    	var quality = move.moveQuality;
    	    // Thêm nước đi hiện tại vào danh sách "Nước đã đi"
    	var currentMoveList = document.getElementById("stepWhite").getElementsByTagName("ol")[0];
    	var moveItem = document.createElement("li");
    	moveItem.innerText = moveNotation;
    	currentMoveList.appendChild(moveItem);

    	// Thêm nước đi tốt hơn vào danh sách "Nước đi tốt hơn"
    	var betterMoveList = document.getElementById("stepBlack").getElementsByTagName("ol")[0];
    	var betterMoveItem = document.createElement("li");
    	betterMoveItem.innerText = betterMove;
    	betterMoveList.appendChild(betterMoveItem);
    	    
    	var qualityImagePath = "./template/web/Icon/" + quality.toLowerCase() + ".png"; // Ví dụ: "good.png", "bad.png", "neutral.png"
    	console.log(qualityImagePath);
    	    // Thực hiện nước đi và thêm hình ảnh đánh giá
    	clearQualityImages();
    	    
    	DiChuyen(moveNotation[0] + moveNotation[1], moveNotation[2] + moveNotation[3]);
    	var cell = document.getElementById(moveNotation[2] + moveNotation[3]);
    	var analysImg = cell.getElementsByClassName("analys")[0];
    	if (analysImg) 
    	    analysImg.src = qualityImagePath;
    	currentMoveIndex++;
    }
}

function previousMove() {
    if (currentMoveIndex > 0 && moveHistory.length > 0) {
        clearInterval(intervalId);
        isPaused = true;
        document.getElementById('pause').innerText = 'Continue';

        // Lấy thông tin từ lịch sử và khôi phục trạng thái
        var lastMove = moveHistory.pop();
        document.getElementById("i" + lastMove.trangThaiTruoc.id).src = lastMove.trangThaiTruoc.src;
        document.getElementById("i" + lastMove.trangThaiSau.id).src = lastMove.trangThaiSau.src;

        // Nếu có nhập thành, khôi phục quân Xe
        lastMove.trangThaiNhapThanh.forEach(function (state) {
            document.getElementById("i" + state.id).src = state.src;
        });
     	// Xóa dòng cuối cùng từ cả hai danh sách nước đi
        var currentMoveList = document.getElementById("stepWhite").getElementsByTagName("ol")[0];
        var betterMoveList = document.getElementById("stepBlack").getElementsByTagName("ol")[0];
        if (currentMoveList.children.length > 0 && betterMoveList.children.length > 0) {
            currentMoveList.removeChild(currentMoveList.lastChild);
            betterMoveList.removeChild(betterMoveList.lastChild);
        }
        
        currentMoveIndex--;
        // set quality
        clearQualityImages();
        if(currentMoveIndex){
        	var move = movesData[currentMoveIndex - 1];
        	var moveNotation = move.moveNotation;
        	var quality = move.moveQuality;
        	    
        	var qualityImagePath = "./template/web/Icon/" + quality.toLowerCase() + ".png"; // Ví dụ: "good.png", "bad.png", "neutral.png"
        	console.log(qualityImagePath);
        	// Thêm hình ảnh đánh giá
        	var cell = document.getElementById(moveNotation[2] + moveNotation[3]);
        	var analysImg = cell.getElementsByClassName("analys")[0];
        	if (analysImg) 
        	    analysImg.src = qualityImagePath;
        }
    }
}


function clearQualityImages() {
    var allCells = document.getElementById("BanCo").getElementsByTagName("td");
    for (var i = 0; i < allCells.length; i++) {
        var cell = allCells[i];
        var analysImg = cell.getElementsByClassName("analys")[0];
        if (analysImg) {
            analysImg.src = ""; // Xóa ảnh đánh giá
        }
    }
}
//Khởi tạo khi tài liệu đã sẵn sàng
document.addEventListener("DOMContentLoaded", function() {
    // Khởi tạo các sự kiện nút
    document.getElementById('pause').addEventListener('click', pauseContinue);
    document.getElementById('next').addEventListener('click', nextMove);
    document.getElementById('back').addEventListener('click', previousMove);
});
</script>
</html>
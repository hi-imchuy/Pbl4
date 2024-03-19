// websocket-connection.js
var socket;
var legal;

function openWebSocket(userID) {
    KhoiTao();
    WhiteOrBlack(true);
    socket = new WebSocket("ws://localhost:8080/spring-mvc/websocket?userID=" + userID);
    console.log(userID);

    socket.onopen = function() {
        console.log("WebSocket connection established");
    };

    socket.onmessage = function(event) {
        handleServerMessage(event.data);
    };

    socket.onclose = function() {
        console.log("WebSocket connection closed");
    };

    socket.onerror = function(error) {
        console.error("WebSocket Error:", error);
    };
}

function handleServerMessage(message) {
    console.log("Message from server:", message);
    // messgage = Start 2 admin 900
    console.log("Type of message:", typeof message);
    var data = message.split(' ');
    console.log(data);
    if (data[0] == "Start") {
        WhiteOrBlack(true);
        document.getElementById('username_top').textContent = data[2];
        document.getElementById('elo_top').textContent = 'Elo: ' + data[3];
        return;
    }
    var data1 = message.split(':');
    if(data1[0] == "MSG"){
    	SendMessage("competitorChat", data1[1]);
    	return;
    }
    
    processGameData(message);
    
}

function processGameData(message) {
    var data = message.split(' AND ');
    var parts = data[0];

    parseDataString(data[1]);
    console.log(legal);
    addMoveToList(parts);
    handleOpponentMove(parts[0] + parts[1], parts[2] + parts[3]);
    Nextturn();

    if (data[1] === "YOU LOSE" || data[1] === "YOU WIN") {
        alert(data[1]);
    }
}

function sendMove(fromLocation, toLocation) {
    var move = fromLocation + toLocation;
    addMoveToList(move);
    socket.send("move " + move);
}

function addMoveToList(move) {
    var list = document.getElementById("myList");
    var listItem = document.createElement("li");
    listItem.className = "result";
    listItem.textContent = move;

    list.appendChild(listItem);
}

function parseDataString(dataString) {
    let resultMap = {};
    let regex = /(\w+)=\[(.*?)\]/g;
    let match;

    while ((match = regex.exec(dataString)) !== null) {
        let key = match[1];
        let values = match[2].split(', ').map(s => s.trim());
        resultMap[key] = values;
    }
    legal = resultMap;
    filllegalMove(legal);
}

function extractMoves(data) {
    let moves = data.replace(/[\[\]]/g, '').split(', ');
    return moves;
}

function RefillMoves(Moves) {
    data = extractMoves(Moves);
    for (const move of data) {
        console.log(move);
        handleOpponentMove(move[0] + move[1], move[2] + move[3]);
        addMoveToList(move);
    }
}

document.addEventListener('DOMContentLoaded', (event) => {
    // Tìm tất cả các nút trong div có id 'message'
    const buttons = document.querySelectorAll('#message .messageButton');

    // Thêm sự kiện 'click' cho mỗi nút
    buttons.forEach(button => {
        button.addEventListener('click', function() {
            SendMessage("meChat", this.textContent);
            socket.send("MSG:" + this.textContent);
        });
    });
});
function SendMessage(who , message) {
    console.log("Sending message:", message);
    var messageElement = document.createElement("div");
    messageElement.classList.add(who); 
    messageElement.textContent = message; 
    var chatBox = document.getElementById("chat");
    chatBox.appendChild(messageElement);
    chatBox.scrollTop = chatBox.scrollHeight;
}


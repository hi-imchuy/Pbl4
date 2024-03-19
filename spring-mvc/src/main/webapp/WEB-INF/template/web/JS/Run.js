var isClick = false, isWhite = true, Done = true, EndGame = false;
var Location = "";

var legalMoves = {
	g1 : [ 'h3', 'f3' ],
	b1 : [ 'c3', 'a3' ],
	h2 : [ 'h3', 'h4' ],
	g2 : [ 'g3', 'g4' ],
	f2 : [ 'f3', 'f4' ],
	e2 : [ 'e3', 'e4' ],
	d2 : [ 'd3', 'd4' ],
	c2 : [ 'c3', 'c4' ],
	b2 : [ 'b3', 'b4' ],
	a2 : [ 'a3', 'a4' ]
};



function Start(){
	Done = true;
}

function filllegalMove(legal) {
	legalMoves = legal;
}

function getValidMoves(pieceId) {
	return legalMoves[pieceId];
}
function isMoveLegal(pieceId, move) {
    var validMoves = getValidMoves(pieceId);
    return validMoves ? validMoves.includes(move) : false;
}

function highlightPossibleMoves(moveIds) {
	moveIds.forEach(function(square) {
		document.getElementById(square).style.backgroundColor = 'yellow';
	});
}


function KhoiTao2(board) {
	VeBanCoTrangDen();
	DatCo2(board);
	isClick = false;
}
function KhoiTao() {
	VeBanCoTrangDen();
	DatCo();
	isClick = false;
}

function WhiteOrBlack(isW) {
	Done = isW;
	isWhite = isW;
}

function Nextturn() {
	Done = true;
}

function Wait(){
	Done = false;
	console.log(Done + " " + "Wait func");
}

function Click(id) {
	console.log(Done);
	console.log(isWhite);
	if (!Done || EndGame)
		return;

	var X = id.charAt(0);
	var Y = id.charAt(1);
	isClick = !isClick;

	if (isClick) {
		console.log("here!");
		if (isCoTrang(X, Y) !== isWhite) {
			isClick = false;
			return;
		}
		var pieceName = GetName(id);
		Location = id;
		VeBanCoTrangDen();
		var validMoves = getValidMoves(id);
		console.log(validMoves);
		highlightPossibleMoves(validMoves);
		if (pieceName == "Rong")
			isClick = false;
	} else {
		if (isMoveLegal(Location, id)) {
			DiChuyen(Location, id)
			Done = false;
			sendMove(Location, id);
		}
		VeBanCoTrangDen();
	}
}

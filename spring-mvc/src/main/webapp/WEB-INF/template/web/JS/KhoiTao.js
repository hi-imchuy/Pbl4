var imported = document.createElement('script');
imported.src = 'template/web/JS/Sources.js';
document.head.appendChild(imported);

//
function VeBanCoTrangDen() {
	for (var j = 1; j <= 8; j++) {
		for (var i = 'a'.charCodeAt(0); i <= 'h'.charCodeAt(0); i++) {
			var kytu = String.fromCharCode(i);
			var id = kytu + j;
			var laOTrang = (i + j) % 2 == 1;
			document.getElementById(id).style.backgroundColor = laOTrang ? Mau.Trang : Mau.Den;
		}
	}
}

function DatCo() {
    for (var i = 1; i < 9; i++) {
        for (var j = 1; j < 9; j++) {
            var cellId = "i" + String.fromCharCode('a'.charCodeAt(0) + j - 1) + i;
            document.getElementById(cellId).src = "template/web/QuanCo/Rong.png";
        }
    }

    // Đặt quân cờ đen
    for (var j = 1; j < 9; j++) {
        var pawnId = "i" + String.fromCharCode('a'.charCodeAt(0) + j - 1) + "7";
        document.getElementById(pawnId).src = CoDen.Tot;
    }

    var blackPieces = [CoDen.Xe, CoDen.Ma, CoDen.Tuong, CoDen.Hau, CoDen.Vua, CoDen.Tuong, CoDen.Ma, CoDen.Xe];
    for (var j = 1; j < 9; j++) {
        var pieceId = "i" + String.fromCharCode('a'.charCodeAt(0) + j - 1) + "8";
        document.getElementById(pieceId).src = blackPieces[j - 1];
    }

    // Đặt quân cờ trắng
    for (var j = 1; j < 9; j++) {
        var pawnId = "i" + String.fromCharCode('a'.charCodeAt(0) + j - 1) + "2";
        document.getElementById(pawnId).src = CoTrang.Tot;
    }

    var whitePieces = [CoTrang.Xe, CoTrang.Ma, CoTrang.Tuong, CoTrang.Hau, CoTrang.Vua, CoTrang.Tuong, CoTrang.Ma, CoTrang.Xe];
    for (var j = 1; j < 9; j++) {
        var pieceId = "i" + String.fromCharCode('a'.charCodeAt(0) + j - 1) + "1";
        document.getElementById(pieceId).src = whitePieces[j - 1];
    }
}

function DatCo2(boardstr) {
    // Mảng ánh xạ ký tự sang đường dẫn hình ảnh quân cờ
//    var pieceImages = {
//        'r': CoDen.Xe, 'n': CoDen.Ma, 'b': CoDen.Tuong, 'q': CoDen.Hau, 'k': CoDen.Vua, 'p': CoDen.Tot,
//        'R': CoTrang.Xe, 'N': CoTrang.Ma, 'B': CoTrang.Tuong, 'Q': CoTrang.Hau, 'K': CoTrang.Vua, 'P': CoTrang.Tot,
//        '.': CoDen.Rong // Ô trống
//    };
//
//    var rows = boardstr.split('?');
//    for (var i = 0; i < rows.length; i++) {
//        var cells = rows[i].split(' ');
//        for (var j = 0; j < cells.length; j++) {
//            var cellId = "i" + String.fromCharCode('a'.charCodeAt(0) + j) + (8 - i);
//            var pieceChar = cells[j];
//            var imgSrc = pieceImages[pieceChar] || CoDen.Rong; // Sử dụng hình ảnh rỗng nếu không tìm thấy ký tự
//            document.getElementById(cellId).src = imgSrc;
//        }
//    }
}


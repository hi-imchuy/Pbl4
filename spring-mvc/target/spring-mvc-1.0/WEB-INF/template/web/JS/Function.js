function LuotDi(){
	var Temp = document.getElementById("iCoDen").src;
	document.getElementById("iCoDen").src = document.getElementById("iCoDo").src;
	document.getElementById("iCoDo").src = Temp;
}


function DoiMau(X, Y){
	document.getElementById(X.toString() + Y.toString()).style.backgroundColor = "#F6CD61";
}

function GetColor(id){
	var rgb = document.getElementById(id).style.backgroundColor;
	var Temp = rgb.substring(rgb.indexOf('(') + 1, rgb.length - 1);
	while(Temp.indexOf(' ') != -1){
		Temp = Temp.replace(" ", "");
	}
	return Temp; //rgb
}

function GetName(id){
	try{
		var src = document.getElementById("i" + id).src;
		var Name = src.substring(src.lastIndexOf('/') + 1, src.length - 4);
		Name = Name.substring(0, Name.indexOf('_')); // Sửa lỗi từ pieceName sang Name
		console.log(Name);
		return Name;
	}catch(err){
		// Có thể bạn muốn xử lý lỗi theo cách khác
		console.error(err);
		return ""; // Trả về chuỗi rỗng nếu có lỗi
	}
}

function isCoTrang(X, Y){
	try {
		var id = X + Y; // Sửa lại để tạo id từ X và Y
		var src = document.getElementById("i" + id).src;
		var Color = src.substring(src.lastIndexOf('/') + 1, src.length - 4);
		Color = Color.substring(Color.indexOf('_') + 1); // Sửa lỗi từ pieceName sang Color và lấy phần tử trước _
		console.log(Color);
		return Color.localeCompare("Trang") == 0;
	} catch (err) {
		console.error(err);
		return false; // Trả về false nếu có lỗi
	}
}


function isCoDen(X, Y){
	var Temp = GetName(X.toString() + Y);
	Temp = Temp.substring(Temp.indexOf('_') + 1 , Temp.length);
	return Temp.localeCompare("Den") == 0 ? true : false;
}

function isRong(X, Y){
	var Temp = GetName(X.toString() + Y);
	Temp = Temp.substring(Temp.indexOf('_') + 1 , Temp.length);
	return Temp.localeCompare("Rong") == 0 ? true : false;
}

function isBien(X, Y){
	if(X < 1 || X > 8)
		return true;
	else if(Y < 1 || Y > 8)
		return true;
	else
		return false;
}

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
    if (tenQuanCo === "Vua" && isNhapThanh(id, idMoi)) {
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
    return true;
}

function handleOpponentMove(id, idMoi){
	var tenQuanCo = GetName(id);
    if (tenQuanCo === "Vua" && isNhapThanh(id, idMoi)) {
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
    document.getElementById("i" + idMoi).src = document.getElementById("i" + id).src;
    document.getElementById("i" + id).src = CoTrang.Rong; // Sử dụng CoTrang.Rong nếu đó là quân trắng
}	





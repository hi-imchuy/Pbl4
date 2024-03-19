function Xe(id){
	var X = id.charAt(0);
	var Y = id.charAt(1);
	switch(isCoTrang(X, Y))
	{
// C? d?
		case true:
		
			// Ði lên trên
			var X_Top = parseInt(X);
			while(!isBien(X_Top - 1, Y) && !isCoTrang(X_Top - 1, Y)){
				DoiMau(X_Top - 1, Y);
				if(isCoDen(X_Top - 1, Y))
						break;
				X_Top -= 1; 
			}
			
			// Ði xu?ng du?i
			var X_Bottom = parseInt(X);
			while(!isBien(X_Bottom +1, Y) && !isCoTrang(X_Bottom + 1, Y)){
				DoiMau(X_Bottom + 1, Y);
				if(isCoDen(X_Bottom + 1, Y))
						break;
				X_Bottom += 1; 
			}
			
			// Ði qua trái
			var Y_Left = parseInt(Y);
			while(!isBien(X, Y_Left - 1) && !isCoTrang(X, Y_Left - 1)){
				DoiMau(X, Y_Left - 1);
				if(isCoDen(X, Y_Left - 1))
						break;
				Y_Left -= 1; 
			}
			
			// Sang ph?i
			var Y_Right = parseInt(Y);
			while(!isBien(X, Y_Right + 1) &&!isCoTrang(X, Y_Right + 1)){
				DoiMau(X, Y_Right + 1);
				if(isCoDen(X, Y_Right + 1))
						break;
				Y_Right += 1; 
			}
		break;
		
		
// C? Den	
		case false:
			// Ði lên trên
			var X_Top = parseInt(X);
			while(!isBien(X_Top - 1, Y) && !isCoDen(X_Top - 1, Y)){
				DoiMau(X_Top - 1, Y);
				if(isCoTrang(X_Top - 1, Y))
						break;
				X_Top -= 1; 
			}
			
			// Ði xu?ng du?i
			var X_Bottom = parseInt(X);
			while(!isBien(X_Bottom + 1, Y) && !isCoDen(X_Bottom + 1, Y)){
				DoiMau(X_Bottom + 1, Y);
				if(isCoTrang(X_Bottom + 1, Y))
						break;
				X_Bottom += 1; 
			}
			
			// Ði qua trái
			var Y_Left = parseInt(Y);
			while(!isBien(X, Y_Left - 1) && !isCoDen(X, Y_Left - 1)){
				DoiMau(X, Y_Left - 1);
				if(isCoTrang(X, Y_Left - 1))
						break;
				Y_Left -= 1; 
			}
			
			// Sang ph?i
			var Y_Right = parseInt(Y);
			while(!isBien(X, Y_Right + 1) && !isCoDen(X, Y_Right + 1)){
				DoiMau(X, Y_Right + 1);
				if(isCoTrang(X, Y_Right + 1))
						break;
				Y_Right += 1; 
			}
		break;
		default:
	}
	
}
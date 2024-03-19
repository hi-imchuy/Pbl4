function Vua(id){
		var X = id.toString().charAt(0);
		var Y = id.toString().charAt(1);
		
		var X_ = parseInt(X);
		var Y_ = parseInt(Y);

		switch(isCoTrang(X, Y))
		{
//C? d?
			case true:
				//Ði xu?ng
				if( !isBien(X_ - 1, Y_) && !isCoTrang(X_ - 1, Y_)) {
					DoiMau(X_ - 1, Y);
				}
				
				//Ði xu?ng ph?i 
				if( !isBien(X_ - 1, Y_ + 1) && !isCoTrang(X_ - 1,Y_ + 1)) {
					DoiMau(X_ - 1, Y_ + 1);
				}
				
				// Ði xu?ng trái			
				if( !isBien(X_ - 1, Y_ - 1) && !isCoTrang(X_ - 1,Y_ - 1)) {
					DoiMau(X_ - 1, Y_ - 1);
				}

				
				//Ði lên	
				if( !isBien(X_ + 1, Y_) && !isCoTrang(X_ + 1, Y_)) {
					DoiMau(X_ + 1, Y_);
				}
				
				// Ði lên ph?i
				if( !isBien(X_ + 1, Y_ + 1) && !isCoTrang(X_ + 1, Y_ + 1)) {
					DoiMau(X_ + 1, Y_ + 1);
				}

				// Ði lên trái
				if( !isBien(X_ + 1, Y_ - 1) && !isCoTrang(X_ + 1, Y_ - 1)) {
					DoiMau(X_ + 1, Y_ - 1);
				}
				
				//qua trái
				if(!isBien(X_, Y_ - 1) && !isCoTrang(X_, Y_ - 1)){
				DoiMau(X_, Y_- 1); 
				}
				
				// qua phai 
				if(!isBien(X_, Y_ + 1) && !isCoTrang(X_, Y_ + 1)){
				DoiMau(X_, Y_ + 1);
			}
			break;
//C? Ðen
			case false:

				// Ði xu?ng
				if( !isBien(X_ - 1, Y_) && !isCoDen(X_ - 1, Y_)) {
					DoiMau(X_ - 1, Y);
				}
				
				//Ði xu?ng ph?i 
				if( !isBien(X_ - 1, Y_ + 1) && !isCoDen(X_ - 1,Y_ + 1)) {
					DoiMau(X_ - 1, Y_ + 1);
				}
				
				// Ði xu?ng trái			
				if( !isBien(X_ - 1, Y_ - 1) && !isCoDen(X_ - 1,Y_ - 1)) {
					DoiMau(X_ - 1, Y_ - 1);
				}

				
				//Ði lên	
				if( !isBien(X_ + 1, Y_) && !isCoDen(X_ + 1, Y_)) {
					DoiMau(X_ + 1, Y_);
				}
				
				// Ði lên ph?i
				if( !isBien(X_ + 1, Y_ + 1) && !isCoDen(X_ + 1, Y_ + 1)) {
					DoiMau(X_ + 1, Y_ + 1);
				}

				// Ði lên trái
				if( !isBien(X_ + 1, Y_ - 1) && !isCoDen(X_ + 1, Y_ - 1)) {
					DoiMau(X_ + 1, Y_ - 1);
				}
				
				//qua trái
				if(!isBien(X_, Y_ - 1) && !isCoDen(X_, Y_ - 1)){
					DoiMau(X_, Y_- 1); 
				}
				
				// qua phai 
				if(!isBien(X_, Y_ + 1) && !isCoDen(X_, Y_ + 1)){
					DoiMau(X_, Y_ + 1);
				}
				break;
	}
}
		
		
		
	
$(document).ready(function(){
	initializeFilter();
	if(admin){
		bindDeleteButtons();
		bindDatePickers();
		bindSubmits();
		enableEditable();
		recalculateKasum();
		
	}else{
		
	}
	
	
});

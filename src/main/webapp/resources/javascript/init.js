$(document).ready(function(){
	if(admin){
		bindDeleteButtons();
		bindDatePickers();
		bindSubmits();
		enableEditable();
		recalculateKasum();
		
	}else{
		
	}
	
});

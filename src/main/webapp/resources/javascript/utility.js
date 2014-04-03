
function addSumma(key, value){
	if(key === "summaKM"){
		summaKMDiv = $("[data-id=totalSummaKM]");
		newSumma = summaKMDiv.val() + value;
		summaKMDiv.val("newSumma");
	}
	
}
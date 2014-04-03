
function addSumma(key, value){
	if(key === "summaKM"){
		summaKMDiv = $("[data-name=totalSummaKM]");
		newSumma = summaKMDiv.text() + value;
		summaKMDiv.text(newSumma);
		console.log(summaKMDiv.text());
	}
	
}

function recalculateSumma(type){
	var summa = 0;
	console.log("div." + type);
	$("div." + type).each(function(){
		var see = $(this);
		console.log(summa);
		console.log(see);
		console.log("pass")
		summa += see.text();
		console.log(summa);
	});
	if(type === "summaKM"){
		$("span.totalSummaKM").text(summa);
	}
}
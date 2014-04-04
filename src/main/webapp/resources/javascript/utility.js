
function addSumma(key, value){
	if(key === "summaKM"){
		summaKMDiv = $("[data-name=totalSummaKM]");
		newSumma = summaKMDiv.text() + value;
		summaKMDiv.text(newSumma);
		console.log(summaKMDiv.text());
	}
	
}

function recalculateSumma(summaType, arvedType){
	var summa = 0;
	console.log("div." + summaType + "[data-type=" + arvedType + "]");
	var summad = $("div." + summaType + "[data-type=" + arvedType + "]");
	console.log(summad);
	$("div." + summaType + "[data-type=" + arvedType + "]").each(function(){
		var see = $(this);
		console.log(summa);
		console.log(see);
		console.log("pass");
		var number= see.text().trim();
		number = parseFloat(number, 10);
		summa += number;
		console.log(summa);
		
	});
	var span = $("span[data-name=total" + summaType + "][data-type=" + arvedType + "]");
	console.log(span);
	$("span[data-name=total" + summaType + "][data-type=" + arvedType + "]").text(summa);
	if(arvedType === "summaKM"){
		$("span.totalSummaKM").text(summa);
	}
}
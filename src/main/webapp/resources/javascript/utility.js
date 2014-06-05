
function addSumma(key, value){
	if(key === "summaKM"){
		summaKMDiv = $("[data-name=totalSummaKM]");
		newSumma = summaKMDiv.text() + value;
		summaKMDiv.text(newSumma);
		console.log(summaKMDiv.text());
	}
	
}

function recalculateSummas(arvedType){
	recalculateSumma("summaKM", arvedType);
	recalculateSumma("summaIlmaKM", arvedType);
}

function recalculateSumma(summaType, arvedType){
	var summa = 0;
	
	$("div." + summaType + "[data-type=" + arvedType + "]").each(function(){
		var see = $(this);

		console.log("pass");
		var number= see.text().trim();
		number = parseFloat(number, 10);
		summa += number;
		console.log(summa);
		
	});

	$("span[data-name=total" + summaType + "][data-type=" + arvedType + "]").text(summa);
	recalculateKasum();
}

function recalculateKasum(){
	var muugiSumma = parseFloat($("span[data-name=totalsummaIlmaKM][data-type=m]").text().trim());
	var ostuSumma  = parseFloat($("span[data-name=totalsummaIlmaKM][data-type=o]").text().trim());
	if(isNaN(muugiSumma)){
		muugiSumma = 0;
	}
	if(isNaN(ostuSumma)){
		ostuSumma = 0;
	}
	var kasum = muugiSumma - ostuSumma;
	kasum = kasum.toFixed(2);
	console.log(muugiSumma);
	console.log(ostuSumma);
	console.log(kasum);
	if(!isNaN(kasum)){
		$("span#objektKasum").text("Kasum üle nähtavate arvete: " + kasum);
		
	}
	
}
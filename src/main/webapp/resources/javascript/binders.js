function initializeFilter(){
	$(".filterDate").each(function(){
		$(this).AnyTime_picker({format: "%d/%m/%Y"});
		
	});
	$(".periodSelect").on("input propertychange, change", function(){
		$(".filterDate").val("");
	});
}

function bindDatePickers(){
	$(".kuuPaev").each(function(){
		$(this).AnyTime_picker({format: "%d/%m/%Y"});
		
	});
}
function bindDatePicker(toBind){
	toBind.AnyTime_picker({format: "%d/%m/%Y"});
}
function bindDeleteButtons(){
	console.log("bindingDeletes");
	bindDelete($("a.deleteArve"));
}

function enableEditable(){
	$("div.editable").attr("contenteditable", true);
	bindEditables();
	
}

function bindEditables(){
	bindEditable($(".editable"));
}

function bindEditable(toBind){
	toBind.on("input propertychange change", function(){
		checkInput(this);
	});
}

function checkInput(toCheck){
	console.log(toCheck);

	var id = $(toCheck).attr("data-id");
	var type = $("#arveRow" + id).attr("data-type");
	console.log(id);
	var formData = new FormData();
	var dataDivs = $("[data-id="+ id +"]");
	dataDivs.each(function(){
		var value = $(this).text().trim();
		var key = $(this).attr("data-name");
		console.log(key);
		console.log(value);
		if(key === "kuuPaev"){
			var kuuPaev = $("input#" + type + "kuuPaev" + id).val();
			console.log(kuuPaev);
			formData.append(key, kuuPaev);
		}else if(key === "tasutud"){
			if($("input[data-id=" +id +"][data-name=tasutud]").is(":checked")){
				console.log("on");
				formData.append(key, "on");
			} 
		}else{
			formData.append(key, value);
		}
		
	});
	console.log(type);
	formData.append("arvedType", type);
	
	formData.append("id", id);
	formData.append("js", true);
	/**
	 * Add data-type to summarows. Use datatype + class to createsummas
	 * 
	 */
	var see = $(toCheck).attr("data-name");
	console.log(see);
	recalculateSumma(see, type);
	sendArved(formSubmitUrl, formData, type, null);
	
}

function bindDelete(toBind){
	console.log("bindingDelete");
	toBind.click(function(e){
		console.log("clickedDelete");
		e.preventDefault();
		deleteUrl = $(this).attr("href");
		$.ajax({
			url: deleteUrl,
			type: 'GET',
			contentType: false,
			success: function(data){
				console.log(data);
				if(data != 0){
					selector = "#arveRow" + data;
					$(selector).remove();
				}
			}
		});
	});
	
	console.log("bindedDelete");
}

function sendArved(formSubmitUrl, formData, type,  form){
	$.ajax({
		  url: formSubmitUrl,
		  
		  data: formData,
		  processData: false,
		  contentType: false,
		  type: 'POST',
		  success: function(data){
			  
			  
			  jData = $(data); 
			  kuuPaev = jData.find("input.kuuPaev");
			  
			 
			  
			  //console.log(deleteButton);
			  if(form != null){
				  selector = "#" + type + "table tr:first";
				  $(selector).before(data);
				  deleteButton = jData.filter("a.deleteArve");
				  bindDatePicker(kuuPaev);
				  bindDelete(deleteButton);
				  bindDeleteButtons();
				  enableEditable();
				  $(".hiddenTable").show();
				  recalculateSummas(type);
				  form.trigger("reset");
			  }
		  }
		});
	
}
function bindSubmits(){
	$("form.arveForm").submit(function (e){
		e.preventDefault();
		form = $(this);
		type = form.find("input[name=arvedType]").val();
		
		formData = new FormData(this);
		formData.append("js", true);
		sendArved(formSubmitUrl, formData, type, form);
		
		
	});
	
}
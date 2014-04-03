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
	div = $(toCheck);
	id = div.attr("data-id");
	text = div.text().trim();
	type = $("#arveRow" + id).attr("data-type");
	console.log(text);
	console.log(id);
	formData = new FormData();
	dataDivs = $("[data-id="+ id +"]");
	dataDivs.each(function(){
		value = $(this).text().trim();
		key = $(this).attr("data-name");
		console.log(key);
		console.log(value);
		if(key == "kuuPaev"){
			kuuPaev = $("input#" + type + "kuuPaev" + id).val();
			console.log(kuuPaev);
			formData.append(key, kuuPaev);
		}else if(key == "tasutud"){
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
	kuuPaev = $("input#" + type + "kuuPaev" + id).val();
	console.log(kuuPaev);
	formData.append("id", id);
	formData.append("js", true);
	
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
				  form.trigger("reset");
			  }
		  }
		});
	
}
function bindSubmits(){
	$("form").submit(function (e){
		e.preventDefault();
		form = $(this);
		type = form.find("input[name=arvedType]").val();
		
		formData = new FormData(this);
		formData.append("js", true);
		sendArved(formSubmitUrl, formData, type, form);
		
		
	});
	
}
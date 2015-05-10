
function notifyStart(){
	$.ajax({
		url:"http://localhost:4567/started"		
	}).done(function(msg){
		alert(msg);
	});
}

function log(step,status){
	var image="";
	if(status.toLowerCase()=== cStatus.Failed)
	{
		image = takeScreenShot();
	}
	
	$.ajax({
		url:"http://localhost:4567/started";
	  	data: "<result><step>"+step+"</step><status>"+status+"</status><snap>"+image+"</snap></result>", 
	    type: 'POST',
	    contentType: "text/xml",
	    dataType: "text",
	    success : parse,
	    error : function (xhr, ajaxOptions, thrownError){  
	        console.log(xhr.status);          
	        console.log(thrownError);
	    } 
	});
}

function notifyEnd(){
	$.ajax({
		url:"http://localhost:4567/ended'"		
	}).done(function(msg){
		alert(msg);
	});
}

function sendMail(){
	$.ajax({
		url:"http://localhost:4567/sendmail'"		
	}).done(function(msg){
		alert(msg);
	});
}

    function loadIframePrint(){
    

    	var pf_el = document.getElementById("printing_frame");
    
    	
    	pf_el.onload= function(){

    		pf_el.contentWindow.print();
    	}
    	pf_el.setAttribute("src","http://www.cbc.ca/news/canada/manitoba/york-university-fails-to-support-sex-assault-victims-woman-says-1.2979396");
    }
    

    function   ge(param){
    	return document.getElementById(param);
    }
    function checkDateFormat(date_candidate){
    	var patt = /[0123456789]{1,2}[\-]{1}(Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Oct|Nov|Dec|JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|OCT|NOV|DEC|jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec){1,}[\-]{1}[0123456789]{4}/
    	return patt.test(date_candidate);
    }
    
    
    
    function checkDate(){
    	if(checkInputURI()){
    		var in_date = ge("date").value;
    		var err_msg_el = ge("err_msg");
    		if (err_msg_el.hasChildNodes()) {
				err_msg_el.removeChild(err_msg_el.childNodes[0]);
			}
    		if(in_date==null || in_date.length<1){
    		
    			err_msg_el.appendChild(ct("please add the date"));
    			err_msg_el.setAttribute("class","err_alert");
    			return false;
    		}else{
    			if(!checkDateFormat(in_date)){
    				err_msg_el.appendChild(ct("use date format 01-Jan-1999"));
        			err_msg_el.setAttribute("class","err_alert");
    			}else{
    				return true;
    			}
    		}
    	}else{
    		return false;
    	}

    }
    
    function checkSavedFile(){
    	//console.log("checksavedfile");
    	if(checkAuth() && checkDate() && checkInputURI()){
    		var file_name = ge("saved_file").value;
    		if(file_name!=null && file_name.length>0){
    			sendToServer();
    		}
    	}
    }
    /*
    function sendParam(str0, str1) {
     
        var xhttp;
        xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange = function() {
            if (xhttp.readyState == 4 && xhttp.status == 200) {
                if (xhttp.responseText.length > 100) {
                    
                } else {
                   
                }
            }
        };
        xhttp.open("GET", "LoadMapAsync?" + str0 + "=" + str1, true);
        xhttp.send();
    }
    */
    
   function sendTest(){
    	var uri = ge("input_uri").value;
      	if(uri!=null && uri.length>0){
      		var url = "http://localhost:8080/archive_tool/Remote?uri_t="+uri;
        	$.get(encodeURI(url), function(data) {
        	
        	 updateSkip(data, uri);
        	});
      	}
   } 
    
   function sendToServer(){
	   
	   
	var uri = ge("input_uri").value;
  	var d = ge("date").value;
  	var a = ge("author").value;
  	var sf= ge("saved_file").value;
  		
  		
    var url = "http://localhost:8080/archive_tool/Remote?uri="+uri+"&d="+d+"&a="+a+"&sf="+sf;
    
    
    $.get(encodeURI(url), function(data) {
        //myArr = JSON.parse(data);   // parsing JSON
    	console.log("["+data+"]");
    	 updateState(data);
    });
   }
   function resetForm(){
	   var err_msg_el = ge("err_msg");
   		if (err_msg_el.hasChildNodes()) { err_msg_el.removeChild(err_msg_el.childNodes[0]); }
   		ge("input_uri").value="";
   		ge("date").value="";
   		ge("author").value="";
   	 ge("saved_file").value="";
   }
   
   function updateSkip(param, uri){
	   console.log("["+param+"]");
	   var err_msg_el = ge("err_msg");   	
	   if (err_msg_el.hasChildNodes()) {
   	    	err_msg_el.removeChild(err_msg_el.childNodes[0]);
   	   }
	   if(param.localeCompare("NO")!=0){
		   err_msg_el.appendChild(ct("SKIP"));
		   err_msg_el.setAttribute("style","color:orange; font-size:18pt");
		   ge("input_uri").value="";
		   ge("input_uri").focus();
		 
	   }
	   if(param.localeCompare("NO")==0){
		   var win = window.open(uri, '_preview');
	    	// win.focus();
	   }
   }
   function updateState(param){
	   var err_msg_el = ge("err_msg");   	
	   if (err_msg_el.hasChildNodes()) {
   	    	err_msg_el.removeChild(err_msg_el.childNodes[0]);
   	   }
	   if(param.localeCompare("NO")!=0){
		   err_msg_el.appendChild(ct("OK"));
		   err_msg_el.appendChild(ce("br"));
		   err_msg_el.appendChild(ct(param));
		   err_msg_el.setAttribute("style","color:green; font-size:12pt");
		   var a_el = ce("a");
		   a_el.setAttribute("href","#");
		   a_el.addEventListener("click", function() {
		        resetForm();
		   });
	   }else{
		   err_msg_el.appendChild(ct("problem"));
		   err_msg_el.setAttribute("style","color:red;font-size:18pt");
	   }
   }
   
   function ce(param){
	   return document.createElement(param);
   }
    
    function checkLoadForPrint(){
    	
    	if(checkAuth() && checkDate() && checkInputURI()){
        	if(checkDate() && checkAuth()){
        		
        		var in_uri = ge("input_uri").value;
        		if(in_uri!=null && in_uri.length>0){
        			win = window.open(in_uri, 'google','width=800,height=600,status=0,toolbar=0'); 
        		}else{
        			console.log("in_uri was null;");
        		}
        	}

        	if(win!=null){
        		//win.onbeforeprint = function() {
        			//console.log('BEFORE the user prints.');
        		//	alert("before");
        		//};
        		//win.onafterprint = function() {
        			//console.log('AFTER the user prints');   
        		//	alert("after");
        	//	};
        		var timer = setInterval(function() { 
        			if(win.closed) {
        				clearInterval(timer);
        				//alert('closed');
        			}
        		}, 1000);
        	}
    	}
    }
    function driverprint(){ 
    	document.title = "Sam"; 
    	window.print(); 
    
    }
    function checkInputURI(){
    	var uri_val = ge("input_uri").value;
    	return (uri_val!=null && uri_val.length>0);
    }
    
    function checkAuth(){
     
    	var in_author = ge("author").value;
    	var err_msg_el = ge("err_msg");
    	if(in_author==null || in_author.length<1){
    		if (err_msg_el.hasChildNodes()) {
    	    		err_msg_el.removeChild(err_msg_el.childNodes[0]);
    	    }
    		err_msg_el.appendChild(ct("please add the author"));
    		err_msg_el.setAttribute("class","err_alert");
    		return false;
    	}else{
    		return true;
    	}
    }
    function ct(param){
    	return document.createTextNode(param);
    }
    function ShowWindow() { 
    	var in_uri = ge("input_uri").value;
    	sendTest();
    }
    

    function getWindowWidth(){
    	var w = window,
        d = document,
        e = d.documentElement,
        g = d.getElementsByTagName('body')[0],
        x = w.innerWidth || e.clientWidth || g.clientWidth;
       // y = w.innerHeight|| e.clientHeight|| g.clientHeight;
    	return x; 
    }
    function getWindowHeight(){
    	var w = window,
        d = document,
        e = d.documentElement,
        g = d.getElementsByTagName('body')[0],
        //x = w.innerWidth || e.clientWidth || g.clientWidth;
        y = w.innerHeight|| e.clientHeight|| g.clientHeight;
    	return y; 
    }
    
    
    
var map;
var markersArray={};
//7.8784551,79.5790252
var myCenter = new google.maps.LatLng(7.8784551,79.5790252);
var json = null;
var jsonArray = {};
function initialize()
{
	var mapProp = {
	  center:myCenter,
	  zoom:8,
	  mapTypeId:google.maps.MapTypeId.ROADMAP
	  };
		map=new google.maps.Map(document.getElementById("googleMap")
	  ,mapProp);
}
function placeMarker(){
		var date = new Date();
		ajax('getMonitorJson?time='+date.getTime(),function(str){
		json = eval(str);
		});
		var id ;
		// alert(getPropertyCount(markersArray));
		var jslength=0;

		for(var js2 in json){
		jslength++;

		}
	if(getPropertyCount(markersArray)!=jslength){
		for(var i=0;i<json.length;i++){
			id = json[i].id;
			var obj = new Object();
			obj.id=json[i].id;
			obj.alarm = json[i].alarm;
			obj.latitude=json[i].latitude;
			jsonArray[id]=obj;
		 (function(){
			 var p = i  ;
			 var id1 = id;
			 var iconstr=null;
			 if(json[i].alarm==2) iconstr="images/marker.png";	
			 if(json[i].alarm==0) iconstr="images/marker_green.png";
			 if(json[i].alarm==1) iconstr="images/marker1.png";
		 	var marker = new google.maps.Marker({
				position:new google.maps.LatLng(json[i].latitude,json[i].longitude),
				
				icon:iconstr
				});
			
		     var infowindow = new google.maps.InfoWindow({
	    		content:json[p].id+""
	 		 });  
		   google.maps.event.addListener(marker,'mouseover',function(){
				infowindow.open(map,marker);
			});	
		   google.maps.event.addListener(marker,'mouseout',function(){
		   		infowindow.close(map,marker);
		   });
		   google.maps.event.addListener(marker,'click',function(){
		   		openpane(id1);
		   });
		   marker.setMap(map);
			markersArray[id] = marker;
		  })(); 
  
		}
		}
		else{
		for(var i=0;i<json.length;i++){
			id = json[i].id;
			if(jsonArray[id].id==id){
				//判断两次状态的改变
				if(jsonArray[id].alarm!=json[i].alarm){
					var obj = new Object();
					obj.id=json[i].id;
					obj.alarm = json[i].alarm;
					obj.latitude=json[i].latitude;
					jsonArray[id]=obj;
					(function(){
						var id1 =id;
						 var p = i;
						 var iconstr=null;
						 if(json[i].alarm==2) iconstr="images/marker.png";	
						 if(json[i].alarm==0) iconstr="images/marker_green.png";
						 if(json[i].alarm==1) iconstr="images/marker1.png";
			
					 	var marker = new google.maps.Marker({
							position:new google.maps.LatLng(json[i].latitude,json[i].longitude),
							
							icon:iconstr
							});
						
					     var infowindow = new google.maps.InfoWindow({
				    		content:json[p].id+""
				 		 });  
					   google.maps.event.addListener(marker,'mouseover',function(){
							infowindow.open(map,marker);
						});	
					   google.maps.event.addListener(marker,'mouseout',function(){
					   		infowindow.close(map,marker);
					   });
					   google.maps.event.addListener(marker,'click',function(){
					   		openpane(id1);
					   });
			var m = markersArray[id];
			 	// alert(m instanceof google.maps.Marker);
			 	if(m){
			 		m.setMap(null);
			 	}
		   marker.setMap(map);
			markersArray[id] = marker;
		  		})();
				}
			}
		}
		}
	}
function getPropertyCount(o){  
   var n, count = 0;  
   for(n in o){  
      if(o.hasOwnProperty(n)){  
         count++;  
      }  
   }  
   return count;  
}  
function clearOverlays() {
    if (markersArray) {
      for (i in markersArray) {
        markersArray[i].setMap(null); 
      }
    }
  }
google.maps.event.addDomListener(window, 'load', initialize);
setInterval(placeMarker, 10000);
var paneTimer;
function flushpane1(){
	alert(1);
}
//信息界面
function openpane(id){
	//如果有轮询，先清除
	if(paneTimer){
		clearInterval(paneTimer);
	}
	
	var monitorInfo= null;
 	var pane = document.getElementById("widget-pane");
 	var tsf = pane.style.transform;
 	if (tsf=='translateX(0px)') {
// 		pane.style.transform="translateX(-408px)";
 		flushpane(id);
 		
 	}else if(id){
 		flushpane(id);		
 		pane.style.transform="translateX(0px)";
 	}
 	//var p_id
 	paneTimer = setInterval('flushpane('+id+')', 10000);
 	//paneTimer = setInterval(function(){alert(1)},1000);
 }

//信息界面刷新
function flushpane(id){
	var date = new Date();
		$.get('getMonitorInfo?id='+id+'&time='+date.getTime(),function(data,status){
			monitorInfo = eval('('+data+')');
			var track1="A:"+monitorInfo.track1_a+"    B:"+monitorInfo.track1_b+"    up:"+monitorInfo.track1_up+"    center:"+monitorInfo.track1_center+"    down:"+monitorInfo.track1_down;
			var track2="A:"+monitorInfo.track2_a+"    B:"+monitorInfo.track2_b+"    up:"+monitorInfo.track2_up+"    center:"+monitorInfo.track2_center+"    down:"+monitorInfo.track2_down;
			
			
			$("#track1_info").text(track1);
			$("#track2_info").text(track2);
			$("#updatetime").text(monitorInfo.updateTime);
			$("#track_id").text(monitorInfo.id);
			
			if(monitorInfo.sound==1){
				if($("#sound_box").is(":checked")==false){
					$("#sound_box").prop("checked",true);
				}
			}else{
				if($("#sound_box").is(":checked")==true){
					$("#sound_box").prop("checked",false);
				}
			}
			if($("#power_icon").is(".power_icon_0")){
				
			}else{
				$("#power_icon").addClass("power_icon_0");
			}
			if(monitorInfo.altpower==1){
				if($("#power_icon").is(".power_icon_0")){
					$("#power_icon").removeClass("power_icon_0");
					$("#power_icon").addClass("power_icon_1");
				}	
			}
			
		}); 
}
//关闭信息界面
window.onload=function(){
	var button = document.getElementById("widget-pane-button");
	button.onclick=function(){
		var pane = document.getElementById("widget-pane");
	 	var tsf = pane.style.transform;
	 	if (tsf=='translateX(0px)') {
	 		clearInterval(paneTimer);
	 		pane.style.transform="translateX(-408px)";
//	 		flushpane(id);
	 	}
 };
 //声音
 $("#sound_box").click(function(){
	 var ordervalue;
	 if(this.checked) ordervalue=1;
	 else ordervalue=0;
	 $("#loading_div").show();
	 $.post("addOrder",
			 {
		 		monitor_id:monitorInfo.id,
		 		field:"sound",
		 		ordervalue:ordervalue,
		 		state:1
			 },
			 function(data,status){
				 
				 $("#loading_div").hide();
				 $("#loading_info").text(status);
				 $("#loading_info").fadeToggle(100);
				 setTimeout(function(){ $("#loading_info").fadeToggle(100);}, 5000);
			 }
			 );
	 
 });
 //复位
 $("#reset_box").click(function(){
	 
	 $("#loading_div").show();
	 $.post("addOrder",
			 {
		 		monitor_id:monitorInfo.id,
		 		field:"reset",
		 		ordervalue:1,
		 		state:1
			 },
			 function(data,status){
				 $("#loading_div").hide();
				 $("#reset_box").prop("checked",false);
			 }
			 );
	 
 });
}
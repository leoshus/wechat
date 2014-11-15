<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
  <style type="text/css">
    body, html {width: 100%;height: 95%;margin:0;font-family:"微软雅黑";} 
    #allmap{width:100%;height:100%;}
    p{margin-left:5px; font-size:14px;}
    #r-result,#r-result table{width:100%;}
  </style>
 <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=n6g3GUL4SnM48zpYgq60hrgc"></script>
<script type="text/javascript" src="${ctx }/static/js/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/ext/msgbox.js"></script>
<title>微地图</title>
</head>

<body>
			<div>搜索：<input type="text" name="startAddress" id="startAddress"><input type="button" name="find" id="find" value="搜索"></div>
		 <div id="allmap"></div>
		 <div id="r-result"></div>
		<div>搜索详细结果在下面</div>
</body>
<script type="text/javascript">
var p1 ="";					/*起始坐标 */
var p2 ="";					/*终止坐标 */

  $(function(){
	  // 百度地图API功能 
	  var map = new BMap.Map("allmap");
	  map.centerAndZoom(new BMap.Point(116.404, 39.915), 11);
	  map.addControl(new BMap.ScaleControl());//添加比例尺寸控件
	  //map.addControl(new BMap.NavigationControl());// 地图平移缩放控件
	  add_navigationControl(map);
	  map.addControl(new BMap.ScaleControl()); // 比例尺控件
	  map.addControl(new BMap.OverviewMapControl()); // 缩略地图控件
	  //map.addControl(new BMap.MapTypeControl()); // 地图类型控件
	  add_geolocationControl(map);
	  add_geolocation(map);
	  var cr = new BMap.CopyrightControl({
	      anchor :BMAP_ANCHOR_TOP_RIGHT,
	      offset : new BMap.Size(10, 5)
	    //设置版权控件位置
	    });
	  var bs = map.getBounds(); //返回地图可视区域
	  cr.addCopyright({
	    id : 1,
	    content : "<a href='#'style='font-size:20px;background:yellow'>@Sonicery_D制作</a>",
	    bounds : bs
	  }); //Copyright(id,content,bounds)类作为CopyrightControl.addCopyright()方法的参数
	  map.addControl(cr); //添加版权控件
	  
	  
	  /* var marker = new BMap.Marker(new BMap.Point(116.404, 39.915));        // 创建标注      
	  map.addOverlay(marker);
	  marker.addEventListener("click", function(){      
		  alert("哈哈 I'm here.");      
		}); */

			/* var label = new BMap.Label("i'm here<br/>",{offset:new BMap.Size(20,-10)});
			marker.setLabel(label);  */
			$("#find").click(function(){
				var startAddress = $("#startAddress").val();
				if(startAddress != null && startAddress != ""&&startAddress.replace(/(^\s*)|(\s*$)/g,'').length != 0){
					var local = new BMap.LocalSearch(map, {      
					      renderOptions:{map: map,panel: "r-result"}      
					});      
					local.search(startAddress);
				}else{
					alert("请输入要查询的地名!");
				}
			});
			
			
			/* var infoWindow = new BMap.InfoWindow("i'm here", opts);  // 创建信息窗口对象      
			map.openInfoWindow(infoWindow, marker.getPosition());      // 打开信息窗口 */
	  //map.setCurrentCity("北京");
	  //add_geolocation(map);	//页面加载完成弹出是否定位 
	  //add_navigationControl(map);  // 添加带有定位的导航控件
	  //add_geolocationControl(map); // 添加定位控件
  });
  
/*   {
	// 定义一个控件类，即function  
	  function ZoomControl(){  
	      // 设置默认停靠位置和偏移量  
	      this.defaultAnchor = BMAP_ANCHOR_TOP_LEFT;  
	      this.defaultOffset = new BMap.Size(10, 10);
	  }  
	// 通过JavaScript的prototype属性继承于BMap.Control  
	  ZoomControl.prototype = new BMap.Control();
	
	// 自定义控件必须实现initialize方法，并且将控件的DOM元素返回  
	// 在本方法中创建个div元素作为控件的容器，并将其添加到地图容器中  
	ZoomControl.prototype.initialize = function(map){  
	    // 创建一个DOM元素  
	    var div = document.createElement("div");  
	    // 添加文字说明  
	    div.appendChild(document.createTextNode("放大2级"));  
	    // 设置样式  
	    div.style.cursor = "pointer";  
	    div.style.border = "1px solid gray";  
	    div.style.backgroundColor = "white";  
	    // 绑定事件，点击一次放大两级  
	    div.onclick = function(e){  
	        map.zoomTo(map.getZoom() + 2);  
	    }  
	    // 添加DOM元素到地图中  
	    map.getContainer().appendChild(div);  
	    // 将DOM元素返回  
	    return div;  
	};
	// 创建控件实例  
	  var myZoomCtrl = new ZoomControl();  
	  // 添加到地图当中  
	  map.addControl(myZoomCtrl);
  } */
  // 添加带有定位的导航控件
   function add_navigationControl(map){
	  var navigationControl = new BMap.NavigationControl({
		    // 靠左上角位置
		    anchor: BMAP_ANCHOR_TOP_LEFT,
		    // LARGE类型
		    type: BMAP_NAVIGATION_CONTROL_LARGE,
		    // 启用显示定位
		    enableGeolocation: true
		  });
		  map.addControl(navigationControl);
  }
  // 添加定位控件
  function add_geolocationControl(map){
	  var geolocationControl = new BMap.GeolocationControl();
		  geolocationControl.addEventListener("locationSuccess", function(e){
			    // 定位成功事件
			    p1 = new BMap.Point(e.point.lng,e.point.lat);
			    province= e.addressComponent.province;
			   /*  p1 = new BMap.Point(121.282665, 29.836272);
			    province="浙江省"; */
			    /* findcity(province); */
			    /* alert("city:"+city); */
			     //alert('您的位置：'+e.point.lng+','+e.point.lat); 
			   });
			  geolocationControl.addEventListener("locationError",function(e){
			    // 定位失败事件
			    alert(e.message);
			  });  
			  map.addControl(geolocationControl); 
  } 
 
 function add_geolocation(map){
	  alert_c("温馨提示 是否打开gps定位？",null,function(){
			var geolocation = new BMap.Geolocation();
			geolocation.getCurrentPosition(function(r){
				if(this.getStatus() == BMAP_STATUS_SUCCESS){
					var marker = new BMap.Marker(r.point);
					map.centerAndZoom(new BMap.Point(r.point.lng, r.point.lat), 13);
					map.addOverlay(marker);
					map.panTo(r.point);
				 p1 = new BMap.Point(r.point.lng,r.point.lat);
				 province= r.address.province;
				/*  p1 = new BMap.Point(113.320709,23.139204);
				   province="广东省"; */
				 /* findcity(province);
				 jiacheluxian(); */
/*  返回数据格式
{"accuracy":null,"altitude":null,"altitudeAccuracy":null,"heading":null,"latitude":"39.92998578","longitude":"116.39564504","speed":null,"timestamp":null,"point":{"lng":116.39564504,"lat":39.92998578},"address":{"city":"北京市","city_code":0,"district":"","province":"北京市","street":"","street_number":""}}
*/ 				   /*  alert("city:"+city); */
				   /*  alert(r.address.city); */
				 //	alert('您的位置：'+r.point.lng+','+r.point.lat); 
 				}
				else {
					alert('failed'+this.getStatus());
				}        
			},{enableHighAccuracy: true})
	},function(){});	  
  }



</script>
</html>

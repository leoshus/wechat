<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<title>首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name='viewport'
	content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' />
<meta content="telephone=no" name="format-detection" />
<style type="text/css">
	/* slider container */
.iosSlider{
    /* required */
    position: absolute;
    top: 0;
    left: 0;
    overflow: hidden;
    
    width: 100%;
    height: 100%;
    z-index: 0;
}

/* slider */
.iosSlider .slider {
    /* required */
    width: 100%;
    height: 100%;
}

/* slide */
.iosSlider .slider .slide{
    /* required */
    float: left;

    width: 100%;
    height: 100%;
}
.menuContainer{
 	position: absolute;
	width: 100%;
    height: 50%;
    top: 50%;
    left: 0;
    z-index: 1;
}
.menuContainer img{
 	/* opacity: 0.3; */
 	width:40%;
 	heigth:80%;
}
.menuiosSlider{
    /* required */
    position: absolute;
    top: 0;
    left: 0;
    overflow: hidden;
    
    width: 100%;
    height: 100%;
    z-index: 1;
}
.menuiosSlider .menuslider{
    /* required */
    width: 100%;
    height: 100%;
}
.menuiosSlider .menuslider .menuslide{
    /* required */
    float: left;

    width: 100%;
    height: 100%;
}

.childMenu-ul{
	list-style: none;
	margin: 0px;
	padding: 0px;
	text-align: center;
	position: relative;
}
.childMenu{
   display:block;
   float:left;
   width:49.8%;
   height:90px;
   /* border-bottom: 1px solid #eeeeee;  */
   vertical-align:bottom;
   list-style-type:none;
}
.red {background-color: red;}
.green{background-color: green;}
.blue{background-color: blue;}
/* .menu_rightline{display: block; border-right: 1px solid #eeeeee; } */ 

</style>
<script type="text/javascript" src="${ctx }/static/js/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctx }/static/js/jquery.iosslider.js"></script>
<script type="text/javascript" src="${ctx }/static/js/ext/jquery.wechat.js"></script>
<script type="text/javascript" src="${ctx}/static/js/ext/msgbox.js"></script>
<script type="text/javascript">
	$(function(){
		$.wechat.initIosslider();
		$(".childMenu img").click(function(){
			var $this = $(this);
			var $id = $this.attr("id");
			if("maps" == $id){
				window.location.href="${ctx}/netmall/weapp/wemap";
				return;
			} else if("photos" == $id){
				window.location.href="${ctx}/netmall/weapp/upload";
				return;
			} else if("clock" == $id){
				alert("当前时间为:"+new Date().Format("yyyy-MM-dd hh:mm:ss"));
				return;
			} else if("calendar" == $id){
				alert("当前日期为:"+new Date().Format("yyyy-MM-dd"));
				return;
			}else{
				alert("即将上线,敬请期待!");
				return;	
			}
			
		});
	});
	
	// 对Date的扩展，将 Date 转化为指定格式的String
	// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
	// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
	// 例子： 
	// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
	// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
	Date.prototype.Format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}
</script>
</head>

<body>
<div class="scrollbarContainer">
	<div class = 'iosslider'>
	    <!-- slider -->
	   <div class = 'slider'>
	       <!-- slides -->
	       <div class='slide'><img src="${ctx }/static/images/netmall/1.png" width="100%" height="100%;"></div>
	        <div class='slide'><img src="${ctx }/static/images/netmall/2.png" width="100%" height="100%;"></div>
	        <div class='slide'><img src="${ctx }/static/images/netmall/3.png" width="100%" height="100%;"></div>
	    </div>
	</div>
	<div class='menuContainer'>
	  <div class = 'menuiosslider'>
	    <!-- slider -->
	   <div class = 'menuslider'>
	       <!-- slides -->
	       <div class='menuslide'>
				<div>
					<ul class="childMenu-ul">
						<li class="childMenu"><img id="message" src="${ctx }/static/images/netmall/menu/message.png"></li>
						<li class="childMenu "><img id="email" src="${ctx }/static/images/netmall/menu/email.png"></li>
						<li class="childMenu"><img id="calendar" src="${ctx }/static/images/netmall/menu/calendar.png"></li>
						<li class="childMenu "><img id="camera" src="${ctx }/static/images/netmall/menu/camera.png"></li>
					</ul>
				</div>
			</div>
	        <div class='menuslide'>
				<ul class="childMenu-ul">
						<li class="childMenu "><img id="photos" src="${ctx }/static/images/netmall/menu/photos.png"></li>
						<li class="childMenu "><img id="clock" src="${ctx }/static/images/netmall/menu/clock.png"></li>
						<li class="childMenu "><img id="weather" src="${ctx }/static/images/netmall/menu/weather.png"></li>
						<li class="childMenu "><img id="videos" src="${ctx }/static/images/netmall/menu/videos.png"></li>
					</ul>
			</div>
	        <div class='menuslide'>
	        	<ul class="childMenu-ul">
					<li class="childMenu "><img id="notes" src="${ctx }/static/images/netmall/menu/notes.png"></li>
					<li class="childMenu "><img id="games" src="${ctx }/static/images/netmall/menu/games.png"></li>
					<li class="childMenu "><img id="itunes" src="${ctx }/static/images/netmall/menu/itunes.png"></li>
					<li class="childMenu "><img id="maps" src="${ctx }/static/images/netmall/menu/maps.png"></li>
				</ul>
			</div>
	    </div>
	</div>
	</div>
</div>
	
	<%@include file="footer.jsp" %>
</body>
</html>
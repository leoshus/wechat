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
 	opacity: 0.3;
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
}
.childMenu{
	display: block;
	float:left;
	width: 49.8%;
	height:100px;
	border-bottom: 1px solid #eeeeee;
	vertical-align:bottom;
	list-style-type:none;
	opacity:0.5;
	/*border: 5px solid #dedede;*/
    -moz-border-radius: 80px;      /* Gecko browsers */
    -webkit-border-radius: 80px;   /* Webkit browsers */
    border-radius:80px;            /* W3C syntax */
}
.red {background-color: red;}
.green{background-color: green;}
.blue{background-color: blue;}
.menu_rightline{border-right: 1px solid #eeeeee;}

</style>
<script type="text/javascript" src="${ctx }/static/js/jquery/jquery-1.10.2.min.js"></script>
<script type="text/javascript" src="${ctx }/static/js/jquery.iosslider.js"></script>
<script type="text/javascript" src="${ctx }/static/js/ext/jquery.wechat.js"></script>
<script type="text/javascript" src="${ctx}/static/js/ext/msgbox.js"></script>
<script type="text/javascript">
	$(function(){
		$.wechat.initIosslider();
	});
</script>
</head>

<body>
<div class="scrollbarContainer">
	<div class = 'iosslider'>
	    <!-- slider -->
	   <div class = 'slider'>
	       <!-- slides -->
	       <div class='slide'><img src="${ctx }/static/images/netmall/1.jpg" width="100%" height="100%;"></div>
	        <div class='slide'><img src="${ctx }/static/images/netmall/2.jpg" width="100%" height="100%;"></div>
	        <div class='slide'><img src="${ctx }/static/images/netmall/3.jpg" width="100%" height="100%;"></div>
	    </div>
	</div>
	<div class='menuContainer'>
	  <div class = 'menuiosslider'>
	    <!-- slider -->
	   <div class = 'menuslider'>
	       <!-- slides -->
	       <div class='menuslide'>
				<div>
					<ul class="childMenu-ul red">
						<li class="childMenu red">test</li>
						<li class="childMenu menu_rightline red">test2</li>
						<li class="childMenu red">test3</li>
						<li class="childMenu menu_rightline red">test4</li>
					</ul>
				</div>
			</div>
	        <div class='menuslide'>
				<ul class="childMenu-ul">
						<li class="childMenu green">test</li>
						<li class="childMenu menu_rightline green">test2</li>
						<li class="childMenu green">test3</li>
						<li class="childMenu menu_rightline green">test4</li>
					</ul>
			</div>
	        <div class='menuslide'>
	        	<ul class="childMenu-ul">
					<li class="childMenu blue">test</li>
					<li class="childMenu menu_rightline blue">test2</li>
					<li class="childMenu blue">test3</li>
					<li class="childMenu menu_rightline blue">test4</li>
				</ul>
			</div>
	    </div>
	</div>
	</div>
</div>
	
	<%@include file="footer.jsp" %>
</body>
</html>
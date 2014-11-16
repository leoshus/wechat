<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <link href="${ctx }/static/css/reset.css" rel="stylesheet" type="text/css"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <script type="text/javascript" src="${ctx }/static/js/jquery/jquery-1.10.2.min.js"></script>
	<script type="text/javascript" src="${ctx }/static/js/jquery.iosslider.js"></script>
	<script type="text/javascript" src="${ctx }/static/js/ext/jquery.wechat.js"></script>
	<script type="text/javascript" src="${ctx}/static/js/ext/msgbox.js"></script>
    <title>微商城</title>
    <script>
        $(function(){
            $('.iosSlider').iosSlider({
                autoSlide:true,
                scrollbar: true,
                snapToChildren: true,//
                desktopClickDrag: true,//
                scrollbarLocation: 'top',
                scrollbarMargin: '10px 10px 0 10px',
                scrollbarBorderRadius: '0',
                responsiveSlideWidth: true,
                navSlideSelector: $('.iosSliderButtons .button'),
                autoSlideTimer:1500,
                infiniteSlider: true,
                //startAtSlide: '0',
                onSlideChange: slideContentChange
            });
            
            $("#wemoney").click(function(){
            	alert("即将上线,敬请期待!");
            	return;
            });
            $("#wemall").click(function(){
            	alert("即将上线,敬请期待!");
            	return;
            });
            $("#wecart").click(function(){
            	alert("即将上线,敬请期待!");
            	return;
            });
            $("#weservice").click(function(){
            	alert("即将上线,敬请期待!");
            	return;
            });
            $("#contactus").click(function(){
            	alert("猛戳:<a href='http://weibo.com/1853131443'>Sonicery_D的微博!</a>");
            	return;
            });
            
        });
        function slideContentChange(args) {
            /* indicator */
            $(args.sliderObject).parent().find('.iosSliderButtons .button').removeClass('selected');
            $(args.sliderObject).parent().find('.iosSliderButtons .button:eq(' + (args.currentSlideNumber - 1) + ')').addClass('selected');

        }
    </script>
    <style>
        body{background:#e5e5e5;font-size: 62.5%;}
        /*====首页头部轮播图====*/
        /*图片*/
        .container {
            position: relative;
            width: 100%;
            height: 0;
            /* 39% being the ratio we are maintaining
             * calculated by looking at the image
             * ratio: 400/1024 = ~0.39 */
            padding: 0 0 44.53% 0;
        }
        .iosSlider {
            width: 100%;
            /* this height ends up being your maximum height,
             * if you want it to scale forever, set it to
             * a very high number */
            height: 1500px;
        }
        .iosSlider .slider {width:100%; height:100%;}
        .iosSlider .slider .item{position:relative; top:0; left:0;width:100%; height:100%; margin:0; }
        .iosSlider .slider .item img{width:100%;}
        /*按钮*/
        .iosSliderButtons {position:absolute; left:50%; bottom:5px;z-index:999;margin-left:-25px;}
        .iosSliderButtons .button {display:inline-block; width:13px; height:4px;background:#d0cfcf; margin:0 2.5px;}
        .iosSliderButtons .selected {background:#5c5f63;}

        .info{background-color:#ffffff;text-align: center;font-size: 1.4em;height:49px;line-height:49px;color: #666666; }
        .info .-text-hide{text-align: left;margin-left: 20px;}
        .info img{vertical-align:middle;}
        .ljbd-p{padding: 0 20px;}
        .ljbd{width:100%;height:28.5px;line-height:28.5px;float: right;margin-top:10px;color: #ff4400;border: 1px solid #c3c3c3;border-radius:5px;
            background-color: #fff;
            background-image: -webkit-gradient(linear, left top, left bottom, from(#fefefe), to(#eae9e9));
            background-image: -webkit-linear-gradient(top, #fefefe, #eae9e9);
            background-image:    -moz-linear-gradient(top, #fefefe, #eae9e9);
            background-image:     -ms-linear-gradient(top, #fefefe, #eae9e9);
            background-image:      -o-linear-gradient(top, #fefefe, #eae9e9);
            background-image:         linear-gradient(top, #fefefe, #eae9e9);
        }
        .navi{padding:13px 5px;text-align: center;color: #fff;}
        .navi img{margin-top:20px;margin-bottom:10px;}
        .menu1,.menu2,.menu3,.menu4,.menu5,.menu6{margin: 0 5px 10px 5px;border-radius:5px;height: 102px;font-size: 1.5em;}
        .menu7{margin: 0 5px 5px 5px;border-radius:5px;height: 37px;font-size: 1.5em;}
        .menu7 #lxwm{margin:0;vertical-align: middle;margin-top: -3px;}
        .menu7{padding-top: 8px;}
        .menu1{background: #4795ff;}
        .menu2{background: #62acff;}
        .menu3{background: #93acfb;}
        .menu4{background: #6fc52a;}
        .menu5{background: #ff861a;}
        .menu6{background: #f5b62f;}
        .menu7{background: #add522;}
        footer{text-align: center;font-size:1.2em;color:#666666;padding-bottom: 5px;padding-top:10px;}
    </style>
</head>
<body>
    <header>
        <div class = 'container'>
            <div class="iosSlider">
                <div class="slider">
                    <div class="swiper-slide item item1"><img src="${ctx}/static/images/netmall/wemall/banner.png" width="100%" alt="旅行保险"></div>
                    <div class="swiper-slide item item2"><img src="${ctx}/static/images/netmall/wemall/banner02.png" width="100%" alt="旅行保险"></div>
                </div>
               <div class = 'iosSliderButtons'>
                    <div class = 'button selected'></div>
                    <div class = 'button'></div>
                </div>
            </div>
        </div>
    </header>
    <!-- <div class="row info" id="binding">
        <div class="col-bin-6"><p class="-text-hide">已有<span id="visitor">130291位</span>访客<p></div>
    </div> -->
    <nav class="row navi">
    	<div class="col-bin-4"><div class="menu4" id="wemoney"><img src="${ctx}/static/images/netmall/wemall/hpIcon_qynn.png" width="35" height="36"/><p>微钱包</p></div></div>
        <div class="col-bin-4"><div class="menu5" id="wemall"><img src="${ctx}/static/images/netmall/wemall/hpIcon_ylbz.png" width="35" height="36"/><p>商城</p></div></div>
        <div class="col-bin-4"><div class="menu6" id="wecart"><img src="${ctx}/static/images/netmall/wemall/hpIcon_04.png" width="35" height="36"/><p>购物车</p></div></div>
        <div class="col-bin-4"><div class="menu3" id="weservice"><img src="${ctx}/static/images/netmall/wemall/hpIcon_03.png" width="35" height="36"/><p>售后服务</p></div></div>
        <div class="col-bin-12"><div class="menu7" id="contactus"><p><img src="${ctx}/static/images/netmall/wemall/hpIcon_05.png" width="35" height="36" id="lxwm"/>&nbsp;&nbsp;联系我们</p></div></div>
    </nav>
    <footer>
        Sonicery_D&nbsp;版权所有<br/>Copyright Sonicery_D.All Rights Reserved.
    </footer> 
</body>
</html>
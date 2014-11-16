<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="${ctx }/static/css/business/netmall/menu.css" media="all">
</head>
<body>

<div data-role="widget" data-widget="nav4" class="nav4">
		<nav>
			<div id="nav4_ul" class="nav_4">
				<ul class="box">
					<li><a href="javascript:void(0);" class=""><span>关于我们</span></a>
						<dl>
							<dd>
								<a href="javascript:void(0);" id="aboutAuthor"><span>关于作者</span></a>
							</dd>
						</dl></li>
					<li><a href="javascript:void(0);" class=""><span>微应用</span></a>
						<dl>
							<dd>
								<a href="javascript:void(0);" id="wemap"><span>微地图</span></a>
							</dd>
							<dd>
								<a href="javascript:void(0);"><span>微社区</span></a>
							</dd>
							<dd>
								<a href="javascript:void(0);"><span>微投票</span></a>
							</dd>
						</dl></li>
					<li><a href="javascript:void(0);" class=""><span>会员专区</span></a>
						<dl>
							<dd>
								<a href="javascript:void(0);"><span>微商城</span></a>
							</dd>
							<dd>
								<a href="javascript:void(0);"><span>微餐饮</span></a>
							</dd>
							<dd>
								<a href="javascript:void(0);"><span>微团购</span></a>
							</dd>
							<dd>
								<a href="javascript:void(0);"><span>微汽车</span></a>
							</dd>
						</dl></li>
				</ul>
			</div>
		</nav>
		<div id="nav4_masklayer" class="masklayer_div on"></div>
		<script src="${ctx }/static/js/business/netmall/menu.js"></script>
		<script type="text/javascript">
			$(function(){
				nav4.bindClick(document.getElementById("nav4_ul").querySelectorAll(
						"li>a"), document.getElementById("nav4_masklayer"));
				$(".box a").each(function(){
					var $this = $(this);
					$this.bind('click',function(){
						var $id = $this.attr("id");
						if("wxmemberCard" == $id){
							alert("即将上线,敬请期待!");
							return;
						}else if("aboutAuthor" == $id){
							alert("猛戳:<a href='http://weibo.com/1853131443'>Sonicery_D的微博!</a>");
							return ;
						}else if("wemap" == $id){
							window.location.href="${ctx}/netmall/weapp/wemap";
							return;
						}else{
							if(!$this.attr("class")){
								alert("即将上线,敬请期待!");
							}
							return;
						}
					});
				});		
			});
		</script>
	</div>
</body>
</html>
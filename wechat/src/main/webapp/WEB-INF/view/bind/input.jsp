<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=0" />
    <meta content="telephone=no" name="format-detection" />
    <title>用户绑定</title>
    <!--jquery mobile-->
    <link rel="stylesheet" href="${ctx}/static/css/jquerymobile/jquery.mobile-1.4.0.min.css"/>
    <script type="text/javascript" src="${ctx}/static/js/jquery/jquery-1.10.2.min.js"></script>
    <script src="${ctx}/static/js/jquerymobile/jquery.mobile-1.4.0.js"></script>
    <!--重置页面样式-->
    <link rel="stylesheet" href="${ctx}/static/css/main.css"/>
    <!--输入页样式表  请加到jquery-mobile.css文件下面-->
    <link rel="stylesheet" href="${ctx}/static/css/bind/css-input.css"/>
    <link rel="stylesheet" href="${ctx}/static/css/bind/bindstyle.css"/>

    <!--表单验证控件-->
   <link type="text/css" rel="stylesheet" href="${ctx}/static/css/form/validationEngine.jquery.css"/>
   <script type="text/javascript" src="${ctx}/static/js/form/jquery.validationEngine.js"></script>
   <script type="text/javascript" src="${ctx}/static/js/form/languages/jquery.validationEngine-zh_CN.js"></script>

    <!-- 日期相关js&css -->
    <script src="${ctx}/static/js/mobiscroll/mobiscroll.core.js"></script>
    <script src="${ctx}/static/js/mobiscroll/mobiscroll.scroller.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/mobiscroll/mobiscroll.datetime.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/mobiscroll/mobiscroll.scroller.android-ics.js" type="text/javascript"></script>
    <script src="${ctx}/static/js/mobiscroll/mobiscroll.i18n.zh.js" type="text/javascript"></script>
    <link href="${ctx}/static/css/mobiscroll/mobiscroll.scroller.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/css/mobiscroll/mobiscroll.scroller.android-ics.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/static/css/mobiscroll/mobiscroll.animation.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${ctx}/static/js/ext/msgbox.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/ext/jquery.wechat.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/ext/loading.js"></script>
    <script type="text/javascript" src="${ctx}/static/js/jquery/jquery.query.js"></script>
    <script>
    	var form = "wechatUser";
    	var openId = "";
        $(function(){
        	//openId = $.query.get("openId");
        	openId = '${openId}';
        	console.log("openId"+openId);
        	$("#openId").val(openId);
        	$.sessionStorage.clear();
        	var condition = [
        	                 {name:"wechatName",rule:"validate[required]"},
        	                 {name:"wechatCardNo",rule:"validate[required,custom[chinaId]]"},
        	                 {name:"wechatPhone",rule:"validate[required,custom[phone]]"}
        	                // {name:"wechatVerify",rule:"validate[required]"}
        	];
        	$.validateInit(condition,form);
			$("#nextbtn").click(function(){
				if(!$.validateForm(form)){
					return;
				}else{
		        	showLoadingb();
		        	return;
		        	var infos = $.wechat.combineForm(form);
					$.post("${ctx}/bind/create.json",infos,function(data){
						if(data != null && data != ""){
							if(data.success == "true" || data.success == true){
								window.location.href="${ctx}/bind/success";								
							}else{
								alert(data.message);
							}
						}else{
							window.location.href="${ctx}/error/error";
						}
						hideLoading();
					}).error(function(){
						alert("服务暂时不可用,请稍后再试!");
						hideLoading();
					});
		        	$.sessionStorage.setData(form);
				}
				
			});        	
			 //投保人证件类型change事件,如果选择的是身份证，则下面的出生日期选项影藏，反之则显示
            $("#wechatCardType").change(function(){
            	console.log($("#wechatCardType").val());
                if($("#wechatCardType").val()=="0"){
                    $("#noIDCardDiv").css("display","none");
                }else{
                    $("#noIDCardDiv").css("display","block");
                }
            });
			 
          //初始化，时间控件
	        $.wechat.showTime($("#wechatBirth"),'optDate',100);//'optDate');
        });
    </script>
</head>
<body>
<form id="wechatUser" name="wechatUser">
<div data-role="page">
        <div data-role="header" class="header-a">
            <div style="text-align: center;">
                	<span>用户绑定</span>
            </div>
        </div>
        <div data-role="content" style="padding: 10px 10px 0 10px;">
            <div class="info-content input1-div clearfix">
                <div class="input-div">
                    <label for="wechatName" class="ls2">姓名</label>
                    <input id="wechatName" type="text" name="wechatName" />
                </div>
                <div class="select-div">
                    <label for="wechatCardType">证件类型</label>
                    <select id="wechatCardType" name="wechatCardType">
                    	<option value="0" selected="selected">身份证</option>
                        <option value="1">护照号</option>
                        <option value="2">军官证</option>
                        <option value="8">其他</option>
                    </select>
                </div>
                <div class="input-div">
                    <label for="wechatCardNo">证件号码</label>
                    <input id="wechatCardNo" name="wechatCardNo" type="text" />
                </div>
                <div id="noIDCardDiv" style="display:none;">
                    <div class="select-div" id="wechatSexDiv">
                        <label for="wechatSex">投保人性别：</label>
                        <select id="wechatSex" name = "wechatSex">
                            <option value="0">男</option>
                            <option value="1">女</option>
                        </select>
                    </div>
                    <div class="input-div" id="csrq_div" id="wechatBirthDiv">
                        <label for="wechatBirth">出生日期：</label>
                        <input id="wechatBirth" name="wechatBirth" type="text"/>
                    </div>
                </div>
                <div class="input-div" id="sjhm_div">
                    <label for="wechatPhone">手机号码</label>
                    <input id="wechatPhone" type="number" name="wechatPhone" />
                    <input type="hidden" name="openId" id="openId"/>
                </div>
                <!-- <div class="input-div v">
                    <label for="wechatVerify" class="ls3">验证码</label>
                    <input id="wechatVerify" name="wechatVerify" type="text" />
                </div> 
                <a href="javascript:$.tkylMessage.getVerifyCode();" class="verify-btn" style="color:#81ccb0;">获取验证码</a>-->
            </div>
            <div id="errorInfomessage" style="text-align:center;width:100%;color:#ff0000;"></div>
            <a id="nextbtn" href="javascript:void(0);" data-ajax="false" class="next-btn" style="color:#ffffff;">绑定</a>
        </div>
        <div data-role="footer" class="footer-a">
            Sonicery_D&nbsp;版权所有<br/>Copyright Sonicery_D.All Rights Reserved.
        </div>
</div>
</form>
</body>
</html>
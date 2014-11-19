/**
 * 微信内部 weixinJsBridge API 封装
 */
var imgUrl,descContent,shareTitle,prePath;
var appid="gh_4f3143843422";
$.weixinJsBridge={
		init:function(imgUrls,descContents,shareTitles,prePaths){
			imgUrl = imgUrls;
			descContent = descContents;
			shareTitle = shareTitles;
			prePath = prePaths;
		},
		shareFriend:function(){// 发送给好友
			 WeixinJSBridge.invoke('sendAppMessage',
				    	{
							"appid": appid,
							"img_url": imgUrl,
							"img_width": "640",
							"img_height": "640",
							"link": prePath,
							"desc": descContent,
							"title": shareTitle
			            }, function(res) {
			            	_report('send_msg', res.err_msg);
			            })
		},
		shareTimeline:function(){// 分享到朋友圈
			 WeixinJSBridge.invoke('shareTimeline',
				    	{
							"img_url": imgUrl,
							"img_width": "640",
							"img_height": "640",
							"link": prePath,
							"desc": descContent,
							"title": descContent
			            }, function(res) {
			            	_report('timeline', res.err_msg);
			            });
		},
		shareWeibo:function(){// 分享到微博
			 WeixinJSBridge.invoke('shareWeibo',
				    	{
							"content": descContent,
							"url":  prePath,
							}, function(res) {
			            	_report('weibo', res.err_msg);
			            });
		},
		/**
		 * 以下封装 有待测试 可能已被封杀
		 */
		/**
		 * 添加联系人 ---对某个页面元素 添加click事件触发
		 */
		addContact:function($this){
			$this.addEventListener("click",function(){
				WeixinJSBridge.invoke("addContact",{
					"webtype":"1",
					"username":appid
				},function(e){
					alert(e.err_msg);
				},!1);
			});
		},
		/**
		 * 扫描二维码
		 * @param $this
		 */
		scanQRCode:function($this){
			$this.addEventListener(
		            "click",function(){
		                WeixinJSBridge.invoke("scanQRCode",{
		                })
		            },!1);
		},
		/**
		 * 跳转到指定公众账号页面
		 */
		jumpToBizProfile:function($this){
			$this.addEventListener(
		            "click",function(){
		                WeixinJSBridge.invoke("jumpToBizProfile",{
		                    "tousername":appid
		                },
		                function(e){
		                    alert(e.err_msg);
		                })
		            },!1);
		},
		/**
		 * 获取网络状态
		 * @param $this
		 */
		getNetType:function($this){
			$this.addEventListener(
		            "click",function(){
		                WeixinJSBridge.invoke("getNetworkType",{},
		                    function(e){
		                        alert(netType[e.err_msg])
		                    })
		            },!1);
		},
		/**
		 * 关闭
		 * @param $this
		 */
		closeWindow:function($this){
			$this.addEventListener(
                    "click",function(){
                        WeixinJSBridge.invoke("closeWindow",{},function(e){})
                },!1);
		},
		/**
		 * 发起公众号微信支付
		 * @param $this
		 */
		getBrandWCPayRequest:function($this){
			$this.addEventListener(
		            "click",function(){
		                WeixinJSBridge.invoke("getBrandWCPayRequest",{
		                    "appId" : appid, //公众号名称，由商户传⼊入
		                    "timeStamp" : "189026618", //时间戳 这⾥里随意使⽤用了⼀一个值
		                    "nonceStr" : "adssdasssd13d", //随机串
		                    "package" :
		                    "body=xxx&fee_type=1&input_charset=GBK&notify_url=http&out_trade_no=16642817866003386000&partner=1900000109&return_url=http&spbill_create_ip=127.0.0.1&total_fee=1&sign=273B7EEEE642A8E41F27213D8517E0E4", //扩展字段，由商户传⼊入
		                    "signType" : "SHA1", //微信签名⽅方式:sha1
		                    "paySign" : "b737015b5b1eabe5db580945a07eac08c7bb55f8" //微信签名
		                    },
		                    function(e){
		                        alert(e.err_msg)
		                    })
		                },!1);
		},
		/**
		 * 设置页面状态
		 * @param $this
		 */
		setPageState:function($this){
			$this.addEventListener(
		            "click",function(){
		                WeixinJSBridge.invoke("setPageState",{
		                    "state" : "1"
		                    })
		                },!1);
		},
		/**
		 * 发邮件
		 * @param $this
		 */
		sendEmail:function($this){
			$this.addEventListener(
		            "click",function(){
		                WeixinJSBridge.invoke("sendEmail",{
		                    "title" : "title!",
		                    "content" : "i am an Email!", //时间戳 这⾥里随意使⽤用了⼀一个值
		                    },
		                    function(e){
		                      alert(e.err_msg)
		                    })
		                },!1);
		},
		/**
		 * 微信团队打开webView,跳到指定页面
		 * @param $this
		 */
		openSpecificView:function($this){
			$this.addEventListener(
		            "click",function(){
		                WeixinJSBridge.invoke("openSpecificView",{
		                    "specificview" : "contacts"
		                    },
		                    function(e){
		                        alert(e.err_msg)
		                    })
		                },!1);
		},
		/**
		 * getCanIAPPay
		 * @param $this
		 */
		getCanIAPPay:function($this){
			$this.addEventListener(
		            "click",function(){
		                WeixinJSBridge.invoke("getCanIAPPay",{  },
		                    function(e){
		                        alert(e.err_msg)
		                    })
		                },!1);
		},
		/**
		 * 发起公众号IAP支付
		 * @param $this
		 */
		getBrandIAPPayRequest:function($this){
			$this.addEventListener(
		            "click",function(){
		                WeixinJSBridge.invoke("getBrandIAPPayRequest",{
		                    "appId" : appid, //公众号名称，由商户传⼊入
		                    "timeStamp" : "189026618", //时间戳 这⾥里随意使⽤用了⼀一个值
		                    "nonceStr" : "adssdasssd13d", //随机串
		                    "package" : "bankType=CITIC_CREDIT&bankName=%e4%b8%ad%e4%bf%a1%e9%93%b6%e8%a1%8c&sign=CF8922F49431FFE8A1834D0B32B25CE3",
		                    //扩展字段，由商户传⼊入
		                    "signType" : "SHA1", //微信签名⽅方式:sha1
		                    "paySign" : "1e6f13f78ca0ec43fbb80899087f77568af66987" //微信签名
		                    },
		                    function(e){
		                        alert(e.err_msg)
		                    })
		                },!1);
		},
		/**
		 * 用safari打开指定链接
		 * @param $this
		 */
		openUrlByExtBrowser:function($this){
			$this.addEventListener(
		            "click",function(){
		                WeixinJSBridge.invoke("openUrlByExtBrowser",{
		                    "url" : "http://m.exmail.qq.com"
		                    },
		                    function(e){
		                        alert(e.err_msg)
		                    })
		                },!1);
		},
		/**
		 * 跳转微信商品页
		 * @param $this
		 */
		openProductView:function($this){
			$this.addEventListener(
		            "click",function(){
		                WeixinJSBridge.invoke("openProductView",{   
		                    "latitude" : 23.113, //纬度
		                    "longitude" : 113.23, //经度
		                    "name" : "TIT创意园", //POI名称
		                    "address" : "⼲⼴广州市海珠区新港中路397号", //地址
		                    "scale" : 14, //地图缩放级别
		                    "infoUrl" : "http://weixin.qq.com/", //查看位置界⾯面底部的超链接                
		                    },
		                    function(e){
		                        alert(e.err_msg)
		                    })
		                },!1);
		},
		/**
		 * 查看地理位置
		 * @param $this
		 */
		openLocation:function($this){
			$this.addEventListener(
		            "click",function(){
		                WeixinJSBridge.invoke("openProductView",{   
		                    "latitude" : 23.113, //纬度
		                    "longitude" : 113.23, //经度
		                    "name" : "TIT创意园", //POI名称
		                    "address" : "⼲⼴广州市海珠区新港中路397号", //地址
		                    "scale" : 14, //地图缩放级别
		                    "infoUrl" : "http://weixin.qq.com/", //查看位置界⾯面底部的超链接                
		                    },
		                    function(e){
		                        alert(e.err_msg)
		                    })
		                },!1);
		},
		/**
		 * 朋友圈签到
		 * @param $this
		 */
		timelineCheckIn:function($this){
			$this.addEventListener(
		            "click",function(){
		                WeixinJSBridge.invoke("timelineCheckIn",{   
		                    "img_url": "http://mmsns.qpic.cn/mmsns/RLllkTm3DUdV24xbZnKicx9jJWxXI0Bq84zzbtibGuRyk/0", // 分享到朋友圈的缩略图
		                    "img_width": "640",// 图⽚片的⻓长度
		                    "img_height": "640",// 图⽚片⾼高度
		                    "link": "http://news.qq.com/zt2012/cxkyym/index.htm",// 连接地址
		                    "desc": "这个是描述啊啊", // 描述
		                    "title": "朝鲜称中国渔船越界捕捞", // 分享标题
		                    "latitude" : 23.113, //纬度
		                    "longitude" : 113.23, //经度
		                    "poiId" : "dianping_2331037", //商户id
		                    "poiName" : "TIT创意园", //POI名称
		                    "poiAddress" : "⼲⼴广州市海珠区新港中路397号", //地址
		                    "poiScale" : 14, //地图缩放级别
		                    "poiInfoUrl" : "http://weixin.qq.com/" //查看位置界⾯面底部的超链接
		                    },
		                    function(e){
		                        alert(e.err_msg)
		                    })
		                },!1);
		},
		/**
		 * 开通微信信用卡
		 * @param $this
		 */
		getBrandWCPayCreateCreditCardRequest:function($this){
			$this.addEventListener(
		            "click",function(){
		                WeixinJSBridge.invoke("getBrandWCPayCreateCreditCardRequest",{  
		                    "appId" : appid, //公众号名称，由商户传⼊入
		                    "timeStamp" : "189026618", //时间戳 这⾥里随意使⽤用了⼀一个值
		                    "nonceStr" : "adssdasssd13d", //随机串
		                    "package" : "bankType=CITIC_CREDIT&bankName=%e4%b8%ad%e4%bf%a1%e9%93%b6%e8%a1%8c&sign= CF8922F49431FFE8A1834D0B32B25CE3",
		                    //扩展字段，由商户传⼊入
		                    "signType" : "SHA1", //微信签名⽅方式:sha1
		                    "paySign" : "1e6f13f78ca0ec43fbb80899087f77568af66987" //微信签名
		                    },
		                    function(e){
		                        alert(e.err_msg)
		                    })
		                },!1);
		},
		/**
		 * 获取地理位置
		 * @param $this
		 */
		geoLocation:function($this){
			$this.addEventListener(
		            "click",function(){
		                WeixinJSBridge.invoke("geoLocation",{   
		                    },
		                    function(e){
		                        alert(e.err_msg)
		                    })
		                },!1);
		},
		/**
		 * 获取某app是否安装
		 * @param $this
		 */
		getInstallState:function($this){
			$this.addEventListener(
		            "click",function(){
		                WeixinJSBridge.invoke("getInstallState",{   
		                        "packageUrl":"teamcircle://"
		                    },
		                    function(e){
		                        alert(e.err_msg)
		                    })
		                },!1);
		},
		/**
		 * 公众号编辑收货地址
		 * @param $this
		 */
		editAddress:function($this){
			$this.addEventListener(
	                "click",function(){
	                    WeixinJSBridge.invoke("editAddress",{   
	                        "appId" : appid, //公众号名称，由商户传⼊入
	                        "timeStamp" : "189026618", //时间戳 这⾥里随意使⽤用了⼀一个值
	                        "nonceStr" : "adssdasssd13d", //随机串
	                        "signType" : "SHA1", //微信签名⽅方式:sha1
	                        "addrSign" : "b737015b5b1eabe5db580945a07eac08c7bb55f8", //微信签名
	                        "scope"    : "snsapi"
	                    },
	                    function(e){
	                        alert(e.err_msg)
	                    })
	                },!1);
		},
		/**
		 * 公众号获取最近的收货地址
		 * @param $this
		 */
		getLatestAddress:function($this){
			$this.addEventListener(
	                "click",function(){
	                    WeixinJSBridge.invoke("getLatestAddress",{  
	                        "appId" : appid, //公众号名称，由商户传⼊入
	                        "timeStamp" : "189026618", //时间戳 这⾥里随意使⽤用了⼀一个值
	                        "nonceStr" : "adssdasssd13d", //随机串
	                        "signType" : "SHA1", //微信签名⽅方式:sha1
	                        "addrSign" : "b737015b5b1eabe5db580945a07eac08c7bb55f8", //微信签名
	                        "scope"    : "snsapi"
	                    },
	                    function(e){
	                        alert(e.err_msg)
	                    })
	                },!1);
		},
		/**
		 * 启动第三方APP
		 * @param $this
		 */
		launch3rdApp:function($this){
			$this.addEventListener(
	                "click",function(){
	                    WeixinJSBridge.invoke("launch3rdApp",{  
	                        "appId" : appid, //公众号名称，由商户传⼊入
	                    },
	                    function(e){
	                        alert(e.err_msg)
	                    })
	                },!1);
		},
		/**
		 * 跳转微信商品购买界面
		 * @param $this
		 */
		jumpWCMall:function($this){
			$this.addEventListener(
	                "click",function(){
	                    WeixinJSBridge.invoke("jumpWCMall",{    
	                        "appId" : appid, //公众号名称，由商户传⼊入
	                        "funcId":"1000"
	                    },
	                    function(e){
	                        alert(e.err_msg)
	                    })
	                },!1);
		},
		/**
		 * 添加表情
		 * @param $this
		 */
		addEmoticon:function($this){
			$this.addEventListener(
		            "click",function(){
		                WeixinJSBridge.invoke("addEmoticon",{
		                    "url":"http://rescdn.qqmail.com/bizmail/zh_CN/htmledition/images/bizmail/v3/icons_features1ca3fe.png",
		                    "thumb_url":"http://rescdn.qqmail.com/bizmail/zh_CN/htmledition/images/bizmail/v3/logo1ca3fe.png"
		                },
		                function(e){
		                   alert(e.err_msg);
		                })
		            },!1)
		},
		/**
		 * 取消下载某表情
		 * @param $this
		 */
		cancelAddEmoticon:function($this){
			$this.addEventListener(
		            "click",function(){
		                WeixinJSBridge.invoke("cancelAddEmoticon",{
		                    "url":"http://rescdn.qqmail.com/bizmail/zh_CN/htmledition/images/bizmail/v3/icons_features1ca3fe.png"		 
		                },
		                function(e){
		                	alert(e.err_msg);
		                })
		            },!1)
		},
		/**
		 * 查询是否存在某表情
		 * @param $this
		 */
		hasEmoticon:function($this){
			$this.addEventListener(
		            "click",function(){
		                WeixinJSBridge.invoke("hasEmoticon",{
		                    "url":"http://rescdn.qqmail.com/bizmail/zh_CN/htmledition/images/bizmail/v3/icons_features1ca3fe.png"		 
		                },
		                function(e){
		                	alert(e.err_msg);
		                })
		            },!1);
		}
		
};
//当微信内置浏览器完成内部初始化后会触发WeixinJSBridgeReady事件。
document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
      	//WeixinJSBridge.call('hideToolbar');				//隐藏工具栏
		//WeixinJSBridge.call('hideOptionMenu');			//隐藏菜单栏
      	WeixinJSBridge.call('showOptionMenu');		//显示菜单栏
		
        // 发送给好友
        WeixinJSBridge.on('menu:share:appmessage', function(argv){
        	$.weixinJsBridge.shareFriend();
            });
        // 分享到朋友圈
        WeixinJSBridge.on('menu:share:timeline', function(argv){
        	$.weixinJsBridge.shareTimeline();
            });
        // 分享到微博
        WeixinJSBridge.on('menu:share:weibo', function(argv){
        	$.weixinJsBridge.shareWeibo();
            });
 }, false);

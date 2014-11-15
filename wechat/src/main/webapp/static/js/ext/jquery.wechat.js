/**
 * wechat 自定义js方法集
 */
/**
 * 对jQuery进行扩展
 */
(function($){
	/**
	 * 为表单元素添加校验信息
	 */
	$.validateInit=function(conditions,form){
		for(var i = 0;i < conditions.length;i++){
			var cond = conditions[i];
			$("#" + form +" [id="+cond.name+"]").attr("class",cond.rule);
			//---add by Sonicery_D ---20140916--start---
            
            // No option, take default one
                var field = $("#" + form +" [id="+cond.name+"]");
                field.bind("click",function(){
                	$(this).validationEngine("changeHideTips");
                });
                
        //---end---
		}
		$("#" + form).validationEngine('attach',{
			showArrow: false,
			autoPositionUpdate: true,
			addPromptClass:'formError-noArrow',
			scroll:false
		});
	},
	/**
	 * input check
	 */
	$.validateForm=function(form){
		return $("#"+form).validationEngine('validate');
	}
})(jQuery);

/**
 * html5 SessionStorage
 * form:formid值
 */
$.sessionStorage={
	/**
	 * 根据key获取保存指定的数据
	 * @param param
	 * @returns
	 */
	getData:function(param){
		return JSON.parse(sessionStorage.getItem(param));
	},
	/**
	 * 将某个元素保存在sessionStorage中
	 * @param item
	 * @param name
	 */
	setItem:function(name,item){
		sessionStorage.setItem(name,JSON.stringify(item));
	},
	/**
	 * 将form表单信息保存在sessionStorage中
	 * @param form
	 */
	setData:function(form){
	        var dataPara = $.sessionStorage.getFormJson("#"+form);
	        if (window.localStorage) {} else {
	            alert("不支持本地存储！");
	        }
	        var data = JSON.stringify(dataPara);
	        try {
	            sessionStorage.clear();
	            sessionStorage.setItem("data", data);
	        } catch (e) {
	            alert(e);
	        }
	},
	//-- ps:注意将同名的放在一个数组里
	//--ps :注意将同名的覆盖掉---by Sonicery_D---20140926
	getFormJson:function(frm){
		var o = {};
        var a = $(frm).serializeArray();
        $.each(a, function () {
            if (o[this.name] !== undefined) {
                /*if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');*/
            	o[this.name] = null;
            	o[this.name] = this.value || '';
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
	},
	/**
	 * 清除sessionStorage中的全部数据
	 */
	clear:function(){
		sessionStorage.clear();
	},
	/**
	 * 删除指定的item
	 * @param item
	 */
	removeItem:function(item){
		sessionStorage.removeItem(item);
	}
		
};
$.wechat={
		//弹出世间选择面板--type:optDate optDateTime optTime
		showTime:function(o,type,cYear){
		       //使用时间插件，弹出时间选择start
		   var currYear = (new Date()).getFullYear();
		   console.log("currYear="+currYear);
		   if(cYear){
		       currYear=currYear-cYear;
		   }
		   var opt={};
		   opt.date = {preset : 'date'};
		   //opt.datetime = { preset : 'datetime', minDate: new Date(2012,3,10,9,22), maxDate: new Date(2014,7,30,15,44), stepMinute: 5  };
		   opt.datetime = {preset : 'datetime'};
		   opt.time = {preset : 'time'};
		   opt.defaul={
		       dateOrder: 'yymmdd', //面板中日期排列格
		   //dateFormat: 'yyyy-mm-dd',
		   theme: 'android-ics light', //皮肤样式
		   display: 'modal', //显示方式
		   mode: 'scroller', //日期选择模式
		   lang:'zh',
		   startYear:currYear, //开始年份
		   showNow: true,
		   stepMinute: 5,
		   endYear:currYear+100 //结束年份

		   };
		   var optDate=$.extend(opt['date'], opt['defaul']);
		   var optDateTime = $.extend(opt['datetime'], opt['defaul']);
		   var optTime = $.extend(opt['time'], opt['defaul']);
		   if(type=='optDate'){
		   o.scroller('destroy').scroller(optDate);
		   }else if(type=='optDateTime'){
		   o.scroller('destroy').scroller(optDateTime);
		   } else if(type=='optTime'){
		   o.scroller('destroy').scroller(optTime);
		       }
		   },
		   /**
		    * 将form表单下的数据 拼装成数组
		    */
		  combineForm:function(form){
			  var arrays = {};
			  $("#"+form+" :input").each(function(i,v){
				  $this = $(this);
				  $name = $this.attr("name");
				  $value = $this.val();
				  arrays[$name]=$value;
				  console.log("name:"+$name+",value="+$value);
			  });
			  //arrays["openId"]="123";
			  console.log("combineForm:"+JSON.stringify(arrays));
			  return arrays;
		  },
		  /**
		   * jquery.iosslider.js init
		   */
		  initIosslider:function(){
			  $('.iosSlider').iosSlider({
				  snapToChildren: true,
			        scrollbar: true,
			        scrollbarHide: false,
			        desktopClickDrag: true,
			        infiniteSlider:true,
			        snapSlideCenter: true,
			        autoSlide: true,
			        scrollbarLocation: 'bottom',
			        scrollbarHeight: '6px',
			        //scrollbarBackground: 'url(${ctx }/static/images/netmall/home-default17.jpg) repeat 0 0',
			        scrollbarBorder: '1px solid #000',
			        scrollbarMargin: '0 30px 16px 30px',
			        scrollbarOpacity: '0.75',
			        autoSlideTransTimer:750,
			        onSlideChange: slideContentChange,		        
			        onSlideComplete: slideContentComplete,
			        onSliderLoaded: slideContentLoaded
			    });
			    function slideContentChange(args) {
			        //setCurrentIndex(args.currentSlideNumber);
			    }
			    function slideContentComplete(args) {

			    }
			    function slideContentLoaded(args) {

			    }
			    
			    
			    $('.menuiosslider').iosSlider({
					  snapToChildren: true,
				        scrollbar: true,
				        scrollbarHide: false,
				        desktopClickDrag: true,
				        infiniteSlider:true,
				        snapSlideCenter: true,
				        autoSlide: true,
				        scrollbarLocation: 'bottom',
				        scrollbarHeight: '6px',
				        //scrollbarBackground: 'url(${ctx }/static/images/netmall/home-default17.jpg) repeat 0 0',
				        scrollbarBorder: '1px solid #000',
				        scrollbarMargin: '0 30px 16px 30px',
				        scrollbarOpacity: '0.75',
				        autoSlideTransTimer:1000,
				        onSlideChange: slideContentChange,		        
				        onSlideComplete: slideContentComplete,
				        onSliderLoaded: slideContentLoaded
				    });
		  }
};

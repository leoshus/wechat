/**
 * Created by zengbin on 2014/8/20.
 */

// 入参，txt：提示消息，obj：返回焦点所在对象
window.alert = function(txt,obj)
{
    var strshield=document.getElementById("shield");
    var stralertFram=document.getElementById("alertFram");

    if(strshield!=null) {
        document.body.removeChild(document.getElementById("shield"));
    }
    if(stralertFram!=null) {
        document.body.removeChild(document.getElementById("alertFram"));
    }
    var shield = document.createElement("DIV");
    shield.id = "shield";
    shield.style.position = "fixed";
    shield.style.left = "0px";
    shield.style.top = "0px";
    shield.style.width = "100%";
    //console.log(document.body.clientHeight,document.documentElement.clientHeight);
    shield.style.height = document.body.scrollHeight+"px";
    shield.style.background = "#000000";
    shield.style.textAlign = "center";
    shield.style.zIndex = "10000";
    //shield.style.filter = "alpha(opacity=0)"; //IE
    shield.style.opacity = 0.4;// chrome
    var alertFram = document.createElement("DIV");
    alertFram.id="alertFram";
    alertFram.style.position = "fixed";
    alertFram.style.left = "50%";
    alertFram.style.top = "40%";
    alertFram.style.marginLeft = "-125px";
    alertFram.style.marginTop = "-60px";
    alertFram.style.width = "250px";
    alertFram.style.height = "auto";
    alertFram.style.background = "#ffffff";
    alertFram.style.textAlign = "center";
    alertFram.style.lineHeight = "50px";
    alertFram.style.zIndex = "10001";
    alertFram.style.borderRadius="5px";
    strHtml = "<ul style=\"list-style:none;margin:0px;padding:0px;width:100%;height: auto;\">\n";
    strHtml += " <li style=\"background:#ffffff;text-align:left;font-size:14px;font-weight:bold;height:25px;line-height:25px;border-bottom:3px solid #f98743;color: #f98743;padding: 5px 0px 5px 20px;border-radius: 5px 5px 0px 0px;\">系统提示</li>\n";
    strHtml += " <li style=\"background:#ffffff;text-align:left;font-size:12px;height:120px;line-height:20px;height: auto;\"><div style=\"padding:20px\">"+txt+"</div></li>\n";
    strHtml += " <li style=\"background:#ffffff;text-align:center;font-weight:bold;height:30px;line-height:30px; border-top:1px solid #cccccc;padding: 0px 0px;color: #f98743;border-radius: 0px 0px 5px 5px;\" onclick=\"doOk()\">确 定</li>\n";
    strHtml += "</ul>\n";
    alertFram.innerHTML = strHtml;
    document.body.appendChild(alertFram);
    document.body.appendChild(shield);
    this.doOk = function(){
        alertFram.style.display = "none";
        shield.style.display = "none";
        if(obj!=null){
            obj.focus();
        }
    }
    //alertFram.focus();
    document.body.onselectstart = function(){return false;};
}

/**
 * 提示框
 * @param txt：提示消息，
 * @param obj：返回焦点所在对象
 * @param ok_f 确定回调函数
 * @param close_f 取消的回调函数
 * */
window.alert_c = function(txt,obj,ok_f,close_f)
{
    var strshield=document.getElementById("shield");
    var stralertFram=document.getElementById("alertFram");
    if(strshield!=null) {
        document.body.removeChild(document.getElementById("shield"));
    }
    if(stralertFram!=null) {
        document.body.removeChild(document.getElementById("alertFram"));
    }
    var shield = document.createElement("DIV");
    shield.id = "shield";
    shield.style.position = "fixed";
    shield.style.left = "0px";
    shield.style.top = "0px";
    shield.style.width = "100%";
//    console.log(document.body.clientHeight,document.documentElement.clientHeight);
    shield.style.height = document.body.scrollHeight+"px";
    shield.style.background = "#000000";
    shield.style.textAlign = "center";
    shield.style.zIndex = "10000";
    //shield.style.filter = "alpha(opacity=0)"; //IE
    shield.style.opacity = 0.4;// chrome
    var alertFram = document.createElement("DIV");
    alertFram.id="alertFram";
    alertFram.style.position = "fixed";
    alertFram.style.left = "50%";
    alertFram.style.top = "40%";
    alertFram.style.marginLeft = "-125px";
    alertFram.style.marginTop = "-60px";
    alertFram.style.width = "250px";
    alertFram.style.height = "auto";
    alertFram.style.background = "#ffffff";
    alertFram.style.textAlign = "center";
    alertFram.style.lineHeight = "50px";
    alertFram.style.zIndex = "10001";
    alertFram.style.borderRadius="5px";
    strHtml = "<ul style=\"list-style:none;margin:0px;padding:0px;width:100%;height: auto;\">\n";
    strHtml += " <li style=\"background:#ffffff;text-align:left;font-size:14px;font-weight:bold;height:25px;line-height:25px;border-bottom:3px solid #f98743;color: #f98743;padding: 5px 0px 5px 20px;border-radius: 5px 5px 0px 0px;\">系统提示</li>\n";
    strHtml += " <li style=\"background:#ffffff;text-align:left;font-size:14px;height:120px;line-height:20px;height: auto;\"><div style=\"padding:20px\">"+txt+"</div></li>\n";
    if(close_f!=null){
        strHtml += " <li style=\"background:#ffffff;font-weight:bold;height:40px;text-align: left;line-height:40px; border-top:1px solid #cccccc;color: #f98743;border-radius: 0px 0px 5px 5px;\">\n" +
                        "<span onclick=\"doOk()\" style='display: inline-block;text-align: center;width: 49%;border-right: 1px solid #cccccc;color: #f98743;font-size:16px;'/>"+"确定"+"</span>"+
                        "<span onclick=\"doClose()\" style='display: inline-block;width: 50%;text-align: center;color: #f98743;font-size:16px;'/>"+"取消"+"</span>"+
                    " </li>\n";
    }else if(ok_f!=null){
        strHtml += " <li style=\"background:#ffffff;text-align:center;font-weight:bold;height:40px;line-height:40px; border-top:1px solid #cccccc;padding: 0px 0px;color: #f98743;border-radius: 0px 0px 5px 5px;font-size: 16px;\" onclick=\"doOk()\">确 定</li>\n";
    }
    strHtml += "</ul>\n";
    alertFram.innerHTML = strHtml;
    document.body.appendChild(alertFram);
    document.body.appendChild(shield);
    //点击确定按钮事件
    this.doOk = function(){
        this.onHide();
        if(ok_f!=null){
            ok_f();
        }
    }
    //点击取消按钮事件
    this.doClose=function(){
        this.onHide();
        if(close_f!=null){
            close_f();
        }
    }
    this.onHide=function(){
        alertFram.style.display = "none";
        shield.style.display = "none";
        if(obj!=null){
            obj.focus();
        }
    }
    //alertFram.focus();
    document.body.onselectstart = function(){return false;};
}
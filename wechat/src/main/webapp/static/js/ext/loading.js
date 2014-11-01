$(function(){
    //设置0.5的透明。
    //filter:alpha(opacity=50);-moz-opacity:0.5;-khtml-opacity: 0.5;opacity: 0.5;
//    console.log("添加loading");
    var devieW=document.body.clientWidth||document.documentElement.clientWidth||document.body.scrollWidth;
    var devieH=document.body.clientHeight||document.documentElement.clientHeight||document.body.scrollHeight;
    var shield2 = document.createElement("DIV");
    shield2.style.display="none";
    shield2.id = "shield2";
    shield2.style.position = "fixed";
    shield2.style.left = "0px";
    shield2.style.top = "0px";
    shield2.style.width = "100%";
//    console.log("document.body.clientHeight="+document.body.clientHeight+"____document.documentElement.clientHeight="+document.documentElement.clientHeight+"__document.body.scrollHeight"+document.body.scrollHeight);
//    console.log("devieH="+devieH);
    shield2.style.height = document.documentElement.clientHeight+"px";
    shield2.style.background = "#ffffff";
    shield2.style.textAlign = "center";
    shield2.style.zIndex = "10000";
    //shield2.style.filter = "alpha(opacity=0)"; //IE
    shield2.style.opacity = 0.4;// chrome
    document.body.appendChild(shield2);

    var l_img=document.createElement("img");
    l_img.id="l_img";
    l_img.src="../static/images/loading.gif";
    l_img.style.position="absolute";
    l_img.style.left="0px";
    l_img.style.top="0px";
    l_img.style.width="50px";
    l_img.style.height="50px";
    l_img.width="50";
    l_img.height="50";
    l_img.style.marginLeft=(devieW-l_img.width)/2+"px";
    l_img.style.marginTop=(devieH-l_img.height)/2+"px";
    shield2.appendChild(l_img);
});
/**
 * 显示loading动画
 * @param src:String 图片路径
 * @param s:Object 动画的样式    默认{"width": "50","height":"50"}
 * return true
 * */
function showLoading2(src,s){
    return;
    $("#l_img").attr("src",src);
    $("#l_img").css(s);
    var oPop=document.getElementById("content_group");
    var devieH=document.body.clientHeight||document.documentElement.clientHeight;
    var devieW=document.body.clientWidth||document.documentElement.clientWidth;
//    console.log(devieH+"___"+devieW);
//    console.log(s["height"],s["width"]);
    oPop.style.marginTop=(devieH-s["height"])/2+"px";
    oPop.style.marginLeft=(devieW-s["width"])/2+"px";
//    console.log(oPop.style.marginTop,oPop.style.marginLeft);
    document.getElementById("loading_group").style.display="block";
    return true;
}
/**
 * 显示loading动画
 * return true
 * */
function showLoading(){
    document.getElementById("shield2").style.display="block";
    return true;
}
/**
 * 显示loading动画
 * return true
 * */
function showLoadingb(){
    var oPop=document.getElementById("l_img");
    var devieH=document.documentElement.clientHeight||window.innerHeight;
    var devieW=document.body.clientWidth||document.documentElement.clientWidth;
    oPop.style.marginTop=(devieH-50)/2+"px";
    oPop.style.marginLeft=(devieW-50)/2+"px";
    document.getElementById("shield2").style.display="block";
    return true;
}
/**
 * 隐藏loading动画
 * return false;
 * */
function hideLoading(){
    document.getElementById("shield2").style.display="none";
    return false;
}

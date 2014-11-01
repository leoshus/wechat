package com.sdw.soft.common.reply.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdw.soft.common.bind.service.IBindService;
import com.sdw.soft.common.config.WechatConfig;
import com.sdw.soft.common.reply.service.IReplyService;
import com.sdw.soft.common.reply.vo.MessageType;
import com.sdw.soft.common.reply.vo.WechatArticleMessage;
import com.sdw.soft.common.reply.vo.WechatBaseMessage;
import com.sdw.soft.common.reply.vo.WechatMusicMessage;
import com.sdw.soft.common.reply.vo.WechatTextMessage;
import com.sdw.soft.common.service.ICommonService;
import com.sdw.soft.common.vo.WechatUserSample;
import com.sdw.soft.core.utils.FreemarkerUtils;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
/**
 * @author Sonicery_D
 * @date 2014年10月28日
 * @version 1.0.0
 **/
@Service
public class ReplyService implements IReplyService {

	private static final Logger logger = LoggerFactory.getLogger(ReplyService.class);
	
	private static final String REPLY_MESSAGE_TYPE_TEXT = "text";

	private static final String REPLY_MESSAGE_TYPE_MUSIC = "music";

	private static final String REPLY_MESSAGE_TYPE_NEWS = "news";

	private static final String DEFAULT_REPLY_MESSAGE = "抱歉，自动回复功能尚未开通 感谢您的关注！";
	
	private static final String DEFAULT_REPLY_ERROR = "系统异常,请稍后再试!";
	
	@Autowired
	private WechatConfig wechatConfig;
	
	@Autowired
	private ICommonService commonService;
	
	@Autowired
	private IBindService bindService;
	
	/* (non-Javadoc)
	 * @see com.sdw.soft.common.reply.service.impl.IReplyService#dealReceiveMessage(java.lang.String)
	 */
	public String dealReceiveMessage(String requestXml){
		String msgType = null;
		WechatBaseMessage baseMessage = this.fectchWechatMessage(requestXml, new Class[]{WechatBaseMessage.class});
		msgType = baseMessage.getMsgType();
		String responseMessage = "响应消息";
		logger.info("当前微信请求数据类型为:{}",msgType);
		String openId = baseMessage.getFromUserName();
		if(StringUtils.isBlank(openId)){//未获取到openid
			responseMessage = processReplyMessage(REPLY_MESSAGE_TYPE_TEXT,assembleTextMessage(baseMessage,DEFAULT_REPLY_ERROR),new Class[]{WechatTextMessage.class});
		}else{
			logger.info("当前请求用户的OpenId为:{}",openId);
			com.sdw.soft.common.bind.vo.WechatUser bindUser = bindService.fetchBindUser(openId);
			if(bindUser != null){//当前用户已经绑定
				responseMessage = replyMessage(msgType, baseMessage, responseMessage);
			}else{//当前用户尚未绑定
				Map<String,String> bindMap = new HashMap<String,String>();
				bindMap.put("url", "http://sydhappy.sinaapp.com/bind/user");
				responseMessage = processReplyMessage(REPLY_MESSAGE_TYPE_TEXT,assembleTextMessage(baseMessage,FreemarkerUtils.data2Template(bindMap, "bind.ftl")),new Class[]{WechatTextMessage.class});
			}
		}
		logger.info("responseMessage finally:{}",responseMessage);
		return responseMessage;
	}
	
	/**
	 * 根据请求的报文 作出响应
	 * @param msgType
	 * @param baseMessage
	 * @param responseMessage
	 * @return
	 */
	private String replyMessage(String msgType, WechatBaseMessage baseMessage,
			String responseMessage) {
		if(msgType != null){
			if(msgType.equals(MessageType.MESSAGE_TYPE_TEXT)){//处理文本消息
				logger.info("dealReceiveMessage---处理文本消息");
				Object message = assembleReplyMessage(baseMessage,REPLY_MESSAGE_TYPE_TEXT);
				responseMessage = processReplyMessage(REPLY_MESSAGE_TYPE_TEXT,message,new Class[]{WechatTextMessage.class});
				
			}else if(msgType.equals(MessageType.MESSAGE_TYPE_IMAGE)){//图片消息
				logger.info("dealReceiveMessage---图片消息");
				Object message = assembleReplyMessage(baseMessage,REPLY_MESSAGE_TYPE_TEXT);
				responseMessage = processReplyMessage(REPLY_MESSAGE_TYPE_TEXT,message,new Class[]{WechatTextMessage.class});
			}else if(msgType.equals(MessageType.MESSAGE_TYPE_LOCATION)){//地理位置消息
				Object message = assembleReplyMessage(baseMessage,REPLY_MESSAGE_TYPE_TEXT);
				responseMessage = processReplyMessage(REPLY_MESSAGE_TYPE_TEXT,message,new Class[]{WechatTextMessage.class});
				logger.info("dealReceiveMessage---地理位置消息");
			}else if(msgType.equals(MessageType.MESSAGE_TYPE_EVENT)){//事件消息
				logger.info("dealReceiveMessage---事件消息");
				Object message = assembleReplyMessage(baseMessage,REPLY_MESSAGE_TYPE_TEXT);
				responseMessage = processReplyMessage(REPLY_MESSAGE_TYPE_TEXT,message,new Class[]{WechatTextMessage.class});
			}else if(msgType.equals(MessageType.MESSAGE_TYPE_LINK)){//链接消息
				logger.info("dealReceiveMessage---链接消息");
				Object message = assembleReplyMessage(baseMessage,REPLY_MESSAGE_TYPE_TEXT);
				responseMessage = processReplyMessage(REPLY_MESSAGE_TYPE_TEXT,message,new Class[]{WechatTextMessage.class});
			}else if(msgType.equals(MessageType.MESSAGE_TYPE_VOICE)){//语言消息
				logger.info("dealReceiveMessage---语言消息");
				Object message = assembleReplyMessage(baseMessage,REPLY_MESSAGE_TYPE_TEXT);
				responseMessage = processReplyMessage(REPLY_MESSAGE_TYPE_TEXT,message,new Class[]{WechatTextMessage.class});
			}
		}
		return responseMessage;
	}
	private String processReplyMessage(String replyMessageType,Object message,Class[] clazz) {
		XStream xstream = new XStream(new DomDriver("UTF-8",
				new XmlFriendlyNameCoder("-_", "_")));
		xstream.autodetectAnnotations(true);
		xstream.processAnnotations(clazz);
		if(REPLY_MESSAGE_TYPE_MUSIC.equals(replyMessageType)){//音乐消息
			WechatMusicMessage music = (WechatMusicMessage)message;
			return xstream.toXML(music);
		}else if(REPLY_MESSAGE_TYPE_NEWS.equals(replyMessageType)){//图文消息
			WechatArticleMessage article = (WechatArticleMessage)message;
			return xstream.toXML(article);
		}else{//文本消息
			WechatTextMessage text = (WechatTextMessage)message;
			return xstream.toXML(text);
		}
	}
	/**
	 * 拼装响应消息
	 * @param baseMessage
	 * @param replyMessageTypeText
	 * @return
	 */
	private Object assembleReplyMessage(WechatBaseMessage baseMessage,String replyMessageType) {
		if(REPLY_MESSAGE_TYPE_MUSIC.equals(replyMessageType)){//响应音乐消息
			WechatMusicMessage message = new WechatMusicMessage();
			//TODO 音乐消息 ---具体信息添加
			return message;
		}else if(REPLY_MESSAGE_TYPE_NEWS.equals(replyMessageType)){//响应图文消息
			WechatArticleMessage message = new WechatArticleMessage();
			//TODO 图文消息----具体信息添加
			return message;
		}else{//响应文本消息
			WechatTextMessage replyMessage = new WechatTextMessage();
			replyMessage.setFuncFlag("1");//位0x0001被标志时，星标刚收到的消息。
			replyMessage.setCreateTime(new Long(new Date().getTime()).toString());
			replyMessage.setToUserName(baseMessage.getFromUserName());
			replyMessage.setFromUserName(wechatConfig.getWeixinId());
			replyMessage.setDatime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
			
			Map<String,String> resultMap = null;
			
			if(MessageType.MESSAGE_TYPE_EVENT.equals(baseMessage.getMsgType())){//事件处理
				if(MessageType.EVENT_TYPE_CLICK.equals(baseMessage.getEvent().toUpperCase())){//click事件
					logger.info("click事件");
				}else if(MessageType.EVENT_TYPE_SUBSCRIBE.equals(baseMessage.getEvent().toUpperCase())){//订阅事件
					logger.info("订阅事件");
					if(baseMessage.getEventKey() != null && !baseMessage.getEventKey().equals("")){//二维码扫描
						logger.info("二维码扫描处理!");
					}else{
						WechatUserSample wechatUser = new WechatUserSample();
						wechatUser.setOpenId(baseMessage.getFromUserName());
						commonService.saveWechatUser(wechatUser);
						resultMap = new HashMap<String,String>();
						resultMap.put("username", "");
						replyMessage.setContent(FreemarkerUtils.data2Template(resultMap, "welcome.ftl"));
						replyMessage.setOperation("欢迎您,关注syd的微信公共账号!\n\n1.美食查询\n2.公交路线\n3.关于Sonicery_D\n回复数字即可");
						replyMessage.setOperation("subscribe");
					}
				}else if(MessageType.EVENT_TYPE_UNSUBSCRIBE.equals(baseMessage.getEvent().toUpperCase())){//取消订阅事件
					logger.info("取消订阅事件");
					replyMessage.setContent("不要离开我~~~~(>_<)~~~~ ");
					replyMessage.setOperation("unsubscribe");
				}else if(MessageType.EVENT_TYPE_SCAN.equals(baseMessage.getEvent().toUpperCase())){//用户已关注时扫描二维码事件
					logger.info("用户已关注时扫描二维码事件");
					replyMessage.setContent("您已关注!");
					replyMessage.setOperation("event-qr-scan");
				}else if(MessageType.EVENT_TYPE_LOCATION.equals(baseMessage.getEvent().toUpperCase())){//用户上传地理位置事件
					logger.info("用户上传地理位置事件");
					logger.info("获取用户地理位置: lat : " + baseMessage.getLatitude());
					logger.info("获取用户地理位置: lng : " + baseMessage.getLongitude());
					logger.info("获取用户地理位置: precision : "
							+ baseMessage.getPrecision());
					replyMessage.setOperation("event-location");
				}else{
					logger.info("无对应消息类型");
					replyMessage.setContent(DEFAULT_REPLY_MESSAGE);
					replyMessage.setOperation("NO EVENT CAPTURE");
				}
			}else if(MessageType.MESSAGE_TYPE_LOCATION.equals(baseMessage.getMsgType())){//用户地理位置消息
				logger.info("用户地理位置消息");
				logger.info("用户上传地理位置事件");
				logger.info("获取用户地理位置: location-X : " + baseMessage.getLocationX());
				logger.info("获取用户地理位置: location-Y : " + baseMessage.getLocationY());
				replyMessage.setContent("您当前的位置为："+baseMessage.getLabel()+",location-X:"+baseMessage.getLocationX()+",location-Y:"+baseMessage.getLocationY());
				replyMessage.setOperation("event-location");
			}else if(MessageType.MESSAGE_TYPE_VOICE.equals(baseMessage.getMsgType())){//语言消息
				logger.info("语言消息");
				replyMessage.setContent("语言消息 尚未处理 敬请期待！");
				replyMessage.setOperation("voice_message");
				
			}else if(MessageType.MESSAGE_TYPE_IMAGE.equals(baseMessage.getMsgType())){//图片消息
				logger.info("图片消息");
				replyMessage.setContent("图片消息 尚未处理 敬请期待！");
				replyMessage.setOperation("image_message");
				
			}else if(MessageType.MESSAGE_TYPE_LINK.equals(baseMessage.getMsgType())){
				logger.info("链接消息");
				replyMessage.setContent("链接消息 尚未处理 敬请期待！");
				replyMessage.setOperation("link_message");
				
			}else{
				String command = baseMessage.getContent().trim();
				replyMessage.setContent(DEFAULT_REPLY_MESSAGE);
				if("1".equals(command)){
					replyMessage.setContent("他很懒 美食查询 还未完成 敬请期待!");
				}else if("2".equals(command)){
					replyMessage.setContent("他很懒 公交路线 还未完成 敬请期待!");
				}else if("3".equals(command)){
					replyMessage.setContent("<a href='http://weibo.com/1853131443'>Sonicery_D</a>  soft engineer 一枚!");
				}
//				replyMessage.setOperation("欢迎您,关注syd的微信公共账号!\n\n1.美食查询\n2.公交路线\n3.关于Sonicery_D\n回复数字即可");
//				resultMap.put("username", "");
				replyMessage.setContent(FreemarkerUtils.data2Template(resultMap, "welcome.ftl"));
				replyMessage.setOperation("NO-TEXT-ANSWER");
			}
			return replyMessage;
		}
	}
	/**
	 * 解析从微信过来的xml 为对象
	 * @param xml
	 * @param clazz
	 * @return
	 */
	private WechatBaseMessage fectchWechatMessage(String xml ,Class[] clazz){
		XStream xstream = new XStream(new DomDriver("UTF-8",new XmlFriendlyNameCoder("-_","_")));
		xstream.autodetectAnnotations(true);
		xstream.processAnnotations(clazz);
		return (WechatBaseMessage)xstream.fromXML(xml);
	}
	/**
	 * 拼装文本消息通用方法
	 * @return
	 */
	private WechatTextMessage assembleTextMessage(WechatBaseMessage baseMessage,String content) {
		WechatTextMessage replyMessage = new WechatTextMessage();
		replyMessage.setFuncFlag("1");//位0x0001被标志时，星标刚收到的消息。
		replyMessage.setCreateTime(new Long(new Date().getTime()).toString());
		replyMessage.setToUserName(baseMessage.getFromUserName());
		replyMessage.setFromUserName(wechatConfig.getWeixinId());
		replyMessage.setDatime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
		replyMessage.setContent(content);
		return replyMessage;
	}
}

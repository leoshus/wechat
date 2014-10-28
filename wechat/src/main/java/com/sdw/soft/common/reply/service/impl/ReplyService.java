package com.sdw.soft.common.reply.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sdw.soft.common.config.WechatConfig;
import com.sdw.soft.common.reply.service.IReplyService;
import com.sdw.soft.common.reply.vo.MessageType;
import com.sdw.soft.common.reply.vo.WechatArticleMessage;
import com.sdw.soft.common.reply.vo.WechatBaseMessage;
import com.sdw.soft.common.reply.vo.WechatMusicMessage;
import com.sdw.soft.common.reply.vo.WechatTextMessage;
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
	
	@Autowired
	private WechatConfig wechatConfig;
	/* (non-Javadoc)
	 * @see com.sdw.soft.common.reply.service.impl.IReplyService#dealReceiveMessage(java.lang.String)
	 */
	public String dealReceiveMessage(String requestXml){
		String msgType = null;
		WechatBaseMessage baseMessage = this.fectchWechatMessage(requestXml, new Class[]{WechatBaseMessage.class});
		msgType = baseMessage.getMsgType();
		String responseMessage = "响应消息";
		logger.info("当前微信请求数据类型为:{}",msgType);
		if(msgType != null){
			if(msgType.equals(MessageType.MESSAGE_TYPE_TEXT)){//处理文本消息
				Object message = assembleReplyMessage(baseMessage,REPLY_MESSAGE_TYPE_TEXT);
				responseMessage = processReplyMessage(REPLY_MESSAGE_TYPE_TEXT,message,new Class[]{WechatTextMessage.class});
				
			}else if(msgType.equals(MessageType.MESSAGE_TYPE_IMAGE)){//图片消息
				
			}else if(msgType.equals(MessageType.MESSAGE_TYPE_LOCATION)){//地理位置消息
				
			}else if(msgType.equals(MessageType.MESSAGE_TYPE_EVENT)){//事件消息
				
			}else if(msgType.equals(MessageType.MESSAGE_TYPE_LINK)){//链接消息
				
			}else if(msgType.equals(MessageType.MESSAGE_TYPE_VOICE)){//语言消息
				
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
					
				}else if(MessageType.EVENT_TYPE_SUBSCRIBE.equals(baseMessage.getEvent().toUpperCase())){//订阅事件
					if(baseMessage.getEventKey() != null && !baseMessage.getEventKey().equals("")){//二维码扫描
						logger.info("二维码扫描处理!");
					}else{
						replyMessage.setContent("欢迎关注 syd的公共账号!");
						replyMessage.setOperation("subscribe");
					}
				}else if(MessageType.EVENT_TYPE_UNSUBSCRIBE.equals(baseMessage.getEvent().toUpperCase())){//取消订阅事件
					replyMessage.setContent("不要离开我~~~~(>_<)~~~~ ");
					replyMessage.setOperation("unsubscribe");
				}else if(MessageType.EVENT_TYPE_SCAN.equals(baseMessage.getEvent().toUpperCase())){//用户已关注时扫描二维码事件
					replyMessage.setContent("您已关注!");
					replyMessage.setOperation("event-qr-scan");
				}else if(MessageType.EVENT_TYPE_LOCATION.equals(baseMessage.getEvent().toUpperCase())){//用户上传地理位置事件
					logger.info("获取用户地理位置: lat : " + baseMessage.getLatitude());
					logger.info("获取用户地理位置: lng : " + baseMessage.getLongitude());
					logger.info("获取用户地理位置: precision : "
							+ baseMessage.getPrecision());
					replyMessage.setOperation("event-location");
				}else{
					replyMessage.setContent(DEFAULT_REPLY_MESSAGE);
					replyMessage.setOperation("NO EVENT CAPTURE");
				}
			}else if(MessageType.MESSAGE_TYPE_LOCATION.equals(baseMessage.getMsgType())){//用户地理位置消息
				
			}else if(MessageType.MESSAGE_TYPE_VOICE.equals(baseMessage.getMsgType())){//语言消息
				
			}else if(MessageType.MESSAGE_TYPE_IMAGE.equals(baseMessage.getMsgType())){//图片消息
				
			}else if(MessageType.MESSAGE_TYPE_LINK.equals(baseMessage.getMsgType())){
				
			}else{
				replyMessage.setContent(DEFAULT_REPLY_MESSAGE);
				replyMessage.setOperation("NO-TEXT-ANSWER");
			}
		}
		return null;
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
}

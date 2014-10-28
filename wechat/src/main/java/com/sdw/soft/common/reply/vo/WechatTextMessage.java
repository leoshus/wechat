package com.sdw.soft.common.reply.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * @author Sonicery_D
 * @date 2014年10月28日
 * @version 1.0.0
 **/
@XStreamAlias("xml")
public class WechatTextMessage {
	@XStreamAlias("ToUserName")
	private String toUserName;

	@XStreamAlias("FromUserName")
	private String fromUserName;

	@XStreamAlias("CreateTime")
	private String createTime;

	@XStreamAlias("MsgType")
	private String msgType = "text";

	@XStreamAlias("Content")
	private String content;

	@XStreamAlias("FuncFlag")
	private String funcFlag;//位0x0001被标志时，星标刚收到的消息。

	@XStreamOmitField
	private String datime;

	@XStreamOmitField
	private String templateId;

	// 本次执行的操作
	@XStreamOmitField
	private String operation;

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFuncFlag() {
		return funcFlag;
	}

	public void setFuncFlag(String funcFlag) {
		this.funcFlag = funcFlag;
	}

	public String getDatime() {
		return datime;
	}

	public void setDatime(String datime) {
		this.datime = datime;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

}

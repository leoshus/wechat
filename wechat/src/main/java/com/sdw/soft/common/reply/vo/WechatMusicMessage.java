package com.sdw.soft.common.reply.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author Sonicery_D
 * @date 2014年10月28日
 * @version 1.0.0
 **/
/**
 *
 * 音乐消息实体
 */
@XStreamAlias("xml")
public class WechatMusicMessage {
	@XStreamAlias("ToUserName")
	private String toUserName;

	@XStreamAlias("FromUserName")
	private String fromUserName;

	@XStreamAlias("CreateTime")
	private String createTime;

	@XStreamAlias("MsgType")
	private String msgType = "music";

	@XStreamAlias("FuncFlag")
	private String funcFlag;

	@XStreamAlias("Music")
	private MusicUnit music;

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

	public String getFuncFlag() {
		return funcFlag;
	}

	public void setFuncFlag(String funcFlag) {
		this.funcFlag = funcFlag;
	}

	public MusicUnit getMusic() {
		return music;
	}

	public void setMusic(MusicUnit music) {
		this.music = music;
	}

}

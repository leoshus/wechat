package com.sdw.soft.common.reply.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * @author Sonicery_D
 * @date 2014年10月28日
 * @version 1.0.0
 **/
/**
 * 
 * 基础消息实体
 */
@XStreamAlias("xml")
public class WechatBaseMessage {
	@XStreamAlias("ToUserName")
	private String toUserName;

	@XStreamAlias("FromUserName")
	private String fromUserName;

	@XStreamAlias("CreateTime")
	private String createTime;

	@XStreamAlias("MsgType")
	private String msgType;

	@XStreamAlias("MsgId")
	private String msgId;

	// Text
	@XStreamAlias("Content")
	private String content;

	// Image
	@XStreamAlias("PicUrl")
	private String picUrl;

	@XStreamAlias("MediaId")
	private String mediaId;

	// Location
	@XStreamAlias("Location_X")
	private String locationX;

	@XStreamAlias("Location_Y")
	private String locationY;

	@XStreamAlias("Scale")
	private String scale;

	@XStreamAlias("Label")
	private String label;

	// Event
	@XStreamAlias("Event")
	private String event;

	@XStreamAlias("EventKey")
	private String eventKey;

	// Link
	@XStreamAlias("Title")
	private String title;

	@XStreamAlias("Description")
	private String description;

	@XStreamAlias("Url")
	private String url;

	// added by wanghuan 2013-10-30
	// 新增带参数二维码消息支持
	@XStreamAlias("Ticket")
	private String ticket;
	// end

	// added by wanghuan 2013-11-8
	// 新增用户提交地理位置信息
	@XStreamAlias("Latitude")
	private String latitude;

	@XStreamAlias("Longitude")
	private String longitude;

	@XStreamAlias("Precision")
	private String precision;

	// end

	// added by wanghuan 2013-11-15
	// 添加语音识别类型支持
	@XStreamAlias("Format")
	private String format;

	@XStreamAlias("Recognition")
	private String recognation;

	// end

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

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getLocationX() {
		return locationX;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	public String getLocationY() {
		return locationY;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getRecognation() {
		return recognation;
	}

	public void setRecognation(String recognation) {
		this.recognation = recognation;
	}

}

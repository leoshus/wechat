package com.sdw.soft.common.reply.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author Sonicery_D
 * @date 2014年10月28日
 * @version 1.0.0
 **/
/**
 * 
 * 图文消息实体
 */
@XStreamAlias("xml")
public class WechatArticleMessage {

	@XStreamAlias("ToUserName")
	private String toUserName;

	@XStreamAlias("FromUserName")
	private String fromUserName;

	@XStreamAlias("CreateTime")
	private String createTime;

	@XStreamAlias("MsgType")
	private String msgType = "news";

	@XStreamAlias("FuncFlag")
	private String funcFlag;

	@XStreamAlias("ArticleCount")
	private String articleCount;

	@XStreamAlias("Articles")
	private ArticlesUnit articles;

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

	public String getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(String articleCount) {
		this.articleCount = articleCount;
	}

	public ArticlesUnit getArticles() {
		return articles;
	}

	public void setArticles(ArticlesUnit articles) {
		this.articles = articles;
	}

}

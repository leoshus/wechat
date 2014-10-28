package com.sdw.soft.common.reply.vo;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @author Sonicery_D
 * @date 2014年10月28日
 * @version 1.0.0
 **/
@XStreamAlias("Articles")
public class ArticlesUnit {

	@XStreamImplicit(itemFieldName = "item")
	private List<ArticleItem> item;

	public List<ArticleItem> getItem() {
		return item;
	}

	public void setItem(List<ArticleItem> item) {
		this.item = item;
	}

}

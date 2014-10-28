package com.sdw.soft.common.reply.vo;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author Sonicery_D
 * @date 2014年10月28日
 * @version 1.0.0
 **/
@XStreamAlias("Music")
public class MusicUnit {

	@XStreamAlias("Title")
	private String title;

	@XStreamAlias("Description")
	private String description;

	@XStreamAlias("MusicUrl")
	private String musicUrl;

	@XStreamAlias("HQMusicUrl")
	private String hQMusicUrl;

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

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String gethQMusicUrl() {
		return hQMusicUrl;
	}

	public void sethQMusicUrl(String hQMusicUrl) {
		this.hQMusicUrl = hQMusicUrl;
	}

}

package com.tinet.api.boss2.model;

/**
* 公共语音库实体类
*<p>
* 文件名： PublicVoice.java
*<p>
* Copyright (c) 2006-2011 T&I Net Communication CO.,LTD.  All rights reserved.
* @author zhancy
* @since 1.0
* @version 1.0
*/
@SuppressWarnings("serial")
public class PublicVoice extends BaseStandardEntity implements java.io.Serializable {

	// Fields    
	private Integer voiceId;
	private String voiceName;
	private String path;
	private String description;
	private String url;

	// Constructors

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/** default constructor */
	public PublicVoice() {
	}

	/** full constructor */

	// Property accessors

	public String getVoiceName() {
		return this.voiceName;
	}

	public void setVoiceName(String voiceName) {
		this.voiceName = voiceName;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getVoiceId() {
		return voiceId;
	}

	public void setVoiceId(Integer voiceId) {
		this.voiceId = voiceId;
	}

	public PublicVoice(Integer voiceId, String voiceName, String path,
			String description, String url) {
		super();
		this.voiceId = voiceId;
		this.voiceName = voiceName;
		this.path = path;
		this.description = description;
		this.url = url;
	}

}
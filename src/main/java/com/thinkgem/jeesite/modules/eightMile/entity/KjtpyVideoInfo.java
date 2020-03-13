/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.eightMile.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 视频文件Entity
 * @author 武鹏飞
 * @version 2019-11-29
 */
/**
 * @author Administrator
 *
 */
public class KjtpyVideoInfo extends DataEntity<KjtpyVideoInfo> {
	
	private static final long serialVersionUID = 1L;
	private String htmlCode;		// 自动协议播放的HTML代码
	private String p2psHtmlCode;		// P2P协议播放时的HTML代码
	private String rtmpHtmlCode;		// RTMP协议播放时的HTML代码
	private String hlsHtmlCode;		// HLS 协议播放时的HTML代码
	private String p2psSwfUrl;		// P2P协议播放时的SWF地址
	private String rtmpSwfUrl;		// RTMP协议播放时的SWF地址
	private String hlsUrl;		// HLS协议播放时的视频地址
	private String virtualPath;		// 本视频在流服务器上的路径
	private String localPath;		// 本视频在服务器上的物理路径
	private String streamName;		// 流名字
	private String title;		// 频道标题
	private String thumbnail;		// 缩略图的URL
	private String playPassword;		// 播放密码
	private String publishPassword;		// 播放密码
	private String maxConcurrents;		// 频道的最大并发数
	private String tag;		// 频道标签
	private String disabled;		// 频道是否被禁用0|1
	private String available;		// 是否可用
	private String creationTime;		// 频道创建时的时间戳
	private String width;		// 宽度
	private String height;		// 高度
	private String duration;		// 长度，单位s
	private String framerate;		// 帧率
	private String bitrate;		// 视频码率
	private String standard;		// 标准, 取值有 NW: 流畅, SD: 标清, HD: 高清
	private String description;		// 视频备注
	private String category;		// 所在分类的名字
	private String cid;		// 所在分类的ID, 1为未分类
	private String cmsId;		// 标识点播的自定义ID
	private String arictleId;		// 文章关联id
	
	public KjtpyVideoInfo() {
		super();
	}
	
	

	public KjtpyVideoInfo(String id){
		super(id);
	}
	
	
	
	
	

	public KjtpyVideoInfo(String htmlCode, String p2psHtmlCode, String rtmpHtmlCode, String hlsHtmlCode,
			String p2psSwfUrl, String rtmpSwfUrl, String hlsUrl, String virtualPath, String localPath,
			String streamName, String title, String thumbnail, String playPassword,
			String maxConcurrents, String tag, String disabled, String available, String creationTime, String width,
			String height, String duration, String framerate, String bitrate, String standard, String description,
			String category, String cid, String cmsId) {
		super();
		this.htmlCode = htmlCode;
		this.p2psHtmlCode = p2psHtmlCode;
		this.rtmpHtmlCode = rtmpHtmlCode;
		this.hlsHtmlCode = hlsHtmlCode;
		this.p2psSwfUrl = p2psSwfUrl;
		this.rtmpSwfUrl = rtmpSwfUrl;
		this.hlsUrl = hlsUrl;
		this.virtualPath = virtualPath;
		this.localPath = localPath;
		this.streamName = streamName;
		this.title = title;
		this.thumbnail = thumbnail;
		this.playPassword = playPassword;
		this.maxConcurrents = maxConcurrents;
		this.tag = tag;
		this.disabled = disabled;
		this.available = available;
		this.creationTime = creationTime;
		this.width = width;
		this.height = height;
		this.duration = duration;
		this.framerate = framerate;
		this.bitrate = bitrate;
		this.standard = standard;
		this.description = description;
		this.category = category;
		this.cid = cid;
		this.cmsId = cmsId;
	}



	@Length(min=0, max=255, message="自动协议播放的HTML代码长度必须介于 0 和 255 之间")
	public String getHtmlCode() {
		return htmlCode;
	}

	public void setHtmlCode(String htmlCode) {
		this.htmlCode = htmlCode;
	}
	
	@Length(min=0, max=255, message="P2P协议播放时的HTML代码长度必须介于 0 和 255 之间")
	public String getP2psHtmlCode() {
		return p2psHtmlCode;
	}

	public void setP2psHtmlCode(String p2psHtmlCode) {
		this.p2psHtmlCode = p2psHtmlCode;
	}
	
	@Length(min=0, max=255, message="RTMP协议播放时的HTML代码长度必须介于 0 和 255 之间")
	public String getRtmpHtmlCode() {
		return rtmpHtmlCode;
	}

	public void setRtmpHtmlCode(String rtmpHtmlCode) {
		this.rtmpHtmlCode = rtmpHtmlCode;
	}
	
	@Length(min=0, max=255, message="HLS 协议播放时的HTML代码长度必须介于 0 和 255 之间")
	public String getHlsHtmlCode() {
		return hlsHtmlCode;
	}

	public void setHlsHtmlCode(String hlsHtmlCode) {
		this.hlsHtmlCode = hlsHtmlCode;
	}
	
	@Length(min=0, max=255, message="P2P协议播放时的SWF地址长度必须介于 0 和 255 之间")
	public String getP2psSwfUrl() {
		return p2psSwfUrl;
	}

	public void setP2psSwfUrl(String p2psSwfUrl) {
		this.p2psSwfUrl = p2psSwfUrl;
	}
	
	@Length(min=0, max=255, message="RTMP协议播放时的SWF地址长度必须介于 0 和 255 之间")
	public String getRtmpSwfUrl() {
		return rtmpSwfUrl;
	}

	public void setRtmpSwfUrl(String rtmpSwfUrl) {
		this.rtmpSwfUrl = rtmpSwfUrl;
	}
	
	@Length(min=0, max=255, message="HLS协议播放时的视频地址长度必须介于 0 和 255 之间")
	public String getHlsUrl() {
		return hlsUrl;
	}

	public void setHlsUrl(String hlsUrl) {
		this.hlsUrl = hlsUrl;
	}
	
	@Length(min=0, max=255, message="本视频在流服务器上的路径长度必须介于 0 和 255 之间")
	public String getVirtualPath() {
		return virtualPath;
	}

	public void setVirtualPath(String virtualPath) {
		this.virtualPath = virtualPath;
	}
	
	@Length(min=0, max=255, message="本视频在服务器上的物理路径长度必须介于 0 和 255 之间")
	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}
	
	@Length(min=0, max=255, message="流名字长度必须介于 0 和 255 之间")
	public String getStreamName() {
		return streamName;
	}

	public void setStreamName(String streamName) {
		this.streamName = streamName;
	}
	
	@Length(min=0, max=255, message="频道标题长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="缩略图的URL长度必须介于 0 和 255 之间")
	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	
	@Length(min=0, max=255, message="播放密码长度必须介于 0 和 255 之间")
	public String getPlayPassword() {
		return playPassword;
	}

	public void setPlayPassword(String playPassword) {
		this.playPassword = playPassword;
	}
	
	@Length(min=0, max=255, message="频道的最大并发数长度必须介于 0 和 255 之间")
	public String getMaxConcurrents() {
		return maxConcurrents;
	}

	public void setMaxConcurrents(String maxConcurrents) {
		this.maxConcurrents = maxConcurrents;
	}
	
	@Length(min=0, max=255, message="频道标签长度必须介于 0 和 255 之间")
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	@Length(min=0, max=255, message="频道是否被禁用0|1长度必须介于 0 和 255 之间")
	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	
	@Length(min=0, max=255, message="是否可用长度必须介于 0 和 255 之间")
	public String getAvailable() {
		return available;
	}

	public void setAvailable(String available) {
		this.available = available;
	}
	
	@Length(min=0, max=255, message="频道创建时的时间戳长度必须介于 0 和 255 之间")
	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	
	@Length(min=0, max=255, message="宽度长度必须介于 0 和 255 之间")
	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}
	
	@Length(min=0, max=255, message="高度长度必须介于 0 和 255 之间")
	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}
	
	@Length(min=0, max=255, message="长度，单位s长度必须介于 0 和 255 之间")
	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	@Length(min=0, max=255, message="帧率长度必须介于 0 和 255 之间")
	public String getFramerate() {
		return framerate;
	}

	public void setFramerate(String framerate) {
		this.framerate = framerate;
	}
	
	@Length(min=0, max=255, message="视频码率长度必须介于 0 和 255 之间")
	public String getBitrate() {
		return bitrate;
	}

	public void setBitrate(String bitrate) {
		this.bitrate = bitrate;
	}
	
	@Length(min=0, max=255, message="标准, 取值有 NW: 流畅, SD: 标清, HD: 高清长度必须介于 0 和 255 之间")
	public String getStandard() {
		return standard;
	}

	public void setStandard(String standard) {
		this.standard = standard;
	}
	
	@Length(min=0, max=255, message="视频备注长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=255, message="所在分类的名字长度必须介于 0 和 255 之间")
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	@Length(min=0, max=255, message="所在分类的ID, 1为未分类长度必须介于 0 和 255 之间")
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
	
	@Length(min=0, max=255, message="标识点播的自定义ID长度必须介于 0 和 255 之间")
	public String getCmsId() {
		return cmsId;
	}

	public void setCmsId(String cmsId) {
		this.cmsId = cmsId;
	}
	
	@Length(min=0, max=255, message="文章关联id长度必须介于 0 和 255 之间")
	public String getArictleId() {
		return arictleId;
	}

	public void setArictleId(String arictleId) {
		this.arictleId = arictleId;
	}



	public String getPublishPassword() {
		return publishPassword;
	}



	public void setPublishPassword(String publishPassword) {
		this.publishPassword = publishPassword;
	}
	
}
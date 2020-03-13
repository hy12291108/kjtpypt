package com.thinkgem.jeesite.modules.attachment.entity;

/**
 * Created by wangjing
 */
public class FileMeta {
    private String name;
    private String url;
    private String zsname;

    public String getZsname() {
		return zsname;
	}

	public void setZsname(String zsname) {
		this.zsname = zsname;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
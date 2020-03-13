package com.thinkgem.jeesite.modules.attachment.entity;

import java.util.List;

/**
 * Created by wangjing.
 */
public class FileMsg {
	
    private List<FileMeta> files;

    public List<FileMeta> getFiles() {
        return files;
    }

    public void setFiles(List<FileMeta> files) {
        this.files = files;
    }
}
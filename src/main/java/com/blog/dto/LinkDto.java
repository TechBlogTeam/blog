package com.blog.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class LinkDto {
    //主键
    private Integer linkId;
    //连接url
    private String linkUrl;
    //链接名称
    private String linkName;
    //链接配图
    private String linkImage;
    //打开方式
    private String linkTarget;
    //描述
    private String description;
    //是否可见
    private Integer linkStatus;
    //连接类型
    private Integer linkType;
    //连接类型
    private Integer linkSort;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    //创建时间
    private Date createTime;
    //更新时间
    private Date updateTime;

    public void setLinkId(Integer linkId){
        this.linkId = linkId;
    }
    public Integer getLinkId(){
        return linkId;
    }

    public void setLinkUrl(String linkUrl){
        this.linkUrl = linkUrl;
    }
    public String getLinkUrl(){
        return linkUrl;
    }

    public void setLinkName(String linkName){
        this.linkName = linkName;
    }
    public String getLinkName(){
        return linkName;
    }

    public void setLinkImage(String linkImage){
        this.linkImage = linkImage;
    }
    public String getLinkImage(){
        return linkImage;
    }

    public void setLinkTarget(String linkTarget){
        this.linkTarget = linkTarget;
    }
    public String getLinkTarget(){
        return linkTarget;
    }

    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }

    public void setLinkStatus(Integer linkStatus){
        this.linkStatus = linkStatus;
    }
    public Integer getLinkStatus(){
        return linkStatus;
    }

    public void setLinkType(Integer linkType){
        this.linkType = linkType;
    }
    public Integer getLinkType(){
        return linkType;
    }

    public void setLinkSort(Integer linkSort){
        this.linkSort = linkSort;
    }
    public Integer getLinkSort(){
        return linkSort;
    }

    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }
    public Date getCreateTime(){
        return createTime;
    }

    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }
    public Date getUpdateTime(){
        return updateTime;
    }
}

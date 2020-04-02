package com.blog.dto.article;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ArticleDto {
    //文章id
    private Integer articleId;
    //uuid
    private String articleUid;
    //标题
    private String articleTitle;
    //关键词
    private String  articleKeywords;
    //描述
    private String articleExcerpt;
    //文章内容
    private String articleContent;

    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date createTime;
    //更新时间
    public Date uUpdateTime;
    //作者
    private String articleAuthor;
    //分类
    private Integer articleTypeId;
    //来源
    private String articleFrom;
    //点击量
    public String articleClick;
    //文章排序
    public Integer articleOrder;
    //缩略图
    private String articleThumbnail;
    //评论量
    public Long commentCount;

    public void setArticleId(Integer articleId){
        this.articleId = articleId;
    }
    public Integer getArticleId(){
        return this.articleId;
    }

    public void setArticleUid(String articleUid){
        this.articleUid = articleUid;
    }
    public String getArticleUid(){
        return this.articleUid;
    }

    public void setArticleTitle(String articleTitle){
        this.articleTitle = articleTitle;
    }
    public String getArticleTitle(){
        return this.articleTitle;
    }

    public void setArticleKeywords(String articleKeywords){
        this.articleKeywords = articleKeywords;
    }
    public String getArticleKeywords(){
        return this.articleKeywords;
    }

    public void setArticleExcerpt(String articleExcerpt){
        this.articleExcerpt = articleExcerpt;
    }
    public String getArticleExcerpt(){
        return this.articleExcerpt;
    }

    public void setArticleContent(String articleContent){
        this.articleContent = articleContent;
    }
    public String getArticleContent(){
        return this.articleContent;
    }

    public void setArticleThumbnail(String articleThumbnail){
        this.articleThumbnail = articleThumbnail;
    }
    public String getArticleThumbnail(){
        return this.articleThumbnail;
    }

    public void setArticleAuthor(String articleAuthor){
        this.articleAuthor = articleAuthor;
    }
    public String getArticleAuthor(){
        return this.articleAuthor;
    }

    public void setArticleTypeId(Integer articleTypeId){
        this.articleTypeId = articleTypeId;
    }
    public Integer getArticleTypeId(){
        return this.articleTypeId;
    }

    public void setArticleFrom(String articleFrom){
        this.articleFrom = articleFrom;
    }
    public String getArticleFrom(){
        return this.articleFrom;
    }
}

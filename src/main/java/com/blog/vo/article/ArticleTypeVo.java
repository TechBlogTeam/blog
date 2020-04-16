package com.blog.vo.article;

public class ArticleTypeVo {
    //文章分类id
    private Integer articleTypeId;
    //文章分类名称
    private String articleTypeName;
    //文章分类描述
    private String articleTypeDescription;
    public void setArticleTypeId(Integer articleTypeId){
        this.articleTypeId = articleTypeId;
    }
    public Integer getArticleTypeId(){
        return  articleTypeId;
    }
    public void setArticleTypeName(String articleTypeName){
        this.articleTypeName = articleTypeName;
    }
    public String getArticleTypeName(){
        return  articleTypeName;
    }

    public void setArticleTypeDescription(String articleTypeDescription){
        this.articleTypeDescription = articleTypeDescription;
    }
    public String getArticleTypeDescription(){
        return  articleTypeDescription;
    }
}

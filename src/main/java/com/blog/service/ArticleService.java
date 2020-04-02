package com.blog.service;

import com.blog.common.utils.PagerUtil;
import com.blog.dto.article.ArticleDto;
import com.blog.dto.article.ArticleTypeDto;
import com.blog.entity.article.Article;
import com.blog.entity.article.ArticleType;

import java.util.List;

public interface ArticleService {
    /**
     * 根据page分页获取文章列表
     *
     * @return
     */
    List<Article> getArticleByPage(PagerUtil pageUtil);

    /**
     * 根据id获取详情
     *
     * @param article 文章内容
     * @return 文章id
     */
    int addArticle(ArticleDto article);
    /**
     * 根据文章总量
     *
     * @return total
     */
    int getArticleTotal();

    /**
     * 获取最新的一条文章
     *
     * @return 文章详情
     */
    Article getNewestArticle();

    /**
     * 获取指定uid的一条文章
     *
     * @return 文章详情
     */
    Article getArticleById(String articleUid);

    /**
     * 更新一条文章
     *
     * @return 文章详情
     */
    int updateArticle(ArticleDto article);

    /**
     * 更新文章的click
     *
     * @return 文章详情
     */
    int updateArticleClick(String articleUid);


    /**
     * 根据id获取详情
     *
     * @return 查询条件
     */
     List<ArticleType> getArticleTypeList();

    /**
     * 根据id获取详情
     *
     * @param params 新增内容
     * @return 新增后的文章分类id
     */
    int addArticleType(ArticleTypeDto params);

    /**
     * 根据id获取详情
     *
     * @param articleTypeId 文章分类id
     * @return 新增后的文章分类id
     */
    int deleteArticleType(Integer articleTypeId);


}

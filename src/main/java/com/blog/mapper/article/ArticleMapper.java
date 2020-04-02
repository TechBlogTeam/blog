package com.blog.mapper.article;

import com.blog.common.utils.PagerUtil;
import com.blog.dto.article.ArticleDto;
import com.blog.entity.article.Article;

import java.util.List;

public interface ArticleMapper {

    List<Article> getArticleByPage(PagerUtil pageUtil);

    int insertSelective(ArticleDto article);

    int updateSelective(ArticleDto article);

    int getArticleTotal();

    Article getNewestArticle();

    Article getArticleById(String articleUid);

    int updateArticleClick(String articleUid);

}

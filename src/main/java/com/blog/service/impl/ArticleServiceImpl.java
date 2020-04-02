package com.blog.service.impl;

import com.blog.common.utils.PagerUtil;
import com.blog.mapper.article.ArticleMapper;
import com.blog.mapper.article.ArticleTypeMapper;
import com.blog.dto.article.ArticleDto;
import com.blog.dto.article.ArticleTypeDto;
import com.blog.entity.article.Article;
import com.blog.entity.article.ArticleType;
import com.blog.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    @Resource
    private ArticleTypeMapper articleTypeMapper;


    @Override
    public List<Article> getArticleByPage(PagerUtil pageUtil){
        return articleMapper.getArticleByPage(pageUtil);
    }
    @Override

    public int addArticle(ArticleDto query){
        return articleMapper.insertSelective(query);
    }
    @Override
    public int getArticleTotal(){
        return articleMapper.getArticleTotal();
    }

    @Override
    public Article getNewestArticle(){
        return articleMapper.getNewestArticle();
    }

    @Override
    public Article getArticleById(String articleUid){
        return articleMapper.getArticleById(articleUid);
    }

    @Override
    public int updateArticle(ArticleDto article){
        return articleMapper.updateSelective(article);
    }

    @Override
    public int updateArticleClick(String articleUid){
        return articleMapper.updateArticleClick(articleUid);
    }


    @Override
    public List<ArticleType> getArticleTypeList(){
        return articleTypeMapper.getArticleTypeList();
    }

    @Override
    public int deleteArticleType(Integer articleTypeId){
        return articleTypeMapper.deleteArticleTypeById(articleTypeId);
    }
    @Override
    public int addArticleType(ArticleTypeDto params){
        return articleTypeMapper.addArticleType(params);
    }
}

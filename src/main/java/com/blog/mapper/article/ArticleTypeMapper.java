package com.blog.mapper.article;


import com.blog.dto.article.ArticleTypeDto;
import com.blog.entity.article.ArticleType;

import java.util.List;

public interface ArticleTypeMapper {
    int addArticleType(ArticleTypeDto params);

    List<ArticleType> getArticleTypeList();

    int deleteArticleTypeById(Integer articleTypeId);
}

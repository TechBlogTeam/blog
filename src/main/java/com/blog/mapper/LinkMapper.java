package com.blog.mapper;

import com.blog.common.utils.PagerUtil;
import com.blog.dto.LinkDto;
import com.blog.entity.Link;

import java.util.List;

public interface LinkMapper {
    List<Link> getLinksByPage(PagerUtil pagerUtil);

    Link getLinkById(Integer linkId);

    int getLinkTotal();

    int insertSelective(LinkDto linkDto);

    int deleteBatchLinks(Integer[] ids);

    int updateSelective(LinkDto linkDto);
}

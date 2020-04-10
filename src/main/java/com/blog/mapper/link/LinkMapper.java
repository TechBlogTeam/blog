package com.blog.mapper.link;

import com.blog.common.utils.PagerUtil;
import com.blog.dto.link.LinkDto;
import com.blog.entity.link.Link;

import java.util.List;

public interface LinkMapper {
    List<Link> getLinksByPage(PagerUtil pagerUtil);

    Link getLinkById(Integer linkId);

    int getLinkTotal();

    int insertSelective(LinkDto linkDto);

    int deleteBatchLinks(Integer[] ids);

    int updateSelective(LinkDto linkDto);
}

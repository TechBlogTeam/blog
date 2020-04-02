package com.blog.service.impl;

import com.blog.common.utils.PagerUtil;
import com.blog.mapper.LinkMapper;
import com.blog.dto.LinkDto;
import com.blog.entity.Link;
import com.blog.service.LinkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LinkServiceImpl implements LinkService {
    @Resource
    private LinkMapper linkMapper;

    @Override
    public List<Link> getLinksByPage(PagerUtil pageUtil){
        return linkMapper.getLinksByPage(pageUtil);
    }

    @Override
    public Link getLinkById(int linkId){
        return linkMapper.getLinkById(linkId);
    }

    @Override
    public int getLinksTotal(){
        return linkMapper.getLinkTotal();
    }

    @Override
    public int addLinks(LinkDto linkDto) {
        return linkMapper.insertSelective(linkDto);
    }

    @Override
    public boolean deleteBatchLinks(Integer[] ids) {
        return linkMapper.deleteBatchLinks(ids)>0;
    }

    @Override
    public int updateLinks(LinkDto linkDto) {
        return linkMapper.updateSelective(linkDto);
    }
}

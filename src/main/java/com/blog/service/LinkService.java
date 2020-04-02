package com.blog.service;

import com.blog.common.utils.PagerUtil;
import com.blog.dto.LinkDto;
import com.blog.entity.Link;

import java.util.List;

public interface LinkService {
    /**
     * 根据link列表
     * @param pageUtil 分页参数
     * @return 查询条件
     */
    List<Link> getLinksByPage(PagerUtil pageUtil);

    /**
     * 获取total
     * @return 查询条件
     */
    int getLinksTotal();

    /**
     * 获取链接详情
     * @return id
     */
    Link getLinkById(int linkId);

    /**
     * 友情链接添加
     * @param linkDto
     * @return linkId
     */
    int addLinks(LinkDto linkDto);

    /**
     * 友情链接删除
     * @param linkId 主键值
     * @return linkId
     */
    boolean deleteBatchLinks(Integer[] linkId);


    /**
     * 友情链接修改
     * @param linkDto 主键值
     * @return linkId
     */
    int updateLinks(LinkDto linkDto);
}

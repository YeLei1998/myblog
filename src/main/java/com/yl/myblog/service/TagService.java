package com.yl.myblog.service;

import com.yl.myblog.entity.Tag;
import com.yl.myblog.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author YeLei
 * @Date 2021/09/18 8:48
 * @Version 1.0
 */

public interface TagService {

    Tag saveTag(Tag type);

    Tag addTag(Long id);

    Tag updateTag(Long id, Tag tag);

    void deleteTag(Long id);

    //根据条件查询类型名称
    Tag getTagByName(String name);

    //分页
    Page<Tag> listTag(Pageable pageable);

}

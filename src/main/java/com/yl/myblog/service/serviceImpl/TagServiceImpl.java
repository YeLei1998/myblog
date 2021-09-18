package com.yl.myblog.service.serviceImpl;

import com.yl.myblog.NotFoundException;
import com.yl.myblog.dao.TagDao;
import com.yl.myblog.dao.TypeDao;
import com.yl.myblog.entity.Tag;
import com.yl.myblog.entity.Type;
import com.yl.myblog.service.TagService;
import com.yl.myblog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author YeLei
 * @Date 2021/09/18 8:52
 * @Version 1.0
 */

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagDao tagDao;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagDao.save(tag);
    }

    @Transactional
    @Override
    public Tag addTag(Long id) {
        return tagDao.findOne(id);
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag ids = tagDao.findOne(id);
        if (ids == null){
            throw new NotFoundException("不存在该类型！");
        }
        BeanUtils.copyProperties(tag,ids);
        return tagDao.save(ids);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagDao.delete(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.findByName(name);
    }

    @Transactional
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagDao.findAll(pageable);
    }
}

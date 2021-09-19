package com.yl.myblog.service.serviceImpl;

import com.yl.myblog.NotFoundException;
import com.yl.myblog.dao.TypeDao;
import com.yl.myblog.entity.Type;
import com.yl.myblog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author YeLei
 * @Date 2021/09/18 8:52
 * @Version 1.0
 */

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Transactional
    @Override
    public Type saveType(Type type) {
        return typeDao.save(type);
    }

    @Transactional
    @Override
    public Type addType(Long id) {
        return typeDao.findOne(id);
    }

    @Transactional
    @Override
    public Type updateType(Long id, Type type) {
        Type ids = typeDao.findOne(id);
        if (ids == null){
            throw new NotFoundException("不存在该类型！");
        }
        BeanUtils.copyProperties(type,ids);
        return typeDao.save(ids);
    }

    @Transactional
    @Override
    public void deleteTpye(Long id) {
        typeDao.delete(id);
    }

    @Override
    public Type getTypeByName(String name) {
        return typeDao.findByName(name);
    }

    @Transactional
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeDao.findAll(pageable);
    }

    @Override
    public List<Type> listType() {
        return typeDao.findAll();
    }
}

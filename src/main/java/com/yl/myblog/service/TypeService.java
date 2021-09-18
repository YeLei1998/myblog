package com.yl.myblog.service;

import com.yl.myblog.entity.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author YeLei
 * @Date 2021/09/18 8:48
 * @Version 1.0
 */

public interface TypeService {

    Type saveType(Type type);

    Type addType(Long id);

    Type updateType(Long id,Type type);

    void deleteTpye(Long id);

    //根据条件查询类型名称
    Type getTypeByName(String name);

    //分页
    Page<Type> listType(Pageable pageable);

}

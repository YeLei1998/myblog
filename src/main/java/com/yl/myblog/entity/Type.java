package com.yl.myblog.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author YeLei
 * @Date 2021/09/16 16:47
 * @Version 1.0
 * 分类主类
 */

@Data
@Entity
@Table(name = "t_type")
public class Type {
    @Id
    @GeneratedValue
    private Long id;
    //分类名称
    @NotBlank(message = "分类名称不能为空")
    private String name;

    //一个分类对应多篇博客
    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();
}

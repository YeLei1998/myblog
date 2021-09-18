package com.yl.myblog.entity;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author YeLei
 * @Date 2021/09/16 16:50
 * @Version 1.0
 * 标签主类
 */

@Data
@Entity
@Table(name = "t_tag")
public class Tag {
    @Id
    @GeneratedValue
    private Long id;
    //标签名称
    @NotBlank(message = "标签名称不能为空")
    private String name;

    //多个标签对应多篇博客
    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();
}

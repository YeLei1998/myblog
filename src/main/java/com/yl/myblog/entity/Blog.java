package com.yl.myblog.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author YeLei
 * @Date 2021/09/16 16:37
 * @Version 1.0
 * 博客主类
 */

@Data
@Entity
@Table(name = "t_blog")
public class Blog {
    @Id
    @GeneratedValue
    private Long id;
    //标题
    private String title;
    //内容
    private String content;
    //首图
    private String firstPicture;
    //标记
    private String flag;
    //浏览次数
    private Integer views;
    //赞赏
    private boolean appreciation;
    //版权
    private boolean shareStatement;
    //评论
    private boolean commentabled;
    //发布
    private boolean published;
    //推荐
    private boolean recommend;
    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    //更新时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    //一篇博客对应多个分类
    @ManyToOne
    private Type type;

    //多个博客对应多个标签
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();

    //多篇博客对应一个用户
    @ManyToOne
    private User user;

    //一篇博客可以多人评论
    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();
}

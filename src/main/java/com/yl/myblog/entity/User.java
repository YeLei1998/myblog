package com.yl.myblog.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author YeLei
 * @Date 2021/09/16 16:58
 * @Version 1.0
 */

@Data
@Entity
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    //昵称
    private String nickname;
    //用户名
    private String username;
    //密码
    private String password;
    //邮箱
    private String email;
    //头像
    private String avatar;
    //类型
    private Integer type;
    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    //更新时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    //一个用户对应多篇博客
    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();
}

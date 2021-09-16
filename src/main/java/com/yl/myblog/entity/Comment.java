package com.yl.myblog.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author YeLei
 * @Date 2021/09/16 16:52
 * @Version 1.0
 * 评论主类
 */

@Data
@Entity
@Table(name = "t_comment")
public class Comment {
    @Id
    @GeneratedValue
    private Long id;
    //昵称
    private String nickname;
    //邮箱
    private String email;
    //内容
    private String content;
    //头像
    private String avatar;
    //创建时间
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    //多个评论可以对应一篇博客
    @ManyToOne
    private Blog blog;

    //一个父评论对应多个子评论
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>();

    //多个子评论对应一个父评论
    @ManyToOne
    private Comment parentComment;
}

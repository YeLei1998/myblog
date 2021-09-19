package com.yl.myblog.vo;

import lombok.Data;

/**
 * @Author YeLei
 * @Date 2021/09/19 15:37
 * @Version 1.0
 */

@Data
public class BlogQuery {

    private String title;
    private Long typeId;
    private boolean recommend;
}

package com.bupt.weibo.dto;

import lombok.Data;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
@Data
public class CommentDTO {

    private Integer uid;
    private Integer tid;
    private Integer srcId;
    private String content;

}

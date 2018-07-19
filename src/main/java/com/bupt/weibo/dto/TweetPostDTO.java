package com.bupt.weibo.dto;

import lombok.Data;

@Data
public class TweetPostDTO {
    private String uid;
    private int srcId;
    private String content;
    private String imageUrl="";

}

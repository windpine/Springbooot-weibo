package com.bupt.weibo.entity.enums;

/**
 * @anthor tanshangou
 * @time 2018/7/14
 * @description
 */
public enum MessageType {

    MENTION(0),
    COMMENT(1),
    LIKES(2);

    private int type;

    MessageType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

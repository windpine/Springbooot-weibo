package com.bupt.weibo.entity.enums;

/**
 * @anthor tanshangou
 * @time 2018/7/12
 * @description
 */
public enum ResourceType {

    WORDCOUNT(0,"WordCount");

    private int type;
    private String desc;

    ResourceType() {
    }

    ResourceType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

package com.bupt.weibo.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
@Entity
public class Message {
    private int messageId;
    private int type;
    private String content;
    private int uid;

    @Id
    @Column(name = "messageID", nullable = false)
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "type", nullable = false)
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Basic
    @Column(name = "content", nullable = true, length = 1024)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "UID", nullable = false)
    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return messageId == message.messageId &&
                type == message.type &&
                uid == message.uid &&
                Objects.equals(content, message.content);
    }

    @Override
    public int hashCode() {

        return Objects.hash(messageId, type, content, uid);
    }
}

package com.bupt.weibo.entity;

import javax.persistence.*;
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
    private int srcId;
    private String content;
    private String uid;
    private String srcUid;

    @Id
    @Column(name = "messageID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "src_id", nullable = false)
    public int getSrcId(){return srcId;}
    public void setSrcId(int srcId) {this.srcId=srcId;}

    @Basic
    @Column(name = "src_uid", nullable = false,length = 32)
    public String getSrcUid() {
        return srcUid;
    }

    public void setSrcUid(String srcUid) {
        this.srcUid = srcUid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return messageId == message.messageId &&
                type == message.type &&
                srcId == message.srcId &&
                Objects.equals(uid , message.uid )&&
                Objects.equals(content, message.content)&&
                Objects.equals(srcUid,message.srcUid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(messageId, type, srcId, content, uid);
    }


}

package com.bupt.weibo.service;

import com.bupt.weibo.dto.MentionDTO;
import com.bupt.weibo.entity.Mention;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/13
 * @description
 */
public interface MentionService {

    List<Mention> getMentionsByUID(String UID);

    void addMention(MentionDTO mentionDTO);
}

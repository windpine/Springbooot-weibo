package com.bupt.weibo.service.impl;

import com.bupt.weibo.dto.MentionDTO;
import com.bupt.weibo.dto.mapper.MentionMapper;
import com.bupt.weibo.entity.Mention;
import com.bupt.weibo.repository.MentionRepository;
import com.bupt.weibo.service.MentionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/13
 * @description
 */

@Service
public class MentionServiceImpl implements MentionService {
    @Autowired
    MentionRepository mentionRepository;
    @Autowired
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    MentionMapper mentionMapper;

    @Override
    public List<Mention> getMentionsByUID(String UID) {

        return mentionRepository.findAllByUid(UID);
    }

    @Override
    public void addMention(MentionDTO mentionDTO) {

        Mention mention = mentionMapper.convertToEntity(mentionDTO);
        mentionRepository.save(mention);
    }
}

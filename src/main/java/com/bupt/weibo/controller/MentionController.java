package com.bupt.weibo.controller;

import com.bupt.weibo.dto.MentionDTO;
import com.bupt.weibo.dto.ResultDTO;
import com.bupt.weibo.entity.Mention;
import com.bupt.weibo.exception.ResultException;
import com.bupt.weibo.service.MentionService;
import com.bupt.weibo.utils.ApplicationUtils;
import com.bupt.weibo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/**
 * @anthor tanshangou
 * @time 2018/7/16
 * @description
 */
@RequestMapping("/mention")
@RestController
public class MentionController {

    @Autowired
    MentionService mentionService;

    @GetMapping("/{UID}")
    public ResponseEntity<ResultDTO> getAllMentions(
            @PathVariable(name = "UID") String UID, UriComponentsBuilder uriComponentsBuilder){
        HttpHeaders headers=ApplicationUtils.getHttpHeaders(uriComponentsBuilder,"");
        List<Mention> mentionList = mentionService.getMentionsByUID(UID);
        if(mentionList!=null){
            return new ResponseEntity<>(ResultUtils.onSuccess(mentionList),headers,HttpStatus.OK);
        }else {
            throw new ResultException("查找内容为空");
        }
    }

    @PostMapping
    public ResponseEntity<ResultDTO> addMention(UriComponentsBuilder uriComponentsBuilder,
                                                @RequestBody MentionDTO mentionDTO){

        HttpHeaders headers=ApplicationUtils.getHttpHeaders(uriComponentsBuilder,"/mention");
        mentionService.addMention(mentionDTO);
        return new ResponseEntity<>(ResultUtils.onSuccess(),headers,HttpStatus.OK);
    }
}

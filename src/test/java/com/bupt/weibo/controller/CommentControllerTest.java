package com.bupt.weibo.controller;

import com.alibaba.fastjson.JSONObject;
import com.bupt.weibo.dto.CommentDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * @anthor tanshangou
 * @time 2018/7/7
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CommentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void go() throws Exception{
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setContent("测试");
        commentDTO.setSrcId(-1);
        commentDTO.setTid(1);
        commentDTO.setUid(1);
        mvc.perform(MockMvcRequestBuilders.post("/comments/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JSONObject.toJSONString(commentDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
    }
}
package com.bupt.weibo.mapper;

import com.bupt.weibo.dto.UserDTO;
import com.bupt.weibo.dto.mapper.UserMapper;
import com.bupt.weibo.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;

/**
 * @anthor tanshangou
 * @time 2018/7/13
 * @description
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class MapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    public void test(){

        UserDTO userDTO = new UserDTO();
        userDTO.setUid("1");
        userDTO.setNickname("1");
        userDTO.setEmail("1");
        userDTO.setPassword("1");
        userDTO.setUsername("1");
        User user= userMapper.convertToEntity(userDTO);
    }
}

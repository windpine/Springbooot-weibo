package com.bupt.weibo.service.impl;

import com.bupt.weibo.dto.UserInfoDTO;
import com.bupt.weibo.entity.Relation;
import com.bupt.weibo.entity.RelationPK;
import com.bupt.weibo.entity.User;
import com.bupt.weibo.entity.UserInfo;
import com.bupt.weibo.repository.RelationRepository;
import com.bupt.weibo.repository.UserInfoRepository;
import com.bupt.weibo.service.RelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class RelationServiceImpl implements RelationService {

    @Autowired
    RelationRepository relationRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Override
    public void addARelation(String followId, String followerId){
        Relation relation=new Relation();
        relation.setFollowId(followId);
        relation.setFollowerId(followerId);
        relationRepository.save(relation);
    }

    @Override
    public void deleteARelation(String followId, String followerId){
        Relation relation=new Relation();
        relation.setFollowId(followId);
        relation.setFollowerId(followerId);
        relationRepository.delete(relation);
    }

    //获取某个uid的所有粉丝（followers）
    @Override
    public List<UserInfoDTO> getAllFollowers(String uid){
        //UserInfo userInfo;
        List<User> users=relationRepository.findFollowersByFollowId(uid);
        List<UserInfoDTO>  userInfoDTOs=new ArrayList<UserInfoDTO>();
        for(int i=0;i<users.size();i++){
            User user=users.get(i);
            UserInfo userInfo=userInfoRepository.findByUid(user.getUid());
            if(userInfo!=null){
                log.info("uid:",user.getUid());
                UserInfoDTO userInfoDTO=new UserInfoDTO(user.getUid(),
                        user.getNickname(),user.getUsername(),userInfo.getTweets(),
                        userInfo.getFollows(),userInfo.getFollowers(),userInfo.getAvatarUrl(),
                        userInfo.getSex(),user.getPassword(),user.getEmail());
                userInfoDTOs.add(userInfoDTO);
            }else{
                log.info("userInfo is null:");
                UserInfoDTO userInfoDTO=new UserInfoDTO(user.getUid(),
                        user.getNickname(),user.getUsername(),0,
                        0,0,"",
                        "无",user.getPassword(),user.getEmail());
                userInfoDTOs.add(userInfoDTO);
            }

        }
        return userInfoDTOs;

    }

    //获取被某个Uid关注的所有follows
    @Override
    public List<UserInfoDTO> getAllFollows(String uid){
        //UserInfo userInfo;
        List<User> users=relationRepository.findFollowsByFollowerId(uid);
        List<UserInfoDTO>  userInfoDTOs=new ArrayList<UserInfoDTO>();
        for(int i=0;i<users.size();i++){
            User user=users.get(i);
            UserInfo userInfo=userInfoRepository.findByUid(user.getUid());
            if(userInfo!=null){
                log.info("uid:",user.getUid());
                UserInfoDTO userInfoDTO=new UserInfoDTO(user.getUid(),
                        user.getNickname(),user.getUsername(),userInfo.getTweets(),
                        userInfo.getFollows(),userInfo.getFollowers(),userInfo.getAvatarUrl(),
                        userInfo.getSex(),user.getPassword(),user.getEmail());
                userInfoDTOs.add(userInfoDTO);
            }else{
                log.info("userInfo is null:");
                UserInfoDTO userInfoDTO=new UserInfoDTO(user.getUid(),
                        user.getNickname(),user.getUsername(),0,
                        0,0,"",
                        "无",user.getPassword(),user.getEmail());
                userInfoDTOs.add(userInfoDTO);
            }

        }
        return userInfoDTOs;
    }

    @Override
    public List<Relation> getAllRelations(){
        return relationRepository.findAll();
    }

    @Override
    public List<Relation> findById(String followId,String followerId){
        return relationRepository.findById(followId,followerId);
    }

}

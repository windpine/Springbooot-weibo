package com.bupt.weibo.utils;

/**
 * @anthor tanshangou
 * @time 2018/7/12
 * @description
 */
import com.bupt.weibo.exception.ResultException;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.shiro.codec.Base64;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by shusesshou on 2017/9/22.
 */
@Slf4j
public class SerializeUtils extends SerializationUtils{
    public static String serializeToString(Serializable obj){
        try{
            log.info("======开始序列化"+obj.getClass().getName());
            byte[] value = serialize(obj);
            return Base64.encodeToString(value);
        }catch (Exception e){
            throw new ResultException("serialize session error");
        }
    }

    public static <T> T deserializeFromString(String base64){
        try{
            byte[] objectData = Base64.decode(base64);
            return (T) deserialize(objectData);
        } catch (Exception e){
            throw new ResultException("deserialize session error");
        }
    }

    public static <T> Collection<T> deserializeFromStrings(Collection<String> base64s){
        try{
            List<T> list = Lists.newLinkedList();
            for(String base64 : base64s){
                byte[] objectData = Base64.decode(base64);
                T t = (T) deserialize(objectData);
                list.add(t);
            }
            return list;
        }catch (Exception e){
            throw new ResultException("deserialize session error");
        }
    }
}
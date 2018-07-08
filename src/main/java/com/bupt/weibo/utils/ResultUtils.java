package com.bupt.weibo.utils;

import com.bupt.weibo.dto.ResultDTO;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @anthor tanshangou
 * @time 2018/7/8
 * @description
 */
@Component
@Data
public class ResultUtils {

    public static final String SUCCESS = "success";
    public static final String ERROR = "error";


    public static ResultDTO onSuccess(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setStatus(SUCCESS);
        resultDTO.setData(null);
        resultDTO.setMsg(null);
        return resultDTO;
    }
    public static ResultDTO onSuccess(String data){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setStatus(SUCCESS);
        resultDTO.setData(data);
        resultDTO.setMsg(null);
        return resultDTO;
    }
    public static ResultDTO onError(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setStatus(ERROR);
        resultDTO.setData(null);
        return resultDTO;
    }
    public static ResultDTO onError(String msg){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setStatus(ERROR);
        resultDTO.setData(null);
        resultDTO.setMsg(msg);
        return resultDTO;
    }

}

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


    /**
     * 成功且有数据
     * @param data
     * @return
     */
    public static ResultDTO onSuccess(Object data){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setStatus(SUCCESS);
        resultDTO.setData(data);
        resultDTO.setMsg(null);
        resultDTO.setErrorCode(null);
        return resultDTO;
    }

    /**
     * 成功，无数据
     * @return
     */
    public static ResultDTO onSuccess(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setStatus(SUCCESS);
        resultDTO.setData(null);
        resultDTO.setMsg(null);
        resultDTO.setErrorCode(null);
        return resultDTO;
    }

    /**
     * 错误，无信息
     * @return
     */

    public static ResultDTO onError(){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setStatus(ERROR);
        resultDTO.setData(null);
        resultDTO.setErrorCode(null);
        return resultDTO;
    }

    /**
     * 错误，有信息
     * @param msg
     * @return
     */
    public static ResultDTO onError(String msg){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setStatus(ERROR);
        resultDTO.setData(null);
        resultDTO.setMsg(msg);
        resultDTO.setErrorCode(null);
        return resultDTO;
    }

}

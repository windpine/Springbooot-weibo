package com.bupt.weibo.handler;

import com.bupt.weibo.dto.ResultDTO;
import com.bupt.weibo.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @anthor tanshangou
 * @time 2018/7/8
 * @description
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultDTO handle(Exception e){
            log.error("system exception",e);

            //TODO 需要自定义异常类
            return ResultUtils.onError(e.getMessage());
    }
}

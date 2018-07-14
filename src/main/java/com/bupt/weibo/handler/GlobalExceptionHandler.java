package com.bupt.weibo.handler;

import com.bupt.weibo.dto.ResultDTO;
import com.bupt.weibo.exception.ResultException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @anthor tanshangou
 * @time 2018/7/8
 * @description
 */
@ControllerAdvice
@RestController
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 统一异常处理(Runtime)
     * @param
     * @return
     */

    @ExceptionHandler(value = ResultException.class)
    public ResponseEntity<Object> handleException(ResultException ex, HttpServletRequest request) throws URISyntaxException {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(new URI(request.getRequestURI()));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccessControlAllowOrigin("*");
        headers.setAccessControlAllowCredentials(true);
        log.error("-----exception Handler---Host: {} invokes url: {} ERROR: {} Cause:",request.getRemoteHost(),request.getRequestURL(), ex.getMessage(),ex.getCause());
        return handleExceptionInternal(ex,headers,HttpStatus.INTERNAL_SERVER_ERROR,request);
    }
    protected ResponseEntity<Object> handleExceptionInternal(ResultException ex, HttpHeaders headers, HttpStatus status, HttpServletRequest request){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setStatus(status.toString());
        resultDTO.setErrorCode(ex.getErrorCode());
        resultDTO.setUrl(request.getRequestURL().toString());
        resultDTO.setMsg(ex.getMessage());
        return new ResponseEntity(resultDTO,headers,status);
    }
}
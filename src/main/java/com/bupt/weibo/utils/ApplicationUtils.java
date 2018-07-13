package com.bupt.weibo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * @anthor tanshangou
 * @time 2018/7/12
 * @description
 */
public class ApplicationUtils {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationUtils.class);

    public static HttpHeaders getHttpHeaders(UriComponentsBuilder uriComponentsBuilder, String uri, Object... uriVariableValues){
        UriComponents uriComponents = uriComponentsBuilder.path(uri).buildAndExpand(uriVariableValues);
        HttpHeaders headers = new HttpHeaders();
        try {
            headers.setLocation(new URI(uriComponents.getPath()));
        }catch (Exception e){
            logger.error(e.getStackTrace().toString());
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}

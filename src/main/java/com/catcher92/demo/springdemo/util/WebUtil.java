package com.catcher92.demo.springdemo.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by caoxuedong on 2017/2/23.
 */
public class WebUtil {

    /**
     * <p>功能描述：通过nginx+tomcat来获取真实请求ip</p>
     * @return
     * @author caoxuedong
     * @date 2017/2/23 14:59
     */
    public static String getRemoteAddr(final HttpServletRequest request) {
        String remoteIp = request.getHeader("X-Real-IP");
        if (StringUtils.isBlank(remoteIp) || "unknown".equalsIgnoreCase(remoteIp)) {
            remoteIp = request.getRemoteAddr();
        }
        return remoteIp;
    }

}

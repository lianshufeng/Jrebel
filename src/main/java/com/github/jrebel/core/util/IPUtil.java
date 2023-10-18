package com.github.jrebel.core.util;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;


/**
 * 取ip的工具
 */
public class IPUtil {


    /**
     * 可能出现用户真实ip的头
     */
    private final static String[] headNames = new String[]{
            "X-FORWARDED-FOR",
            "Proxy-Client-IP",
            "WL-Proxy-Client-IP",
            "HTTP_CLIENT_IP",
            "HTTP_X_FORWARDED_FOR"
    };


    /**
     * 获取远程ip
     *
     * @param request
     * @return
     */
    public static String getRemoteIp(HttpServletRequest request) {
        for (String name : headNames) {
            String ip = request.getHeader(name);
            if (!StringUtils.isEmpty(ip)) {
                int at = ip.indexOf(",");
                if (at > -1) {
                    ip = ip.substring(0, at);
                }
                return ip;
            }
        }
        return request.getRemoteAddr();
    }


}

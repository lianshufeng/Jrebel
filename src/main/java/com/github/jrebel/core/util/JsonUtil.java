package com.github.jrebel.core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.util.StreamUtils;

import java.io.InputStream;

/**
 * json工具
 *
 * @作者 练书锋
 * @联系 oneday@vip.qq.com
 * @时间 2014年5月17日
 */
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

    static {
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 转换到json字符串
     *
     * @param object
     * @return
     * @throws Exception
     */
    public static String toJson(Object object, boolean format) {
        try {
            ObjectWriter objectWriter;
            if (format) {
                return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            } else {
                return objectMapper.writeValueAsString(object);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 转换到json字符串
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        return toJson(object, false);
    }


    /**
     * 转换为对象
     *
     * @param json
     * @param cls
     * @return
     * @throws Exception
     */
    public static <T> T toObject(String json, Class<T> cls) throws Exception {
        return objectMapper.readValue(json, cls);
    }

    /**
     * 载入文件到对象
     *
     * @param configName
     * @param cls
     * @return
     * @throws Exception
     */
    public static <T> T loadToObject(String configName, Class<T> cls) throws Exception {
        T t = null;
        InputStream inputStream = JsonUtil.class.getClassLoader().getResourceAsStream(configName);
        byte[] bin = StreamUtils.copyToByteArray(inputStream);
        String json = new String(bin, "UTF-8");
        t = toObject(json, cls);
        inputStream.close();
        return t;
    }


}

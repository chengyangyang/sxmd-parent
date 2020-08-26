package com.sxmd.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sxmd.constant.ConstantSystem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * Description:  response 工具类
 *
 * @author cy
 * @date 2020年06月18日 9:06
 * Version 1.0
 */
@Slf4j
public class ResponseUtil {

    /**
     * Description:   response 直接返回
     *
     * @param response:
     * @param map:      参数
     * @return void
     * @author cy
     * @date 2020/6/18 9:08
     */
    public static void directResponse(HttpServletResponse response, Map<String, String> map) {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.setCharacterEncoding(ConstantSystem.CHARSET_UTF_8);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        String resBody = null;
        try (PrintWriter printWriter = response.getWriter()) {
            resBody = objectMapper.writeValueAsString(map);
            printWriter.print(resBody);
            printWriter.flush();
        } catch (IOException e) {
            log.warn("io 异常", e);
        }
    }

}

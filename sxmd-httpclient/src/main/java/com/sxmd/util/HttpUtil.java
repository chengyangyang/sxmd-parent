package com.sxmd.util;

import com.sxmd.constant.ConstantSystem;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:  http 请求工具类
 *
 * @author cy
 * @date 2020年08月24日 11:09
 * Version 1.0
 */
@Slf4j
public class HttpUtil {

    private HttpUtil() {
    }

    /**
     * Description:  get 请求
     *
     * @param url:    路径
     * @param params: 参数 参数位置是params
     * @return java.lang.String
     * @author cy
     * @date 2020/8/24 11:13
     */
    public static String doGet(String url, Map<String, String> params) {
        String result = null;
        HttpResponse response = null;
        try (
                // 创建HttpClient对象
                CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        ) {
            URIBuilder uriBuilder = new URIBuilder(url);
            // 以拼接的形式
            if (params != null && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    uriBuilder.setParameter(entry.getKey(), entry.getValue());
                }
            }
            URI uri = uriBuilder.build();
            // 创建get请求
            HttpGet httpGet = new HttpGet(uri);
            response = httpClient.execute(httpGet);
            // 返回200，请求成功
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 结果返回
                result = EntityUtils.toString(response.getEntity(), ConstantSystem.CHARSET_UTF_8);
                log.debug("http client get uri[{}],请求成功。数据[{}]", uri, result);
            } else {
                log.warn("http client get uri[{}],请求失败", uri);
            }
        } catch (Exception e) {
            log.warn("http client get url[{}],请求失败", url);
            log.warn("http client get 请求失败", e);
        } finally {
            if (response != null) {
                try {
                    ((CloseableHttpResponse) response).close();
                } catch (IOException e) {
                    log.warn(ConstantSystem.ERROR_MESSAGE_CLOSE_STREAM, e);
                }
            }
        }
        return result;
    }

    /**
     * Description:   post 请求 表单提交格式
     *
     * @param url:    路径
     * @param params: 参数位置  body
     * @return java.lang.String
     * @author cy
     * @date 2020/8/24 13:36
     */
    public static String doPostForm(String url, Map<String, String> params) {
        String result = null;
        HttpResponse response = null;
        try (
                // 创建HttpClient对象
                CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        ) {
            HttpPost httpPost = new HttpPost(url);
            if (params != null && !params.isEmpty()) {
                List<NameValuePair> pairs = new ArrayList<>();
                for (Map.Entry<String, String> obj : params.entrySet()) {
                    NameValuePair pair = new BasicNameValuePair(obj.getKey(), obj.getValue());
                    pairs.add(pair);
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs, ConstantSystem.CHARSET_UTF_8);
                httpPost.setEntity(entity);
            }
            response = httpClient.execute(httpPost);
            // 返回200，请求成功
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 结果返回
                result = EntityUtils.toString(response.getEntity(), ConstantSystem.CHARSET_UTF_8);
                log.debug("http client post uri[{}],请求成功。数据[{}]", url, result);
            } else {
                log.warn("http client post uri[{}],请求失败", url);
            }
        } catch (Exception e) {
            log.warn("http client post url[{}],请求失败", url);
            log.warn("http client post 请求失败", e);
        } finally {
            if (response != null) {
                try {
                    ((CloseableHttpResponse) response).close();
                } catch (IOException e) {
                    log.warn(ConstantSystem.ERROR_MESSAGE_CLOSE_STREAM, e);
                }
            }
        }
        return result;
    }


    /**
     * Description:   post 请求
     *
     * @param url:     路径
     * @param jsonStr: json 字符串
     * @return java.lang.String
     * @author cy
     * @date 2020/8/24 13:36
     */
    public static String doPostJson(String url, String jsonStr) {
        return doPost(url, jsonStr, ContentType.APPLICATION_JSON);
    }

    /**
     * Description:   post 请求
     *
     * @param url:     路径
     * @param jsonStr: json 字符串
     * @param jsonStr: contentType 内容格式
     * @return java.lang.String
     * @author cy
     * @date 2020/8/24 13:36
     */
    public static String doPost(String url, String jsonStr, ContentType contentType) {
        String result = null;
        HttpResponse response = null;
        try (
                // 创建HttpClient对象
                CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        ) {
            HttpPost httpPost = new HttpPost(url);
            if (jsonStr != null && !jsonStr.isEmpty()) {
                StringEntity stringEntity = new StringEntity(jsonStr, "UTF-8");
                stringEntity.setContentType(contentType.toString());
                httpPost.setEntity(stringEntity);
            }
            response = httpClient.execute(httpPost);
            // 返回200，请求成功
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 结果返回
                result = EntityUtils.toString(response.getEntity(), "UTF-8");
                log.debug("http client post uri[{}],请求成功。数据[{}]", url, result);
            } else {
                log.warn("http client post uri[{}],请求失败", url);
            }
        } catch (Exception e) {
            log.warn("http client post url[{}],请求失败", url);
            log.warn("http client post 请求失败", e);
        } finally {
            if (response != null) {
                try {
                    ((CloseableHttpResponse) response).close();
                } catch (IOException e) {
                    log.warn(ConstantSystem.ERROR_MESSAGE_CLOSE_STREAM, e);
                }
            }
        }
        return result;
    }

}

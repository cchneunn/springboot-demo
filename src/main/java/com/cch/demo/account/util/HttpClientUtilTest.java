package com.cch.demo.account.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpStatus;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author caiconghao
 */
public class HttpClientUtilTest {

    private static String TOKEN;

    /**
     * 更新TOKEN
     *
     * @param token 新的token
     */
    public static void refreshToken(@NotNull String token) {
        TOKEN = token;
    }

    /**
     * 最简单的GET方法
     *
     * @param url 地址
     * @return 返回体
     */
    public static String get(@NotNull String url) throws IOException {
        return get(url, null, null);
    }

    /**
     * 包含参数的GET方法
     *
     * @param url 地址
     * @param params 参数
     * @return 返回体
     */
    public static String get(@NotNull String url, Map<String, String> params) throws IOException {
        return get(url, null, params);
    }

    /**
     * 包含参数和请求头的GET方法
     *
     * @param url 地址
     * @param headers 头信息
     * @param params 请求参数
     * @return 返回体
     */
    public static String get(@NotNull String url, Map<String, String> headers, Map<String, String> params) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        InputStream input = null;
        String result = null;

        if(StringUtils.isEmpty(TOKEN)) {
            // todo token为空，抛异常或者在这里重新去获取token
        }

        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            // 设置URL参数
            if(params != null && !params.isEmpty()) {
                params.forEach(uriBuilder::addParameter);
            }

            HttpGet httpGet = new HttpGet(uriBuilder.build());
            // 设置其他头信息
            httpGet.addHeader("token", TOKEN);
            if(headers != null && !headers.isEmpty()) {
                headers.forEach(httpGet::addHeader);
            }

            response = httpClient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.OK.value()) {
                // todo 返回code不是200的情况
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                input = entity.getContent();
                result = IOUtils.toString(input, StandardCharsets.UTF_8);
            }

        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        } finally {
            if(input != null) {
                input.close();
            }
            if(response != null) {
                response.close();
            }
            if(httpClient != null) {
                httpClient.close();
            }
        }
        // 这里简单的返回String，可以根据需要将String变形成任何类型
        return result;
    }
}

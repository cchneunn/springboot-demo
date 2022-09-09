package com.cch.demo.account.util;

import com.google.gson.Gson;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

/**
 * @author caiconghao
 */
@Slf4j
public class HttpClientUtil {

    public static String TOKEN;
//    private static final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
    /**
     * 包含参数的POST方法
     *
     * @param url 地址
     * @param params 参数
     * @return 返回体
     */
    public static String post(@NotNull String url, Map<String, String> params) throws IOException {
        Gson gson = new Gson();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        InputStream input = null;
        String result = null;
//        result = post(url,null,params);
        HttpPost httpPost = new HttpPost(url);
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000) //连接超时时间
                .setConnectionRequestTimeout(1000) //从连接池中取的连接的最长时间
                .setSocketTimeout(10 * 1000) //数据传输的超时时间
                .build();
        httpPost.setConfig(config);
       // httpPost.setHeader("Content-Type","multipart/form-data;charset=utf-8");

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("appId",params.get("appId") );
        builder.addTextBody("appSecret",params.get("appSecret"));
        HttpEntity multipart = builder.build();
        httpPost.setEntity(multipart);
        try {
            response = httpClient.execute(httpPost);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.OK.value()) {
                // todo 返回code不是200的情况
                //response = httpClient.execute(httpPost);
                System.out.println(url+"返回码不是200呦！！！！！！！！");
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                input = entity.getContent();
                result = IOUtils.toString(input, StandardCharsets.UTF_8);
            }

        } catch (IOException e) {
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
        Map<String,Object> returnMap = new HashMap<String,Object>();
        returnMap = gson.fromJson(result,returnMap.getClass());
        log.info(returnMap.toString());
        //住建局返回格式{"status": ,"message": ,"data" }
        TOKEN = (String)returnMap.get("data");
        return TOKEN;
//        Gson gson = new Gson();
//        CloseableHttpClient httpClient = HttpClients.createDefault();
//        CloseableHttpResponse response = null;
//        InputStream input = null;
//        String result = null;
//        String result = post(url,null,params);

//        Map<String,Object> returnMap = new HashMap<String,Object>();
//        returnMap = gson.fromJson(result,returnMap.getClass());
//        log.info(returnMap.toString());
//        //住建局返回格式{"status": ,"message": ,"data" }
//        TOKEN = (String)returnMap.get("data");
//        return TOKEN;
    }
    /**
     * 包含参数和请求头的POST方法
     *
     * @param url 地址
     * @param headers 头信息
     * @param params 请求参数
     * @return 返回体
     */
    public static String post(@NotNull String url, Map<String, String> headers, Map<String, String> params) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        InputStream input = null;
        String result = null;
        int statusCode = 0;
        Gson gson = new Gson();

        HttpPost httpPost = new HttpPost(url);
        if(params != null && !params.isEmpty()) {
            httpPost.setEntity(new StringEntity(gson.toJson(params),"utf-8"));
        }
        //即使没有请求头为空，也得设置请求头格式
        httpPost.setHeader("Content-Type","application/json;charset=utf-8");
        if(headers != null && !headers.isEmpty()&&headers.containsKey("token")){
        headers.put("token",TOKEN);}
        if(headers != null && !headers.isEmpty()) {
            headers.forEach((key, value) -> {
                httpPost.addHeader(key, value);
                System.out.println("key:"+key+"value:"+value);
            });
        }

        try {
            response = httpClient.execute(httpPost);
            statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != HttpStatus.OK.value()) {
                // todo 返回code不是200的情况
                log.error("向住建局发请求情况异常，请求返回体为：{}",response.toString());
            }else{
                log.error("向住建局发请求情况正常，请求返回体为：{}",response.toString());
            }
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                input = entity.getContent();
                result = IOUtils.toString(input, StandardCharsets.UTF_8);
            }

        } catch (IOException e) {
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

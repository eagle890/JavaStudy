package com.qdc.demoeurekaconsumer1.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.netflix.eureka.registry.Key.KeyType.JSON;

public class HttpClientWithBasicAuth {
    public HttpClientWithBasicAuth(){
    }


    private String getHeader(String UserName,String Password){
        //auth由两部分组成：Username和Password组成
        String auth = UserName + ":" + Password;

        //使用Base64将auth进行加密
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);
        System.out.println("authHeader:"+authHeader);
        return authHeader;
    }

    public String send(String url, String UserName, String Password, Map<String ,String> params){
        System.out.println("********");
        System.out.println(url);
        System.out.println(UserName);
        System.out.println(Password);
        System.out.println(params);
        System.out.println("********");

        //实例化一个post对象，
        HttpPost post = new HttpPost(url);
//      HttpGet get =  new HttpGet(url);

        CloseableHttpClient client = HttpClients.createDefault();
        //组织请求参数，在获取token时参数为grant——type和scope
        List<NameValuePair> paramList = new ArrayList<NameValuePair>();

        if(params != null && params.size() > 0){
            Set<String> keySet = params.keySet();
            for(String key:keySet){
                paramList.add(new BasicNameValuePair(key,params.get(key)));
            }
        }

        //调用setEntity()方法设置要提交的数据，将paramList参数转换为UTF-8格式进行提交
        try{
            //设置要提交的数据
            post.setEntity(new UrlEncodedFormEntity(paramList,"utf-8"));
            System.out.println("setEntity:"+new UrlEncodedFormEntity(paramList,"utf-8"));
        }catch (UnsupportedEncodingException e1){
            e1.printStackTrace();
        }
        //在设置的请求中将Baisc Auth信息添加到Post请求包头中。
        post.addHeader("Authorization",getHeader(UserName,Password));
        //执行提交请求，execute执行post请求，并对返回的结果进行判断。
        String responseContent = null;
        CloseableHttpResponse response = null;

        try {
            response = client.execute(post);
            int status_code = response.getStatusLine().getStatusCode();
            System.out.println("status_code:"+status_code);
//            if(response.getStatusLine().getStatusCode() == 200){
                HttpEntity entity = response.getEntity();
                responseContent = EntityUtils.toString(entity,"UTF-8");
//            }
            System.out.println("responseContent:"+responseContent);
        }catch (ClientProtocolException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(response !=null)
                    response.close();
            }catch (IOException e){
                e.printStackTrace();
            }finally {
                try {
                    if(client != null)
                        client.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return responseContent;
    }
}

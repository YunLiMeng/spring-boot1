package com.example.demo.util;


import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class HttpUtilV2 {
    public HttpUtilV2(){
        this.getHttpClient();
    }
	/**
	 * 日志
	 */
    private static Logger logger = LoggerFactory.getLogger(HttpUtilV2.class);
    
    //常用mediaType START
    
    /*JSON mediaType*/
    public static final MediaType MEDIA_TYPE_JSON = MediaType.parse("application/json; charset=utf-8");
    
    /*通用mediaType*/
    public static final MediaType MEDIA_TYPE_ALL = MediaType.parse("application/octet-stream");
    
    //常用mediaType END	
    private OkHttpClient okHttpClient = null;
    
    public OkHttpClient getHttpClient() {
    	return this.getHttpClient(null);
    }
    public OkHttpClient getHttpClient(OkHttpClient.Builder builder) {
    	if(okHttpClient == null) {
    		synchronized (this) {
				if(okHttpClient == null) {
					logger.info("Create client Start--------");
					if(builder == null) {
						logger.info("use default okHttpClientBuilder");
						//设置OkHttpClient参数
						OkHttpClient.Builder okBuilder = new OkHttpClient.Builder();
						ConnectionPool connectionPool = new ConnectionPool(20, 5000, TimeUnit.MILLISECONDS);
						//设置连接池
						okBuilder.connectionPool(connectionPool);
						//设置连接超时时间(10s)
						okBuilder.connectTimeout(10000, TimeUnit.MILLISECONDS);
						//设置读超时时间为无限
						okBuilder.readTimeout(0, TimeUnit.MILLISECONDS);
						//设置写超时时间为无限
						okBuilder.writeTimeout(0, TimeUnit.MILLISECONDS);
						okHttpClient = okBuilder.build();
						//设置最大并发总数
						okHttpClient.dispatcher().setMaxRequests(200);
						//设置单个主机最大并发数
						okHttpClient.dispatcher().setMaxRequestsPerHost(50);
					}else {
						logger.info("use custom okHttpClientBuilder");
						okHttpClient = builder.build();
						//设置最大并发总数
						okHttpClient.dispatcher().setMaxRequests(200);
						//设置单个主机最大并发数
						okHttpClient.dispatcher().setMaxRequestsPerHost(50);
					}
					logger.info("OkHttpClient created");
				}
			}
    	}
    	logger.info("get OkHttpClient");
    	return okHttpClient;
    }
    
    public synchronized OkHttpClient reInitClientAndGet(OkHttpClient.Builder okBuilder) {
    	logger.info("re init OkHttpClient");
    	okHttpClient = okBuilder.build();
    	return okHttpClient;
    }
    
    public Request postRequest(String url,Map<String,String> param) {
    	Request.Builder requestBuilder = new Request.Builder();
    	requestBuilder.url(url);
    	//如果存在参数，则准备参数
    	if(param != null && !param.isEmpty()) {
    		FormBody.Builder bodyBuilder = new FormBody.Builder() ;
    		for(String key : param.keySet()) {
    			bodyBuilder.add(key, param.get(key));
    		}
    		requestBuilder.post(bodyBuilder.build());
    	}
    	Request request = requestBuilder.build();
    	return request;
    }
    
    public Request getRequest(String url) {
    	Request.Builder requestBuilder = new Request.Builder();
    	requestBuilder.url(url);
    	//设置为get请求
    	requestBuilder.get();
    	Request request = requestBuilder.build();
    	return request;
    	
    }
    
    public Request postRequestBodyRequset(String url ,String body) {
    	RequestBody requestBody = RequestBody.create(MEDIA_TYPE_JSON, body);
    	Request.Builder requestBuilder = new Request.Builder();
    	requestBuilder.url(url);
    	//设置请求体
    	requestBuilder.post(requestBody);
    	Request request = requestBuilder.build();
    	return request;
    }
    
    public Request fileUploadRequest(String url,Map<String,String> textParam,String[] fileNames,File [] files) {
    	MultipartBody.Builder requestBodyBuilder = new MultipartBody.Builder();
    	//处理普通文本参数
    	for(String key : textParam.keySet()) {
    		requestBodyBuilder.addFormDataPart(key, textParam.get(key));
    	}
    	//处理文件参数
    	for(int i = 0 ;i < fileNames.length && i< files.length ; i++) {
    		requestBodyBuilder.addFormDataPart(fileNames[i], fileNames[i], RequestBody.create(MEDIA_TYPE_ALL, files[i]) );
    	}
    	Request.Builder requestBuilder = new Request.Builder();
    	requestBuilder.url(url);
    	requestBuilder.post(requestBodyBuilder.build());
    	Request request = requestBuilder.build();
    	return request;
    }
    
    public Response syncExecuteRequest(Request request) {
    	try {
			return this.getHttpClient().newCall(request).execute();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
    }
    
    public String executeRequestGetString(Request request) {
    	Response response = this.syncExecuteRequest(request);
    	if(response == null) {
    		return null;
    	}
    	try {
			return response.body().string();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			return null;
		}
    }
    
    
}

package com.gomeplus.comx.utils.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gomeplus.comx.context.Context;
import com.gomeplus.comx.utils.rest.clients.ApacheHttpClient;
import com.gomeplus.comx.utils.rest.clients.HttpClientX;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xue on 12/15/16.
 */
public class RequestMessage implements ArrayAccessBase{
    public static final String METHOD_GET      = "get";
    public static final String METHOD_POST     = "post";
    public static final String METHOD_DELETE   = "delete";
    public static final String METHOD_PUT      = "put";
    static final String HEADER_FIELD_TRACE_ID           = "X-Gomeplus-Trace-Id";
    static final String HEADER_FIELD_X_FORWARDED_FOR    = "X-Forwarded-For";
    static final String QUERY_FIELD_TRACE_ID            = "traceId";
    static final String DEFAULT_TRACE_ID_PREFIX         = "COMX";

    // TODO
    protected HashMap<String, String>   commonParameterHolder;

    protected Url                       url;
    protected String                    method;
    protected Map                       data;
    protected HashMap<String, String>   headerParameters;
    protected Integer                   timeout;

    // constructors;
    public RequestMessage() {
    }
    public RequestMessage(Url url, String method, Map data, HashMap<String, String> headerParameters, Integer timeout) {
        this.url                = url;
        this.method             = method;
        this.data               = data;
        this.headerParameters   = headerParameters;
        this.timeout            = timeout;
    }



    // implements interface
    // TODO containsKey 实现
    public boolean containsKey(Object key) {
        return true;
    }
    public Object get(Object key) {
        if (key.equals("url"))                  return url;
        if (key.equals("method"))               return method;
        if (key.equals("data"))                 return data;
        if (key.equals("headerParameters"))     return headerParameters;
        return null;
    }








    /**
     * 传入context 是为了日共日志功能
     * 类原则上可替换
     * 抛出异常还是由业务检查抛出异常？
     * @param context 当前context
     * @return ResponseMessage
     */
    public ResponseMessage execute(Context context) throws IOException {
        String targetUrl   = url.getUrl();
        String requestData = (null != data)?new JSONObject(data).toJSONString():"";

        // 执行 HttpClient request 函数
        try {
            HttpResponse httpResponse = ApacheHttpClient.request(targetUrl, method.toUpperCase(), requestData, headerParameters, timeout);
            int status = httpResponse.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = httpResponse.getEntity();
                String responseBody = (entity != null) ? EntityUtils.toString(entity) : null;
                JSONObject responseBodyJson = JSON.parseObject(responseBody);
                return new ResponseMessage(responseBodyJson.get("data"), responseBodyJson.get("message").toString(), status);
            } else {
                context.getLogger().error("loading http source :" + targetUrl + " error status code:" + status);
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        } catch (Exception ex) {
            // TODO bizness exception
            context.getLogger().error(ex.getMessage());
            throw ex;
        }
    }






































    // getters and setters
    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map getData() {
        return data;
    }

    public void setData(Map data) {
        this.data = data;
    }

    public HashMap<String, String> getHeaderParameters() {
        return headerParameters;
    }

    public void setHeaderParameters(HashMap<String, String> headerParameters) {
        this.headerParameters = headerParameters;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }
}

package com.gomeplus.comx.utils.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gomeplus.comx.context.Context;

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
     * 传入context 是为了日共日志功能， 类原则上可替换
     * @param context 当前context
     * @return
     */
    public ResponseMessage execute(Context context) throws IOException {
        String requestData = null;
        if (null != data) {
            requestData = new JSONObject(data).toJSONString();
        }
        return ApacheClient.request(url, method, requestData, headerParameters, timeout);
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

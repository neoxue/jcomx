package com.gomeplus.comx.utils.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * Created by xue on 12/15/16.
 */
public class RequestMessage implements ArrayAccessBase{
    static final String METHOD_GET      = "get";
    static final String METHOD_POST     = "post";
    static final String METHOD_DELETE   = "delete";
    static final String METHOD_PUT      = "put";
    //static final ArrayList ACCEPTED_METHODS = new ArrayList([METHOD_PUT, METHOD_GET, METHOD_POST, METHOD_DELETE]);
    //static final String[] ACCEPTED_METHODS = ["get", self::METHOD_GET, self::METHOD_POST, self::METHOD_DELETE];
    //static final String ACCEPTED_METHODSs = [self::METHOD_PUT, self::METHOD_GET, self::METHOD_POST, self::METHOD_DELETE];
    static final String HEADER_FIELD_TRACE_ID           = "X-Gomeplus-Trace-Id";
    static final String HEADER_FIELD_X_FORWARDED_FOR    = "X-Forwarded-For";
    static final String QUERY_FIELD_TRACE_ID            = "traceId";
    static final String DEFAULT_TRACE_ID_PREFIX         = "COMX";

    protected HashMap<String, String>   commonParameterHolder;

    protected Url                       url;
    protected String                    method;
    protected JSONObject                data;
    protected HashMap<String, String>   headerParameters;
    protected Integer                   timeout;

    // constructors;
    public RequestMessage() {
    }

    // implements interface
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

    public JSONObject getData() {
        return data;
    }

    public void setData(JSONObject data) {
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

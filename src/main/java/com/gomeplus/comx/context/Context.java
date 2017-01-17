package com.gomeplus.comx.context;

import com.gomeplus.comx.utils.rest.RequestMessage;
import com.gomeplus.comx.utils.rest.ResponseMessage;
import com.gomeplus.comx.schema.Schema;
import com.gomeplus.comx.utils.log.ComxLogger;

import java.util.HashMap;


/**
 * Created by xue on 12/16/16.
 */
public class Context {
    private Schema                  schema;
    private RequestMessage          request;
    private User                    user;
    private ResponseMessage         response;
    private ComxLogger              logger;
    private ContextCache            cache;
    private HashMap<String, Object> localCache = new HashMap<>();
    private Boolean                 localCacheEnabled = false;



    @Deprecated
    private String          urlPrefix;


    private String          traceId;
    // 记录各资源请求
    // 似乎可以是个结构体来记录
    // 或者redis count http count ...?
    private Integer         count = 0;
    // 先不处理
    //private ScriptLoader scriptLoader;















    @Override
    public Context clone() throws CloneNotSupportedException {
        return (Context) super.clone();
    }





    // getter and setter
    public Boolean getLocalCacheEnabled() {
        return localCacheEnabled;
    }

    public void setLocalCacheEnabled(Boolean localCacheEnabled) {
        this.localCacheEnabled = localCacheEnabled;
    }

    public HashMap<String, Object> getLocalCache() {
        return localCache;
    }

    public void setLocalCache(HashMap<String, Object> localCache) {
        this.localCache = localCache;
    }


    public ContextCache getCache() {
        return cache;
    }

    public void setCache(ContextCache cache) {
        this.cache = cache;
    }

    public ResponseMessage getResponse() {
        return response;
    }

    public void setResponse(ResponseMessage response) {
        this.response = response;
    }

    public ComxLogger getLogger() {
        return logger;
    }

    public void setLogger(ComxLogger logger) {
        this.logger = logger;
    }

    @Deprecated
    public String getUrlPrefix() {
        return urlPrefix;
    }

    @Deprecated
    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public RequestMessage getRequest() {
        return request;
    }

    public void setRequest(RequestMessage request) {
        this.request = request;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

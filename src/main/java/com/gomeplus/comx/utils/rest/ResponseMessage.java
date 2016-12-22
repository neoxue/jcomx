package com.gomeplus.comx.utils.rest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by xue on 12/16/16.
 */
// 需要满足自己调用自己
public class ResponseMessage {
    private Object    data;
    private Object    error;
    private JSONArray debug;

    private String message;
    private String code;
    private String dataType;
    private String callback;

    static final String FIELD_DEBUG                 = "debug";
    static final String FIELD_MESSAGE               = "message";
    static final String FIELD_DATA                  = "data";
    static final String FIELD_ERROR                 = "error";
    static final String DEFAULT_HEADER_CONTENT_TYPE = "Content-Type: application/json; charset=UTF-8";
    static final String JSONP_HEADER_CONTENT_TYPE   = "Content-Type: application/javascript; charset=UTF-8";
    static final String HEADER_PROTOCOL_LINE        = "HTTP/1.1";






    // constructors;
    public ResponseMessage(Object data, String message, String code, Object error) {
        this.data       = data;
        this.message    = message;
        this.code       = code;
        this.error      = error;
    }
    public ResponseMessage(Object data, String message, String code) {
        this.data       = data;
        this.message    = message;
        this.code       = code;
    }


    public String send() {
        JSONObject body = new JSONObject();
        body.put(this.FIELD_MESSAGE, this.message);
        body.put(this.FIELD_DATA, this.data);
        return JSONObject.toJSONString(body, SerializerFeature.WriteMapNullValue);
    }



















    // setter and getter
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getError() {
        return error;
    }

    public void setError(Object error) {
        this.error = error;
    }

    public JSONArray getDebug() {
        return debug;
    }

    public void setDebug(JSONArray debug) {
        this.debug = debug;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getCallback() {
        return callback;
    }

    public void setCallback(String callback) {
        this.callback = callback;
    }
}

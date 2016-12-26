package com.gomeplus.comx.utils.rest;

import java.util.HashMap;
import java.net.*;
import java.io.*;


/**
 * Created by xue on 12/19/16.
 * TODO 寻找适合的 url 解析以及请求类
 * TODO 实现map 细节再思考
 * TODO 同时考虑 client, netty and quasar fiber 写法
 * TODO port schema etc.
 * 暂时用手写解析
 */
public class Url implements ArrayAccessBase{
    protected String url;
    protected HashMap parsedData;



    protected String path;
    protected UrlQuery query;

    public Url(String url) throws UrlException{
        this.url = url;
        try {
            URL aURL = new URL(url);
            query = new UrlQuery(aURL.getQuery());
            path = aURL.getPath();
        }catch(Exception ex) {
            throw new UrlException(ex.getMessage());
        }
    }

    // implements interface
    public boolean containsKey(Object key) {
        return true;
    }
    public Object get(Object key) {
        if (key.equals("url"))      return url;
        if (key.equals("query"))    return query;
        return null;
    }













    // getters and setters
    public HashMap getParsedData() {
        return parsedData;
    }

    public void setParsedData(HashMap parsedData) {
        this.parsedData = parsedData;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setQuery(UrlQuery query) {
        this.query = query;
    }

    public UrlQuery getQuery() {
        return query;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

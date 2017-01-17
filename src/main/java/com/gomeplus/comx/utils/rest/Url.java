package com.gomeplus.comx.utils.rest;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import java.util.HashMap;
import java.net.*;
import java.io.*;
import java.util.List;


/**
 * Created by xue on 12/19/16.
 * TODO 寻找适合的 url 解析以及请求类
 * TODO 实现map 细节再思考
 * TODO 同时考虑 client, netty and quasar fiber 写法
 * 暂时用手写解析
 */
public class Url implements ArrayAccessBase{
    private   String    url;
    private   URI       aURI;
    private   UrlQuery  query;


    public Url(String url) throws UrlException{
        this.url = url;
        try {
            aURI = new URI(url);
            List<NameValuePair> list = URLEncodedUtils.parse(aURI, "utf8");
            HashMap<String, String> parsedParameters = new HashMap<>();
            for (NameValuePair pair:list) {
                parsedParameters.put(pair.getName(), pair.getValue());
            }
            //System.out.println(list);
            query = new UrlQuery(aURI.getQuery(), parsedParameters);
        }catch(Exception ex) {
            ex.printStackTrace();
            throw new UrlException("UrlException: new url" + ex.getMessage() + " exception:" + ex.getClass());
        }
    }

    /**
     *  包含
     *  query           return query;
     *  url             return url;
     *  queryString     URI getQuery
     *  path            URI getPath
     *  host            URI getHost
     *  scheme          URI getScheme
     *  port            URI getPort
     *  hash|fragment   兼容（hash）
     *  user
     *  pass
     *  portWithDefaultValue
     */

    // implements interface TODO
    public boolean containsKey(Object key) {
        return true;
    }
    // user & pass 暂时不实现    hash 即 fragment
    public Object get(Object key) {
        switch ((String)key) {
            case "url":             return url;
            case "query":           return query;
            case "queryString":     return aURI.getQuery();
            case "path":            return aURI.getPath();
            case "host":            return aURI.getHost();
            case "scheme":          return aURI.getScheme();
            case "port":            return aURI.getPort();
            case "fragment":        return aURI.getFragment();
            case "hash":            return aURI.getFragment();
            case "userInfo":        return aURI.getUserInfo();
        }
        return null;
    }

    public String getRelatedPath(String urlPrefix) throws UrlException{
        Url prefix = new Url(urlPrefix);
        if (!prefix.get("host").equals(this.get("host"))) {
            throw new UrlException("UrlException: failed to get related path: unmatched host. prefix["+ urlPrefix + "]; URL:[" + getUrl() + "]");
        }
        if (!prefix.getPortWithDefaultValue().equals(this.getPortWithDefaultValue())) {
            throw new UrlException("UrlException: failed to get related path: unmatched port. prefix["+ urlPrefix + "]; URL:[" + getUrl() + "]");
        }

        String sourcePath = (String)this.get("path");
        String prefixPath = (String)prefix.get("path");
        if ("".equals(prefixPath)) {
            return sourcePath;
        }
        if (sourcePath.indexOf(prefixPath) != 0) {
            throw new UrlException("UrlException: fail to get related path. unmatched path: PREFIX[" + urlPrefix + "]; URL[" + url + "]");
        }
        return sourcePath.substring(prefixPath.length());
    }

    /**
     * default port;
     * if defined,          return defined;
     * else if has default, return default;
     * else return -1;
     * @return
     */
    public Integer getPortWithDefaultValue() {
        Integer port = (Integer) get("port");
        if (!port.equals(-1)) return port;
        if (get("scheme").equals("http"))   return 80;
        if (get("scheme").equals("https"))  return 443;
        return -1;
    }



    public UrlQuery getQuery() {
        return query;
    }

    public String getUrl() {
        return url;
    }
    // URL detail
    //public String  getQueryString()  {   return aURI.getQuery();   }
    //public String  getPath()         {   return aURI.getPath();    }
    //public String  getHost()         {   return aURI.getHost();    }
    //public String  getScheme()       {   return aURI.getScheme();  }
    //public Integer getPort()         {   return aURI.getPort();    }
    //public String  getFragment()     {   return aURI.getFragment();}
    //public String  getHash()         {   return aURI.getFragment();}
    //public String  getUserInfo()     {   return aURI.getUserInfo();}
}

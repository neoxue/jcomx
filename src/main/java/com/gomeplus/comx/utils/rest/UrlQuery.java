package com.gomeplus.comx.utils.rest;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.*;

import com.google.common.base.Splitter;

/**
 * Created by xue on 12/19/16.
 * TODO 优化 解析逻辑 暂时实现最简单逻辑
 * TODO UTF-8 bug 修正
 * http://server/action?id=a&id=b | http://server/action?id=a,b
 * multiple values: since the specification has no definition for this situation,
 * use id=a&id=b to do this;
 * and more info: for PHP, it suggests a style: id[]=a&id[]=b;
 */
public class UrlQuery implements ArrayAccessBase{
    private String  queryString;
    private HashMap parsedParameters = new HashMap();

    /**
     * @param queryString
     * @throws UnsupportedEncodingException
     */
    public UrlQuery(String queryString) throws UnsupportedEncodingException {
        this.queryString = queryString;
        //parsedParameters = Splitter.on('&').trimResults().withKeyValueSeparator("=").split(queryString);
        /*
        final Map<String, List<String>> query_pairs = new LinkedHashMap<String, List<String>>();
        final String[] pairs = queryString.split("&");
        for(String pair: pairs) {
            final int idx = pair.indexOf("=");
            final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
            if (!query_pairs.containsKey(key)) {
                query_pairs.put(key, new LinkedList<String>());
            }
            final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
            query_pairs.get(key).add(value);
        }
        */
        final String[] pairs = queryString.split("&");
        for(String pair: pairs) {
            final int idx = pair.indexOf("=");
            final String key = idx > 0 ? URLDecoder.decode(pair.substring(0, idx), "UTF-8") : pair;
            if (!parsedParameters.containsKey(key)) {
                parsedParameters.put(key, new LinkedList<String>());
            }
            final String value = idx > 0 && pair.length() > idx + 1 ? URLDecoder.decode(pair.substring(idx + 1), "UTF-8") : null;
            parsedParameters.put(key, value);
        }
    }

    // implements interface
    public Object get(Object key) {
        if (key.equals("queryString"))      return queryString;
        return parsedParameters.get(key);
    }






}

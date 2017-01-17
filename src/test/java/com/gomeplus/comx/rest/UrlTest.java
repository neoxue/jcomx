package com.gomeplus.comx.rest;

import com.gomeplus.comx.utils.rest.Url;
import com.gomeplus.comx.utils.rest.UrlException;
import com.gomeplus.comx.utils.rest.clients.ApacheHttpClient;
import org.apache.http.HttpResponse;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

/**
 * Created by xue on 1/16/17.
 */
public class UrlTest {
    @Test
    public void testsuccess() throws Exception {
        String urlstr = "https://username:password@a.b.com:8833/p/a/t/h?query=test&中文query=中文测试#fragment";
        //urlstr = "http://a.b.com/p/a/t/h?query=test&中文query=中文测试#fragment";
        Url url = new Url(urlstr);
        assertEquals("not equal", url.get("scheme"), "https");
        assertEquals("not equal", url.get("host"), "a.b.com");
        assertEquals("not equal", url.get("port"), new Integer(8833));
        assertEquals("not equal", url.get("path"), "/p/a/t/h");
        assertEquals("not equal", url.get("fragment"), "fragment");
        assertEquals("not equal", url.get("hash"), "fragment");
        assertEquals("not equal", url.get("queryString"), "query=test&中文query=中文测试");
        assertEquals("not equal", url.get("userInfo"), "username:password");

    }


    @Test
    public void testRelatedPath() throws UrlException, Exception{
        String urlstr = "https://username:password@a.b.com/p/a/t/h?query=test&中文query=中文测试#fragment";
        String prefixUrl = "https://username:password@a.b.com/p/a";
        Url aUrl = new Url(urlstr);
        String testpath = aUrl.getRelatedPath(prefixUrl);
        assertEquals("not equal", testpath, "/t/h");

    }
}

package com.gomeplus.comx.utils.rest.clients;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gomeplus.comx.utils.config.Config;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * Created by xue on 1/11/17.
 */
public class ApacheHttpClientTest {
    @Test
    public void testsuccess() throws IOException{
        String url = "http://localhost:80/";
        HttpResponse httpResponse = ApacheHttpClient.request(url, "GET", "", new HashMap<>(), 10);
        //System.out.println(httpResponse);
        //System.out.println(EntityUtils.toString(httpResponse.getEntity()));
        String nginxdefault = "<!DOCTYPE html>";
        assertEquals("new config data not equal", new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent())).readLine(), nginxdefault);
    }
}

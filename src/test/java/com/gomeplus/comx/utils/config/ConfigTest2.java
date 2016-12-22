package com.gomeplus.comx.utils.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
/**
 * Created by xue on 12/8/16.
 */
public class ConfigTest2 {
    @Test
    public void testConfig() {
        String jsonStr  = "{'test':'testval'}";
        JSONObject jobj = JSON.parseObject(jsonStr);
        Config config   = new Config(jobj);
        Config config2  = new Config(jobj);
        assertEquals("new config data not equal", config.rawData(), config2.rawData());
    }
}

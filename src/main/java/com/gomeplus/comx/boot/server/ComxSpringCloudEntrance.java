package com.gomeplus.comx.boot.server;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xue on 2/3/17.
 */
@RestController
public class ComxSpringCloudEntrance {

    @RequestMapping("/atom")
    public JSONObject main() {
        return new JSONObject();
    }
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Object getUserByRibbon(String id) {
        //return ribbonClient.getUser(id);
        return new JSONObject();
    }

}

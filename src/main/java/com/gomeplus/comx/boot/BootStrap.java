package com.gomeplus.comx.boot;

/**
 * Created by xue on 12/16/16.
 */

import com.gomeplus.comx.context.Context;
import com.gomeplus.comx.context.ContextBuilder;
import com.gomeplus.comx.utils.rest.RequestMessage;
import com.gomeplus.comx.utils.rest.ResponseMessage;
import com.gomeplus.comx.utils.config.Config;

/**
 * 业务无关日志
 * sandbox and meta
 * read comx.conf.json
 * request;
 */
public class BootStrap {
    public static ResponseMessage start(RequestMessage requestMessage) {
        Context context;
        try {
            Config comxConf     = ComxConfLoader.load();
            ContextBuilder cb   = new ContextBuilder(requestMessage);
            cb.setConf(comxConf);
            context = cb.build();
            //TODO debug mode for meta and sandbox

            Handler handler = new Handler();
            handler.handle(context);
        } catch(Exception ex){
            // 只处理初始化错误，业务错误在handler 处理
            // 构建初始环境异常 打印日志，返回服务不可用
            ex.printStackTrace();
            // 是否针对 jsonp 做特殊处理
            return new ResponseMessage(null, ex.getMessage(),500);
        }

        return context.getResponse();
    }
}

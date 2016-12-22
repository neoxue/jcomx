package com.gomeplus.comx.boot;

import com.alibaba.fastjson.JSONObject;
import com.gomeplus.comx.context.Context;
import com.gomeplus.comx.schema.Schema;
import com.gomeplus.comx.schema.SchemaLoader;
import com.gomeplus.comx.schema.datadecor.decors.AbstractDecor;
import com.gomeplus.comx.schema.datadecor.DecorFactory;
import com.gomeplus.comx.schema.datadecor.decors.UnknownDecorTypeException;
import com.gomeplus.comx.utils.config.ConfigException;
import com.gomeplus.comx.utils.rest.Url;

/**
 * Created by xue on 12/19/16.
 */
// 处理流程
public class Handler {
    public void handle(Context context) {
        try {
            // 记录进入日志
            Url url         = context.getRequest().getUrl();
            // 规范约定应当为map形式， 但最终并不强制要求是
            JSONObject data = new JSONObject();
            context.getLogger().log("in url" + url.getUrl(), "info");

            Schema schema   = SchemaLoader.load(ComxConfLoader.COMX_HOME + "apis/v2/ext/social/group", "get");
            context.setSchema(schema);
            // TODO 处理登录验证
            AbstractDecor rootdecor = DecorFactory.create(schema.getConf());
            rootdecor.decorate(data, context);
            context.getResponse().setData(data);
        } catch (ConfigException ex) {
            ex.printStackTrace();
            context.getLogger().error(ex.getMessage());
        } catch (UnknownDecorTypeException ex){
            ex.printStackTrace();
            context.getLogger().error(ex.getMessage());
        }
    }
}

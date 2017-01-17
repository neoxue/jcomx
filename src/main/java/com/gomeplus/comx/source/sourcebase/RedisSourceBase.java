package com.gomeplus.comx.source.sourcebase;

import com.gomeplus.comx.context.Context;
import com.gomeplus.comx.utils.config.Config;

import java.util.HashMap;

/**
 * Created by xue on 12/23/16.
 * 仅仅是资源
 * 只按最新写法
 */
// TODO
public class RedisSourceBase extends AbstractSourceBase{
    public RedisSourceBase(Config conf) {
        super(conf);
    }
    public Object executeLoading(Context context, Config sourceOptions, HashMap<String, Object> reservedVariables){
        return new Object();
    }

}

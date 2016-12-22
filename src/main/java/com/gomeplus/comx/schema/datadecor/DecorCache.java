package com.gomeplus.comx.schema.datadecor;

import com.gomeplus.comx.context.Context;
import com.gomeplus.comx.schema.TinyTemplate;
import com.gomeplus.comx.utils.config.Config;
import com.gomeplus.comx.utils.config.ConfigException;

import java.util.HashMap;

/**
 * Created by xue on 12/20/16.
 */
public class DecorCache {
    public static final String FIELD_KEY = "key";
    public static final String FIELD_TTL = "ttl";
    public static final String FIELD_WITH_CHILDREN  = "withChildren";
    public static final String FIELD_IS_GLOBAL      = "isGlobal";
    public static final String KEY_PREFIX           = "Decor:";

    protected String    key;
    protected Integer   ttlMs;
    protected boolean   withChildren;
    protected boolean   isGlobal =false;

    protected DecorCache (String key, Integer ttlMs, boolean withChildren, boolean isGlobal) {
        this.isGlobal = isGlobal;
        this.key = key;
        this.ttlMs = ttlMs;
        this.withChildren = withChildren;
    }
    protected DecorCache (String key, Integer ttlMs, boolean withChildren) {
        this(key, ttlMs, withChildren, false);
    }

    public static DecorCache fromConf(Config config, Context context, Object data) throws ConfigException{
        if(config.rawData().isEmpty()) {
            return new NullDecorCache();
        }
        // TODO 是否需要rstr
        TinyTemplate keyTpl = new TinyTemplate(config.rstr(DecorCache.FIELD_KEY));
        // TODO 添加 global 分支
        String prefix = context.getRequest().getUrl().getPath() + ":";
        HashMap vars = new HashMap();
        vars.put("data", data);
        vars.put("request", context.getRequest());
        String key =  prefix + keyTpl.render(vars, context);

        return new DecorCache(key, config.rintvalue(DecorCache.FIELD_TTL), config.bool(DecorCache.FIELD_WITH_CHILDREN, false));
    }
}

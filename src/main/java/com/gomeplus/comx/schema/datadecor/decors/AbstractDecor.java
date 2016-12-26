package com.gomeplus.comx.schema.datadecor.decors;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gomeplus.comx.context.Context;
import com.gomeplus.comx.schema.ConfBaseNode;
import com.gomeplus.comx.schema.datadecor.DecorCache;
import com.gomeplus.comx.schema.datadecor.DecorFactory;
import com.gomeplus.comx.utils.config.Config;
import com.gomeplus.comx.utils.config.ConfigException;

import java.util.Set;

/**
 * Created by xue on 12/19/16.
 */
public abstract class AbstractDecor extends ConfBaseNode{
    public static final String FIELD_DECORS                = "decors";
    public static final String FIELD_CACHE                 = "cache";
    public static final String FIELD_PRECONDITION          = "precondition";
    public static final String FIELD_LOCAL_CACHE_ENABLED   = "localCacheEnabled";
    // localCache 未启用

    public static final String FIELD_TYPE          = "type";
    public static final String[] ACCEPTED_TYPES = {
            AbstractDecor.TYPE_ROOT,
            AbstractDecor.TYPE_SCRIPT,
            AbstractDecor.TYPE_COMPOSITION,
            AbstractDecor.TYPE_EACH,
            AbstractDecor.TYPE_BATCH,
            AbstractDecor.TYPE_FIXED,
    };
    public static final String TYPE_COMPOSITION    = "Composition";
    public static final String TYPE_FIXED          = "Fixed";
    public static final String TYPE_ROOT           = "Root";
    public static final String TYPE_BATCH          = "Batch";
    public static final String TYPE_EACH           = "Each";
    public static final String TYPE_SCRIPT         = "Script";

    public AbstractDecor(Config conf){
        super(conf);
    }
    abstract public void doDecorate(Object data, Context context) throws ConfigException, Exception;
    abstract public String getType();






    public void decorate(Object data, Context context){
        // TODO localcache enable

        // TODO 记录更多decor conf 信息
        context.getLogger().debug("Execute Decor:"+ this.getType());
        try {
            // TODO before decorate : precondition node

            DecorCache decorCache = DecorCache.fromConf(conf.sub(AbstractDecor.FIELD_CACHE), context, data);
            // TODO 处理 decorCache withChildren & withOutChildren

            this.doDecorate(data, context);
            // TODO decorCache set before children;
            this.executeChildDecors(data, context);
            // TODO decorCache set after children;
        } catch(Exception ex) {
            context.getLogger().error("decorate error:" + ex.getMessage());
            ex.printStackTrace();
            // TODO onError 流程
            // TODO 对于ConfigException 需要直接抛出？
            // TODO UnknownDecorTypeException
        }

    }
    // precondition node; 是否在beforeDecorate 考虑
    public void beforeDecorate(Object data, Context context){
    }








    // TODO 写法变更
    public void executeChildDecors(Object data, Context context) throws ConfigException,UnknownDecorTypeException{
        this.sequentialExecuteChildDecors(data, context);
    }
    public void sequentialExecuteChildDecors(Object data, Context context) throws ConfigException,UnknownDecorTypeException{
        Config children = conf.sub(AbstractDecor.FIELD_DECORS);
        Set<String> keys = children.keys();
        for(String key: keys){
            Config conf = children.sub(key);
            AbstractDecor decor = DecorFactory.create(conf);
            decor.decorate(data, context);
        }
    }
}







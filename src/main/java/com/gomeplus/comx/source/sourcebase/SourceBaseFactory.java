package com.gomeplus.comx.source.sourcebase;

import com.alibaba.fastjson.JSONObject;
import com.gomeplus.comx.source.Source;
import com.gomeplus.comx.utils.config.Config;
import com.gomeplus.comx.utils.config.ConfigException;

import java.util.HashMap;

/**
 * Created by xue on 12/23/16.
 * ComxConfLoader 当中
 *
 * 实现 http/redis/inner
 * 还应当实现 dubbo, protobuf
 */
public class SourceBaseFactory {
    private static final String DEFAULT_BASE_ID         = "default";
    private static final String FIELD_ATOMIC_URL_PREFIX = "atomicUrlPrefix";
    private static final String FIELD_SOURCE_BASES      = "sourceBases";
    private static final String SELF_BASE_ID            = "self";

    private static final String FIELD_TYPE              = "type";
    private static final String TYPE_HTTP               = "http";
    private static final String TYPE_REDIS              = "redis";

    private static final String DEFAULT_TYPE            = "http";

    private static HashMap<String, AbstractSourceBase> pool = new HashMap<String, AbstractSourceBase>();

    public static SourceBaseFactory fromConf(Config conf) throws ConfigException, UnknownSourceBaseTypeException {
        // sourceBaseFactory 实例
        SourceBaseFactory factory       = new SourceBaseFactory();

        // sourceBaseFactory 添加默认原子 base
        String defaultAtomicUrlPrefix   = conf.str(FIELD_ATOMIC_URL_PREFIX, "");
        if (null != defaultAtomicUrlPrefix){
            HashMap<String, Object> simplehashmap = new HashMap<>();
            simplehashmap.put(HttpSourceBase.FIELD_ID,          DEFAULT_BASE_ID);
            simplehashmap.put(HttpSourceBase.FIELD_URL_PREFIX,  DEFAULT_BASE_ID);

            JSONObject simplejson = new JSONObject(simplehashmap);
            HttpSourceBase httpSourceBase = new HttpSourceBase(new Config(simplejson));
            factory.putSourceBase(httpSourceBase);
        }

        // other bases;
        Config bases = conf.sub(FIELD_SOURCE_BASES);
        for (String i: bases.keys()) {
            Config aconf = bases.sub(i);
            factory.putSourceBase(populateBaseObject(aconf));
        }
        return factory;
    }

    protected static AbstractSourceBase populateBaseObject(Config conf) throws UnknownSourceBaseTypeException{
        String type = conf.str(FIELD_TYPE, DEFAULT_TYPE);
        switch (type) {
            case TYPE_HTTP:  return new HttpSourceBase(conf);
            // TODO 添加其他source base
            case TYPE_REDIS: return new RedisSourceBase(conf);
            default:         throw new UnknownSourceBaseTypeException("unkown source base type:"+ type);
        }
    }

    public void putSourceBase(AbstractSourceBase sourceBase) throws ConfigException{
        if (sourceBase.getId().equals(SELF_BASE_ID)) {
            throw new ConfigException("self is A RESERVED base ID");
        }
        pool.put(sourceBase.getId(), sourceBase);
    }

    //TODO 添加兼容或者抛出异常
    public static AbstractSourceBase getSourceBase(String id) {
        return pool.get(id);
    }




















}

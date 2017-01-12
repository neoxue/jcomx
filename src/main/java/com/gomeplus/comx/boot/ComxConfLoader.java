package com.gomeplus.comx.boot;

import com.gomeplus.comx.source.sourcebase.SourceBaseFactory;
import com.gomeplus.comx.source.sourcebase.UnknownSourceBaseTypeException;
import com.gomeplus.comx.utils.cache.AbstractCache;
import com.gomeplus.comx.utils.cache.CacheFactory;
import com.gomeplus.comx.utils.cache.JedisClusterCache;
import com.gomeplus.comx.utils.config.Config;
import com.gomeplus.comx.utils.config.ConfigException;

/**
 * Created by xue on 12/16/16.
 */
public class ComxConfLoader {
    private static boolean initialized = false;
    private static String COMX_HOME;
    private static Config comxConf;
    private static SourceBaseFactory sourceBaseFactory;
    private static AbstractCache cache;



    // synchronized TODO
    // 提供更新方法 TODO
    public static Config load()  throws ConfigException, UnknownSourceBaseTypeException{
        if (!initialized) {
            initialize();
        }
        return comxConf;
    }

    public static void initialize()  throws ConfigException, UnknownSourceBaseTypeException{
        COMX_HOME = "/www/comx-conf";
        String comxConfFile = COMX_HOME + "/comx.conf.json";
        comxConf            = com.gomeplus.comx.utils.config.Loader.fromJsonFile(comxConfFile);
        sourceBaseFactory   = SourceBaseFactory.fromConf(comxConf);
        Config cacheConf    = comxConf.sub("cache");
        cache               = CacheFactory.fromConf(cacheConf);
        initialized         = true;
    }








    public static AbstractCache getCache() {return cache;}

    public static String getComxHome() {
        return COMX_HOME;
    }

    public static Config getComxConf() {
        return comxConf;
    }

    public static SourceBaseFactory getSourceBaseFactory() {
        return sourceBaseFactory;
    }
}

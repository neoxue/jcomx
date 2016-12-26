package com.gomeplus.comx.boot;

import com.gomeplus.comx.source.sourcebase.SourceBaseFactory;
import com.gomeplus.comx.source.sourcebase.UnknownSourceBaseTypeException;
import com.gomeplus.comx.utils.config.Config;
import com.gomeplus.comx.utils.config.ConfigException;

/**
 * Created by xue on 12/16/16.
 */
public class ComxConfLoader {
    public static boolean initialized = false;
    public static String COMX_HOME;
    public static Config comxConf;
    public static SourceBaseFactory sourceBaseFactory;



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
        initialized         = true;
    }
}

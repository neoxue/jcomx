package com.gomeplus.comx.boot;

import com.gomeplus.comx.utils.config.Config;
import com.gomeplus.comx.utils.config.ConfigException;

/**
 * Created by xue on 12/16/16.
 */
public class ComxConfLoader {
    public static Integer initialized;
    public static String COMX_HOME;
    public static Config comxConf;




    public static Config load()  throws ConfigException{
        if (initialized.equals(1)) {
            initialize();
        }
        return comxConf;
    }

    public static void initialize()  throws ConfigException{
        COMX_HOME = "/www/comx-conf";
        String comxConfFile = COMX_HOME + "/comx.conf.json";
        comxConf            = com.gomeplus.comx.utils.config.Loader.fromJsonFile(comxConfFile);
        initialized         = 1;
    }
}

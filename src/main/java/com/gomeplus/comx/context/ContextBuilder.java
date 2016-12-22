package com.gomeplus.comx.context;

import com.gomeplus.comx.utils.rest.RequestMessage;
import com.gomeplus.comx.utils.config.Config;
import com.gomeplus.comx.utils.log.ComxLogger;


/**
 * Created by xue on 12/15/16.
 */
public class ContextBuilder {
    protected static Config conf;
    protected static final String home = "/www/comx-conf/";
    protected RequestMessage request;

    public ContextBuilder(RequestMessage request) {
        this.request = request;
    }





    /**
     * @return Context context
     */
    // TODO cache init;
    // TODO traceId;
    public Context build () {
        Context     context = new Context();
        User        user    = new User(request);
        ComxLogger  logger  = new ComxLogger();


        context.setLogger(logger);
        context.setRequest(request);
        context.setUser(user);

        // TODO jsonp
        return context;
    }























    public static Config getConf() {
        return conf;
    }

    public static void setConf(Config conf) {
        ContextBuilder.conf = conf;
    }
}

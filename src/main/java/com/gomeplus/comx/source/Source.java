package com.gomeplus.comx.source;

import com.alibaba.fastjson.JSONObject;
import com.gomeplus.comx.context.Context;
import com.gomeplus.comx.schema.TinyTemplate;
import com.gomeplus.comx.source.sourcebase.AbstractSourceBase;
import com.gomeplus.comx.source.sourcebase.SourceBaseFactory;
import com.gomeplus.comx.source.sourcebase.UnmatchedRequestMethodException;
import com.gomeplus.comx.utils.config.Config;
import com.gomeplus.comx.utils.config.ConfigException;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by xue on 12/22/16.
 */
public class Source {
    static final Integer DEFAULT_TIMEOUT            = 8000;
    static final String FIELD_TIMEOUT               = "timeout";
    static final String FIELD_BASE                  = "base";
    static final String FIELD_CACHE                 = "cache";
    static final String RESERVED_TPL_VAR_REQUEST    = "request";
    static final String FIELD_FIRST_ENTRY_ONLY      = "firstEntryOnly";
    static final String FIELD_JSON_PATH             = "jsonPath";
    static final String FIELD_ON_ERROR              = "onError";
    static final String FIELD_BACKUP                = "backup";

    public static final String FIELD_URI            = "uri";
    public static final String FIELD_METHOD         = "method";

    Config conf;
    public Source(Config conf){
        this.conf = conf;
    }

    /**
     * loadData
     * @param context
     * @param reservedVariables
     * @return
     */
    public Object loadData(Context context, HashMap<String, Object> reservedVariables) throws ConfigException, Exception{
        // since redis operation, choose nanotime; 1E-9
        long time0      = System.nanoTime();
        Object result   = null;
        Exception ex    = null;
        try {
            result = this.doLoadData(context, reservedVariables);
        } catch (Exception ex0){
            context.getLogger().error("source loading error, ex:" + ex0.getMessage() +  " URI:" + this.getUri());
            ex = ex0;
        }
        double deltaTime = (System.nanoTime() - time0) * 1.0 / 1000_000_000;
        context.getLogger().debug("source loading used:" + deltaTime + " sec, URI:" + this.getUri());

        if (result != null) return result;
        Config backupConf = conf.sub(Source.FIELD_BACKUP);
        if (backupConf.rawData().isEmpty()) {
            context.getLogger().error("source loading error, null return and backupConf empty; URI:" + this.getUri());
            if (ex != null)     throw ex;
            return null;
        }
        Source backSource = new Source(backupConf);
        return backSource.loadData(context, reservedVariables);
    }


    public Object doLoadData(Context context, HashMap<String, Object> reservedVariables) throws ConfigException, IOException, UnmatchedRequestMethodException{
        try {
            HashMap tplParams = (HashMap) reservedVariables.clone();
            tplParams.put(Source.RESERVED_TPL_VAR_REQUEST, context.getRequest());

            String uri = getUri();
            Config cacheConf = conf.sub(Source.FIELD_CACHE);
            TinyTemplate tpl = new TinyTemplate(uri);
            String renderedUri = tpl.render(tplParams, context);

            // localcache 部分 TODO
            // SourceCache 部分 (主分支) TODO

            Object data = this.getBase(context).executeLoading(context, conf, tplParams);
            // cache sets

            String jsonPath = conf.str(FIELD_JSON_PATH, "");
            if (jsonPath.isEmpty()) {
                return data;
            }
            return extractDataWithJsonPath(data);
        } catch (Exception ex) {
            context.getLogger().error(ex.getMessage());
            // TODO strategy on error;
            return new JSONObject();
        }

    }

    public String getUri() throws ConfigException{
        return conf.rstr(Source.FIELD_URI);
    }


    public AbstractSourceBase getBase(Context context) throws ConfigException{
        String baseId = conf.rstr(Source.FIELD_BASE);
        return SourceBaseFactory.getSourceBase(baseId);
    }

    // TODO 逻辑补写
    public Object extractDataWithJsonPath(Object data) {
        return data;
    }







}

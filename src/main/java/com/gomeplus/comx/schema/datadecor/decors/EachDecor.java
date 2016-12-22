package com.gomeplus.comx.schema.datadecor.decors;

import com.alibaba.fastjson.JSONObject;
import com.gomeplus.comx.context.Context;
import com.gomeplus.comx.source.Source;
import com.gomeplus.comx.utils.config.Config;
import com.gomeplus.comx.utils.config.ConfigException;

import java.util.ArrayList;

/**
 * Created by xue on 12/20/16.
 */
public class EachDecor extends AbstractDecor {
    public static final String FIELD_SOURCE         = "source";
    public static final String FIELD_FIELD          = "field";
    public static final String FIELD_REF_JSON_PATH  = "refJsonPath";

    protected Source source;
    protected String field;

    public EachDecor(Config conf){
        super(conf);
    }
    public String getType(){ return AbstractDecor.TYPE_EACH;}



    public void doDecorate(Object data, Context context) throws ConfigException{
        Object[] MatchedNodes = this.getMatchedNodes(data);
        source = new Source(conf.rsub(EachDecor.FIELD_FIELD));
        field  = conf.rstr(EachDecor.FIELD_FIELD);

        context.getLogger().debug("decor eachdecor, input data:"+ data.toString());
        //


    }

    // TODO refjson
    //protected Object[] getMatchedNodes(Object data){
    //    String refJsonPath = conf.str(EachDecor.FIELD_REF_JSON_PATH, null);
    //    if (null == refJsonPath) {  return new ArrayList({data});}
    //}
}
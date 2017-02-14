package com.gomeplus.comx.schema.datadecor.decors;

import com.alibaba.fastjson.JSONPath;
import com.gomeplus.comx.context.Context;
import com.gomeplus.comx.utils.config.Config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xue on 2/2/17.
 * TODO 验证逻辑 以及 warning
 */
public interface RefJsonPath {
    String FIELD_REF_JSON_PATH  = "refJsonPath";
    default List<Object> getMatchedNodes(Config conf, Object data, Context context) {
        String refJsonPath = conf.str(FIELD_REF_JSON_PATH, null);
        if (null == refJsonPath) {
            return new ArrayList(Arrays.asList(data));
        }
        try {
            Object matchedNode = JSONPath.eval(data, refJsonPath);
            if (matchedNode instanceof List) {
                context.getLogger().error("RefJsonPaht: matched list:" + matchedNode.toString());
                return (List)matchedNode;
            } else {
                context.getLogger().error("RefJsonPaht: is not list:" + matchedNode.toString());
                return Arrays.asList(matchedNode);
            }
        } catch(Exception ex){
            context.getLogger().error("Decor Eachdecor, refJsonPath error:" + ex.getMessage() + " refJsonPath:"+ refJsonPath+ ", data:" + data);
            return new ArrayList(Arrays.asList(data));
        }
    }
}

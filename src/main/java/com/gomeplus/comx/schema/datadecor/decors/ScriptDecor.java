package com.gomeplus.comx.schema.datadecor.decors;

import com.alibaba.fastjson.JSONPath;
import com.gomeplus.comx.context.Context;
import com.gomeplus.comx.schema.datadecor.DecorException;
import com.gomeplus.comx.utils.config.Config;
import groovy.lang.GroovyObject;
import groovy.util.GroovyScriptEngine;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by xue on 1/17/17.
 * TODO 还没写完。。
 */
public class ScriptDecor extends AbstractDecor{
    public ScriptDecor(Config conf) {
        super(conf);
    }
    public String getType() {
        return AbstractDecor.TYPE_SCRIPT;
    }

    //该变量用于指明groovy脚本所在的父目录
    static String root[] = new String[]{"/www/comx-conf/groovy-scripts/"};
    static GroovyScriptEngine groovyScriptEngine;

    static {
        try {
            groovyScriptEngine = new GroovyScriptEngine(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
// TODO 暂时只支持 script, lambda 再想办法
    public void doDecorate(Object data, Context context) throws DecorException{
        context.getLogger().error("Decor ScriptDecor: none:" + conf.rawData());
        ArrayList matchedNodes = this.getMatchedNodes(data, context);

        try {
            String scriptName = conf.rstr("script");
            Class scriptClass = groovyScriptEngine.loadScriptByName(scriptName);
            GroovyObject scriptInstance = (GroovyObject) scriptClass.newInstance();
            for (Object ref: matchedNodes) {
                Object params = new Object[] {data, context, ref};
                scriptInstance.invokeMethod("callback", params);
            }
        } catch (Exception ex) {
            // 有以下错误
            //(ResourceException | ScriptException | InstantiationException | IllegalAccessException e1)
            throw new DecorException(ex);
        }
    }



    /**
     * 记录日志需要
     * TODO 和eachdecor 一致， 需要抽象出来
     * TODO 需要验证 refjsonpath 效果一致
     * @param data
     * @param context 记录日志
     * @return ArrayList
     */
    protected ArrayList getMatchedNodes(Object data, Context context){
        String refJsonPath = conf.str(EachDecor.FIELD_REF_JSON_PATH, null);
        if (null == refJsonPath) {
            return new ArrayList(Arrays.asList(data));
        }
        try {
            Object matchedNode = JSONPath.eval(data, refJsonPath);
            if (matchedNode instanceof ArrayList) {
                return (ArrayList) matchedNode;
            } else {
                return new ArrayList(Arrays.asList(matchedNode));
            }
        } catch(Exception ex){
            context.getLogger().warn("Decor Eachdecor, refJsonPath error, refJsonPath:"+ refJsonPath+ ", data:" + data);
            return new ArrayList(Arrays.asList(data));
        }
    }

}

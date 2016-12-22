package com.gomeplus.comx.utils.config;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by xue on 12/6/16.
 * 需要分辨 dataObject or dataArray
 *
 */
public class Config {
    protected JSONObject dataObject;
    //protected JSONArray  dataArray;
    protected HashMap<String, Config> subs;

    public Config(JSONObject dataObject)      {this.dataObject = dataObject;}
    //public Config(JSONArray dataArray)  {this.dataArray = dataArray;}







    /**
     * TODO str & rstr 修正逻辑
     * @param key
     * @return String
     */
    public String str(String key, String defaultValue){
        if (!(dataObject.containsKey(key))) return defaultValue;
        return dataObject.get(key).toString();
    }
    public String rstr(String key) throws ConfigException{
        String rvalue = "";
        if (!dataObject.containsKey(key)) {
            rvalue = this.str(key, "");
        }
        if (rvalue.isEmpty()) {
            throw new ConfigException("get rstr failed. key:"+ key + " config.dataObject:"+ dataObject);
        }
        return rvalue;
    }




    //public boolean bool(String key) {
    //    return bool(key, false);
    //}
    public boolean bool(String key, boolean defaultValue) throws ConfigException{
        if (!dataObject.containsKey(key)) return defaultValue;
        Object value = dataObject.get(key);
        if (value instanceof Boolean) { return (Boolean)value;}
        if (value instanceof Integer) { return !(value.equals(0));}
        if (value instanceof String)  {
            if (value.equals("false")) return false;
            else return true;
        }
        throw new ConfigException("get boolean type error. key:" + key + " config.dataObject:" + dataObject);
    }

    public Integer intvalue(String key, Integer defaultValue) throws ConfigException{
        if (!dataObject.containsKey(key)) return defaultValue;
        Object value = dataObject.get(key);
        if (value instanceof Boolean) { return (Integer)value;}
        if (value instanceof Integer) { return (Integer)value;}
        if (value instanceof String)  {
            return Integer.parseInt((String)value);
        }
        throw new ConfigException("get Integer type error. key:" + key + " config.dataObject:" + dataObject);
    }

    public Integer rintvalue(String key) throws ConfigException{
        Integer value = this.intvalue(key, null);
        if (value == null) throw new ConfigException("get Integer type error. key:" + key + "config.dataObject:" + dataObject);
        return value;
    }












    public Set<String> keys() {
        return dataObject.keySet();
    }






    public Config rsub(String key) throws ConfigException{
        if (!subs.containsKey(key)) subs.put(key, this.genSub(key, true));
        return subs.get(key);
    }
    public Config sub(String key) throws ConfigException{
        if (!subs.containsKey(key)) subs.put(key, this.genSub(key, false));
        return subs.get(key);
    }

    public Config genSub(String key, boolean restrict)  throws ConfigException{
        if (!(dataObject.containsKey(key))) {
            if (restrict) {
                throw new ConfigException("sub node does not exist. FIELD[" + key + "]");
            }
        }
        Object arr = dataObject.get(key);
        if (arr instanceof JSONObject){
            return new Config((JSONObject) arr);
        }
        if (arr instanceof JSONArray){
            // 将array 转化为 map
            Object[] s = ((JSONArray) arr).toArray();
            JSONObject tdataObject = new JSONObject();
            for (Integer index = 0; index < s.length; index++){
                tdataObject.put(index.toString(), s[index]);
            }
            return new Config(tdataObject);
        }
        throw new ConfigException("type error, expects array or object. FIELD[" + key + "]");
    }








    public JSONObject rawData() {return this.dataObject;}
}

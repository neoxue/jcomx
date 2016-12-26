package com.gomeplus.comx.utils.rest;

import java.util.HashMap;

/**
 * Created by xue on 12/23/16.
 * 部分请求参数保留到调用接口的类
 */
public class ReservedParameterManager {

    public static final HashMap<String, String> RESERVED_PARAMETERS = new HashMap<String, String>(){
        {
            put("loginToken",   "X-Gomeplus-Login-Token"    );
            put("userId",       "X-Gomeplus-User-Id"        );
            put("accessToken",  "X-Gomeplus-Access-Token"   );
            put("device",       "X-Gomeplus-Device"         );
            put("app" ,         "X-Gomeplus-App"            );
            put("net",          "X-Gomeplus-Net"            );
            put("accept",       "Accept"                    );
            put("traceId",      "X-Gomeplus-Trace-Id"       );
            put("appVersion",   "X-Gomeplus-App-Version"    );
        }
    };
    public static final HashMap<String, String> RESERVED_TO_QUERY_PARAMERTERS = new HashMap<String, String>(){
        {
            put("uniqueDeviceId",  "X-Gomeplus-Unique-Device-Id");

        }
    };




    protected HashMap<String, String> reservedQueryParams;
    protected HashMap<String, String> reservedHeaderParams;

    public static ReservedParameterManager fromRequest(RequestMessage request) {
        HashMap<String, String> headers = request.getHeaderParameters();
        HashMap<String, String> queries = request.getUrl().getQuery().getParameters();

        HashMap<String, String> reservedHeaderParams = new HashMap<>();
        HashMap<String, String> reservedQueryParams  = new HashMap<>();

        for (String queryName: RESERVED_PARAMETERS.keySet()) {
            String headerName = RESERVED_PARAMETERS.get(queryName);
            if (headers.containsKey(headerName)) {
                reservedHeaderParams.put(queryName, headers.get(headerName));
            }
            if (queries.containsKey(queryName)) {
                reservedQueryParams.put(queryName, queries.get(queryName));
            }
        }
        for (String queryName: RESERVED_TO_QUERY_PARAMERTERS.keySet()) {
            String headerName = RESERVED_PARAMETERS.get(queryName);
            if (headers.containsKey(headerName)) {
                reservedQueryParams.put(queryName, queries.get(headerName));
            }
            if (queries.containsKey(queryName)) {
                reservedQueryParams.put(queryName, queries.get(queryName));
            }
        }
        return new ReservedParameterManager(reservedQueryParams, reservedHeaderParams);
    }

    public ReservedParameterManager(HashMap<String, String> reservedQueryParams, HashMap<String, String> reservedHeaderParams) {
        this.reservedHeaderParams = reservedHeaderParams;
        this.reservedQueryParams  = reservedQueryParams;
    }

    public HashMap<String, String> getReservedQueryParams(){
        return reservedQueryParams;
    }

    // TODO 初始化之时直接将query和heade中的冲突参数merge，以query为主
    public HashMap<String, String> getFilteredReservedHeaders() {
        HashMap<String, String> result = new HashMap<>();
        for (String queryName: reservedHeaderParams.keySet()) {
            if (reservedQueryParams.containsKey(queryName)){
                continue;
            }
            result.put(RESERVED_PARAMETERS.get(queryName), reservedHeaderParams.get(queryName));
        }
        return result;
    }
}

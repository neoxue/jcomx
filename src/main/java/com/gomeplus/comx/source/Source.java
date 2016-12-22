package com.gomeplus.comx.source;

import com.gomeplus.comx.utils.config.Config;

/**
 * Created by xue on 12/22/16.
 */
public class Source {
    static final Integer DEFAULT_TIMEOUT        = 8000;
    static final String FIELD_TIMEOUT           = "timeout";
    static final String FIELD_BASE              = "base";
    static final String FIELD_URI               = "uri";
    static final String FIELD_CACHE             = "cache";
    static final String RESERVED_TPL_VAR_REQUEST    = "request";
    static final String FIELD_FIRST_ENTRY_ONLY      = "firstEntryOnly";
    static final String FIELD_METHOD                = "method";
    static final String FIELD_JSON_PATH             = "jsonPath";
    static final String FIELD_ON_ERROR              = "onError";
    static final String FIELD_BACKUP                = "backup";

    Config conf;
    public Source(Config conf){
        this.conf = conf;
    }
}

package com.gomeplus.comx.utils.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by xue on 12/13/16.
 */

/**
 *  trace debug info warning error
 *    1    2     2     2      2     dev
 *    1    2     2     2      2     test
 *                                  integrated
 *    0    1     1     1      1     pre
 *               1     1      1     pro
 *
 * 0, 不记录
 * 1, 代表记录本地日志
 * 2, 代表输出到页面debug信息
 *
 * 将debug 信息输出到页面
 * 另 可以指定参数输出所有信息  TODO
 */
public class ComxLogger {
    private static Logger logger = LoggerFactory.getLogger(ComxLogger.class);
    private String traceId = "";
    // TODO 实现。。
    public void trace(String str)   { logger.trace(str);}
    public void debug(String str)   { logger.debug(str);}
    public void info(String str)    { logger.info(str);}
    public void warn(String str)    { logger.warn(str);}
    public void error(String str)   { logger.error(str);}

    public void trace(Throwable throwable) {
        throwable.printStackTrace();
    }
}

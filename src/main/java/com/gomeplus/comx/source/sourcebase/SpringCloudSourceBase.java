package com.gomeplus.comx.source.sourcebase;

import com.alibaba.fastjson.JSONObject;
import com.gomeplus.comx.context.Context;
import com.gomeplus.comx.source.SourceException;
import com.gomeplus.comx.utils.config.Config;
import com.gomeplus.comx.utils.config.ConfigException;
import com.gomeplus.comx.utils.rest.RequestMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xue on 2/6/17.
 *
 * TODO 应当是一个插件，暂时这么实现
 * 不配置不应当影响正常服务
 * Spring 在启动时加载？
 * 因为SERVER_ID 不是一个域名，使用通用的方式解析会失败，故自己拼装
 */
public class SpringCloudSourceBase extends AbstractRequestBasedSourceBase{
    /*
    @Autowired
    private RestTemplate restTemplate;
    */

    static final String FIELD_URL_PREFIX = "urlPrefix";
    public String getUrlPrefix(Context context) throws ConfigException {
        return conf.rstr(FIELD_URL_PREFIX);
    }
    public SpringCloudSourceBase(Config conf){super(conf);}





    public Object doRequest(RequestMessage requestMessage, Context context) {
        /*
        String method = requestMessage.getMethod();
        // Prepare header
        HttpHeaders headers = new HttpHeaders();
        //headers.setAccept(acceptableMediaTypes);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        Map data = requestMessage.getData();
        context.getLogger().debug("resttemplate1");
        context.getLogger().debug("resttemplate" + restTemplate.toString());
        return restTemplate.getForEntity("http://config-server/user-service-dev.json", Object.class).getBody();

*/
        return "";
        //return restTemplate.exchange(requestMessage.getUrl().getaURI(), HttpMethod.GET, entity, Object.class).getBody();
    }
}

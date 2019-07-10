package com.ls.zd.remote.apache;

import com.ls.zd.datas.AppRequest;
import com.ls.zd.redis.RedisHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import utils.JsonBinder;

/**
 * Created by danzhang on 2019/7/9.
 */

@Component
public abstract class BaseClient {
    Logger logger = LoggerFactory.getLogger(BaseClient.class);

    @Autowired
    RestTemplate restTemplate;


    JsonBinder jsonBinder = JsonBinder.buildNormalBinder();


    public RedisHelper getRedisHelper() {
        return redisHelper;
    }


    @Autowired
    private RedisHelper redisHelper;


    private String userUrl;


    public String buildUserUrl(AppRequest request) {
        userUrl = redisHelper.hget("baseUrl", "order").toString();
        return userUrl + "api" + "/" + "v1/" + request.getReqsn() + "/" + request.getBizCode();
    }

}

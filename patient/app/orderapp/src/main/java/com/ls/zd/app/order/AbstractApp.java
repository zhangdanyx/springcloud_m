package com.ls.zd.app.order;

import com.ls.zd.redis.RedisHelper;
import org.springframework.beans.factory.annotation.Autowired;
import utils.JsonBinder;
import com.ls.zd.command.Accessable;

/**
 * Created by danzhang on 2019/7/5.
 */
public abstract class AbstractApp implements Accessable {
    public JsonBinder jsonBinder = JsonBinder.buildNormalBinder();

    @Autowired
    RedisHelper redisHelper;
}

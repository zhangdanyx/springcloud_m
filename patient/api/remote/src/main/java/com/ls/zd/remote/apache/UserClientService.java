package com.ls.zd.remote.apache;

import com.ls.zd.beans.Order1001Data;
import com.ls.zd.datas.AppRequest;
import com.ls.zd.datas.AppResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by danzhang on 2019/7/9.
 */

@Component
public class UserClientService extends BaseClient {


    Logger log = LoggerFactory.getLogger(UserClientService.class);


    public <T> T getUserAppData(AppRequest request,Class<T> c) {
        String url = buildUserUrl(request);
        log.info("请求地址:" + url);
        AppResponse response = restTemplate.postForObject(
                url,
                Order1001Data.builder()
                        .orderName("234234")
                        .orderTotal("222")
                        .build(),
                AppResponse.class);


        return jsonBinder.fromMapToBean(response.getData(),c);
    }

}

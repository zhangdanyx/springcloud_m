package com.ls.zd.app.user.controller;

import com.ls.zd.app.user.LoginBaseAction;
import com.ls.zd.beans.Order1001Data;
import com.ls.zd.beans.User1002Data;
import com.ls.zd.datas.AppRequest;
import com.ls.zd.datas.AppResponse;
import com.ls.zd.remote.apache.UserClientService;
import com.ls.zd.remote.feign.UserClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by danzhang on 2019/7/4.
 */

@Component
@Log4j2
public class AppUser1002 extends LoginBaseAction {

    @Autowired
    UserClient userClient;

    @Autowired
    UserClientService userClientService;


    @Override
    public void execute(AppRequest request, AppResponse resbonse) {
       Order1001Data userAppData = userClientService.getUserAppData(AppRequest.builder()
                .bizCode("order1001")
                .reqsn(request.getReqsn())
                .uid(request.getUid())
                .build(),Order1001Data.class);
        resbonse.setData(userAppData);
        log.info("resbonse"+resbonse);

    }

    @Override
    public User1002Data instanceRequestData(String data) {
        return jsonBinder.fromJson(data, User1002Data.class);
    }


}

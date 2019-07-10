package com.ls.zd.app.user.controller;

import com.ls.zd.app.user.LoginBaseAction;
import com.ls.zd.beans.Order1001Data;
import com.ls.zd.beans.User1001Data;
import com.ls.zd.datas.AppRequest;
import com.ls.zd.datas.AppRequestData;
import com.ls.zd.datas.AppResponse;
import com.ls.zd.remote.feign.UserClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by danzhang on 2019/7/4.
 */

@Component
@Log4j2
public class AppUser1001 extends LoginBaseAction {

    @Autowired
    UserClient userClient;


    @Override
    public void execute(AppRequest request, AppResponse resbonse) {
        Order1001Data byId = userClient.findById(99999l);
        AppRequestData data = request.getData();
        resbonse.setData(data);
    }

    @Override
    public User1001Data instanceRequestData(String data) {
        return jsonBinder.fromJson(data, User1001Data.class);
    }


}

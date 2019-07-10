package com.ls.zd.app.order.controller;

import com.ls.zd.app.order.LoginBaseAction;
import com.ls.zd.datas.AppRequest;
import com.ls.zd.datas.AppRequestData;
import com.ls.zd.datas.AppResponse;

/**
 * Created by danzhang on 2019/7/5.
 */
public class AppOrder1002 extends LoginBaseAction{
    @Override
    public void execute(AppRequest request, AppResponse resbonse) {

    }

    @Override
    public <T extends AppRequestData> T instanceRequestData(String data) {
        return null;
    }
}

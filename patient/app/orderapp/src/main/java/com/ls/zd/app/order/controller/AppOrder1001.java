package com.ls.zd.app.order.controller;

import com.ls.zd.app.order.LoginBaseAction;
import com.ls.zd.beans.Order1001Data;
import com.ls.zd.datas.AppRequest;
import com.ls.zd.datas.AppResponse;
import org.springframework.stereotype.Component;

/**
 * Created by danzhang on 2019/7/4.
 */

@Component
public class AppOrder1001 extends LoginBaseAction {
    @Override
    public void execute(AppRequest request, AppResponse resbonse) {

        Order1001Data data = (Order1001Data) request.getData();
        data.setOrderName("奶粉片");
        data.setOrderTotal("222.2");
        resbonse.setData(data);
    }

    @Override
    public Order1001Data instanceRequestData(String data) {
        return jsonBinder.fromJson(data, Order1001Data.class);
    }


}

package com.ls.zd.app.user;


import com.ls.zd.datas.AppRequest;

/**
 * Created by danzhang on 2019/7/4.
 */
public abstract class LoginBaseAction extends AbstractApp {
    @Override
    public void allow(AppRequest request) {
        request.setUid("17614266276");
    }
}








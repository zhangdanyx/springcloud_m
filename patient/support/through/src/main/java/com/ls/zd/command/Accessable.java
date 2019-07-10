package com.ls.zd.command;


import com.ls.zd.datas.AppRequest;

/**
 * Created by danzhang on 2019/7/4.
 */
public interface Accessable extends AppCmd {


    /**
     * 权限校验
     *
     * @param request
     * @return
     */
    void allow(AppRequest request);
}

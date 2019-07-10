package com.ls.zd.command;


import com.ls.zd.datas.AppRequest;
import com.ls.zd.datas.AppRequestData;
import com.ls.zd.datas.AppResponse;

import java.util.logging.Logger;

/**
 * Created by danzhang on 2019/7/4.
 */

public interface AppCmd {


    /**
     * 执行业务逻辑
     * @param request
     * @param resbonse
     */
    void execute(AppRequest request, AppResponse resbonse);


    /**
     * 获取实例id
     * @return
     */
    default String getkey() {
        Logger.getLogger("==============="+this.getClass().getName()).info(getClass().getSimpleName());
        return this.getClass().getSimpleName().toLowerCase();
    }

    /**
     * 实例化参数
     * @param data
     * @param <T>
     * @return
     */
   <T extends AppRequestData> T instanceRequestData(String data);
}

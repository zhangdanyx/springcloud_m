package com.ls.zd.app.user;

import com.ls.zd.command.Accessable;
import com.ls.zd.command.AppCmd;
import com.ls.zd.datas.AppRequest;
import com.ls.zd.datas.AppRequestData;
import com.ls.zd.datas.AppResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;

/**
 * Created by danzhang on 2019/7/4.
 */

@Component
@Log4j2
public class AppControl {

    @Autowired
    List<AppCmd> appCmds;//业务管理


    HashMap<String, AppCmd> cmdContainer = new HashMap<>();


    @PostConstruct
    public void init() {
        appCmds.forEach(cmds -> cmdContainer.put(cmds.getkey(), cmds));
    }

    public AppResponse process(HttpHeaders httpHeaders, MultiValueMap queryValue, String apiCode, String reqsn, String body) {

        AppRequest request = AppRequest.builder()
                .httpHeaders(httpHeaders)
                .queryParam(queryValue)
                .bizCode(apiCode)
                .reqsn(reqsn).build();
        log.info("222222");
        AppCmd appCmd = cmdContainer.get(getKey(request));
        ((Accessable) appCmd).allow(request);


        AppRequestData appRequestData = appCmd.instanceRequestData(body);
        request.setData(appRequestData);

        AppResponse appResbonse = AppResponse.builder().uid(request.getUid()).build();
        appCmd.execute(request, appResbonse);
        return appResbonse;
    }

    private String getKey(AppRequest request) {
        return "app" + request.getBizCode().toLowerCase();
    }
}

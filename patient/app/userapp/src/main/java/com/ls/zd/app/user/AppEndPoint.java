package com.ls.zd.app.user;

import com.ls.zd.datas.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by danzhang on 2019/7/4.
 */

@RestController
@RequestMapping("/patient")
public class AppEndPoint {

    @Autowired
    AppControl appControl;

    @RequestMapping("/api/v1/{reqsn}/{apiCode}")
    public AppResponse join(HttpServletRequest request,
                            @PathVariable String apiCode,
                            @PathVariable String reqsn,
                            @RequestParam MultiValueMap<String, String> queryValue,
                            @RequestHeader HttpHeaders httpHeaders) throws IOException {


        //入参日志

        String body = request.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);


        AppResponse process = appControl.process(httpHeaders, queryValue, apiCode, reqsn, body);
        //返回值日志

        return process;
    }
}
















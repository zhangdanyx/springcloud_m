package com.ls.zd.datas;

import lombok.Builder;
import lombok.Data;
import org.springframework.util.MultiValueMap;

import java.util.Map;

/**
 * Created by danzhang on 2019/7/4.
 */

@Data
@Builder
public class AppRequest {
    private Map httpHeaders;

    private String bizCode;

    private String reqsn;

    private AppRequestData data;

    private String uid;

    private MultiValueMap queryParam;
}

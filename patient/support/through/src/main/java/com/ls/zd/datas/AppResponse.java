package com.ls.zd.datas;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by danzhang on 2019/7/4.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppResponse <T>{
    private  T data;
    private String uid;
}

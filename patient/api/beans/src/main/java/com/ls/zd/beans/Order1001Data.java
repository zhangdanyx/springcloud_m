package com.ls.zd.beans;

import com.ls.zd.datas.AppRequestData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by danzhang on 2019/7/5.
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order1001Data extends AppRequestData {
    private String orderName;
    private String orderTotal;


}

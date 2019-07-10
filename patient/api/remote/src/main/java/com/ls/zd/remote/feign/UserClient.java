package com.ls.zd.remote.feign;

import com.ls.zd.beans.Order1001Data;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "orderapp",url = "http://localhost:8080/patient")
public interface UserClient {

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public Order1001Data findById(@PathVariable("id") Long id);
}

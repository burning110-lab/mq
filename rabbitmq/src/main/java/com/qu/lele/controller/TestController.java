package com.qu.lele.controller;
import com.qu.lele.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: 屈光乐
 * @create: 2022-03-10 10-02
 */
@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @GetMapping(value = "/test/{rand}")
    public String test(@PathVariable("rand") String rand) {
        return testService.testMsg(rand);
    }
}

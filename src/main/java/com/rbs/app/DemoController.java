package com.rbs.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/welcome")
public class DemoController {

    @Autowired
    Environment environment;

    @GetMapping("/app")
    public String getByAppProperties(){
        return environment.getProperty("app.config.message");
    }

}

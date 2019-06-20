package com.rbs.app;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/welcome")
public class DemoController {

    @Value("${app.config.message}")
    String message;

    @Value("${app.env.message}")
    String envMessage;

    @GetMapping("/app")
    public String getByAppProperties(){
        return message;
    }


    @GetMapping("/env")
    public String getEnvMessage() {
        return envMessage;
    }
}

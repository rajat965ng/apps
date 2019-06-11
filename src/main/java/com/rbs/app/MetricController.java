package com.rbs.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@PropertySource(value = {"classpath:git.properties"})
public class MetricController {

    @Autowired
    Environment environment;

    @GetMapping("/version")
    public String getVersion(){
       return environment.getProperty("git.commit.id.abbrev");
    }

}

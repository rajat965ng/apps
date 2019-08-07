package com.vault.example.controller;

import com.vault.example.repository.MyCollection;
import com.vault.example.service.DBservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class DBController {


    @Autowired
    private DBservice dBservice;

    @GetMapping("/db/find")
    public Object getDB(){
        return dBservice.read();
    }

    @PostMapping("/db/write")
    public Object setDB(@RequestBody MyCollection collection){
        return dBservice.write(collection);
    }

}

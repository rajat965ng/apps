package com.vault.example.service;

import com.vault.example.repository.CollectionRepository;
import com.vault.example.repository.MyCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBservice {

    @Autowired
    public CollectionRepository collectionRepository;

    public Object read(){
        return collectionRepository.findAll();
    }

    public Object write(MyCollection collection){
        return collectionRepository.insert(collection);
    }

}

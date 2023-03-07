package com.example.multi.prj.TestService;

import org.springframework.stereotype.Service;

@Service
public class TestService {
    private int i = 1;



    public void increased(){
        this.i += 1;
    }


    public int getNumber(){
        return this.i;
    }
}

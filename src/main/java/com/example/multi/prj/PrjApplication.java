package com.example.multi.prj;

import com.example.multi.prj.dto.MyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(MyConfig.class)
@SpringBootApplication
public class PrjApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrjApplication.class, args);
    }

}

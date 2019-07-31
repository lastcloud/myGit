package com.indusfo.spc.api.startup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Descirption 应用启动类
 * @Author JiWei
 * @Email lastcloud@yeah.net
 * @Date 2019/07/21 04:38
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, MongoAutoConfiguration.class, SecurityAutoConfiguration.class})
@RestController
public class SpcApplication {

    public static void main(String args[]){
        SpringApplication.run(SpcApplication.class, args);
    }

    @GetMapping(value = {"/", "/index"})
    public String home(){
        return "☆☆  SPCBoot项目启动成功  ☆☆ -- Jiwei";
    }
}

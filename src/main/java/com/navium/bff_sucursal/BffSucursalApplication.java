package com.navium.bff_sucursal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {
    "com.navium.bff_sucursal",
    "com.navium.security_lib"
})
public class BffSucursalApplication {

    public static void main(String[] args) {
        SpringApplication.run(BffSucursalApplication.class, args);
    }
}
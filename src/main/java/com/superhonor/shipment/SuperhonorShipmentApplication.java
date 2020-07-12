package com.superhonor.shipment;

import cn.shuibo.annotation.EnableSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author liuweidong
 */
@SpringBootApplication
@EnableSecurity
public class SuperhonorShipmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperhonorShipmentApplication.class, args);
        System.out.println("=================================");
        System.out.println("[superhonor-shipment] 启动完成!!!");
        System.out.println("=================================");
    }

}

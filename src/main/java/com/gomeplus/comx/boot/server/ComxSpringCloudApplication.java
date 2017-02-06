package com.gomeplus.comx.boot.server;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
/**
 * Created by xue on 2/3/17.
 //@ImportResource("spring/spring-boot.xml")
 TODO logback-spring.xml & logback.xml 需要合并
 */
@SpringBootApplication
@ComponentScan("com.gomeplus.comx")
@EnableDiscoveryClient
public class ComxSpringCloudApplication {
    public static void main(String[] args) {
        long starTime = System.currentTimeMillis();
        SpringApplication.run(ComxSpringCloudApplication.class, args);
        long endTime = System.currentTimeMillis();
        long time = endTime - starTime;
        System.out.println("\nStart Time: " + time / 1000 + " s");
        System.out.println("...............................................................");
        System.out.println("..................Service starts successfully..................");
        System.out.println("...............................................................");
    }
}


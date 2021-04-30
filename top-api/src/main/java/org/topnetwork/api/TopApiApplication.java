package org.topnetwork.api;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author CasonCai
 * @since 2021/4/30 10:00 上午
 **/
@SpringBootApplication(scanBasePackages = {
        "org.topnetwork.*"
})
@MapperScan(basePackages = {"org.topnetwork.common.dao"})
public class TopApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TopApiApplication.class, args);
    }

}

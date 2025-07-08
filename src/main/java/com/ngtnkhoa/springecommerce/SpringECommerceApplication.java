package com.ngtnkhoa.springecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringECommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringECommerceApplication.class, args);
    }

}

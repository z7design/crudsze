package com.tr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableJpaRepositories
@ComponentScan({"com.example.service", "com.example.controlle‌​r"})
public class Application {

  public static void main(String[] args) {

    SpringApplication.run(Application.class);
  }
  
}

package com.tr.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
  @Bean
  public ModelMapper mapper(){
    //vai mapear as entidades
    return new ModelMapper();
  }
}

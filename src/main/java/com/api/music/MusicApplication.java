package com.api.music;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(considerNestedRepositories = true)
@ComponentScan(basePackages = "com.api.music")
public class MusicApplication {

  public static void main(String[] args) {
    SpringApplication.run(MusicApplication.class, args);
  }

}

package com.sparta.hh99springlv4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing // Timestamped 설정
@SpringBootApplication
public class Hh99SpringLv4Application {

    public static void main(String[] args) {
        SpringApplication.run(Hh99SpringLv4Application.class, args);
    }

}

package com.rim.rimserver;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RimServerApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RimServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}

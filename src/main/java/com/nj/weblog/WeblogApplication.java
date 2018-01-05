package com.nj.weblog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import nz.net.ultraq.thymeleaf.LayoutDialect;

@EntityScan(
        basePackageClasses = {WeblogApplication.class, Jsr310JpaConverters.class}
)
@SpringBootApplication
public class WeblogApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeblogApplication.class, args);
	}
	
}

package com.carreservation.catalogservice;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
//@EnableCaching
public class CatalogServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(CatalogServiceApplication.class, args);
	}

  @Bean
  public ModelMapper modelMapper() {
    return new ModelMapper();
  }

}

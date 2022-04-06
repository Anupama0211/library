package com.epam.library;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@OpenAPIDefinition(info = @Info(title="Library API", version="1.0", description="Managing Books and Users"))
public class LibraryserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryserviceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate getRest() {
        return new RestTemplate();
    }

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}

//know the use of  transanctional and modifying
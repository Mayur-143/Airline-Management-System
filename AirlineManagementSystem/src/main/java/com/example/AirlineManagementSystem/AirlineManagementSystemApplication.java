// This Java code utilizes Spring Boot to create the foundation for an Airline Management System, defining component scanning, JPA repositories, and entity scanning.
package com.example.AirlineManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class AirlineManagementSystemApplication {

	public static void main(String[] args) {

		SpringApplication.run(AirlineManagementSystemApplication.class, args);
	}

}

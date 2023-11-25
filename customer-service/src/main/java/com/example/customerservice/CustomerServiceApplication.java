package com.example.customerservice;

import com.example.customerservice.config.GlobalConfig;
import com.example.customerservice.entities.Customer;
import com.example.customerservice.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
@EnableConfigurationProperties(GlobalConfig.class)
public class CustomerServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository){
		return args -> {
			Stream.of("Amine","Omayma","Ismail","Hamza")
					.forEach(s -> {
						Customer customer =Customer.builder()
										.firstName(s)
										.lastName("EL "+s.toUpperCase())
										.email(s+ "@gmail.com")
										.build();
						customerRepository.save(customer);
							}
					);

		};
	}
}

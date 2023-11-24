package com.example.accountservice;

import com.example.accountservice.clients.CustomerRestClient;
import com.example.accountservice.entities.BankAccount;
import com.example.accountservice.enums.AccountType;
import com.example.accountservice.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootApplication
@EnableFeignClients
public class AccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(BankAccountRepository accountRepository, CustomerRestClient customerRestClient){
		return args -> {
			customerRestClient.allCustomers().forEach(customer -> {
				for(int i = 0; i<2 ; i++){
					BankAccount account = BankAccount.builder()
							.accountId(UUID.randomUUID().toString())
							.balance((double) Math.round(Math.random()*95000))
							.type(Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
							.currency(Math.random() > 0.5 ? "MAD" : "EURO")
							.createdAt(LocalDate.now())
							.customerId(Long.valueOf(customer.getCustomerId()))
							.customer(customer)
							.build();
					accountRepository.save(account);
				}
			});

		};
	}
}

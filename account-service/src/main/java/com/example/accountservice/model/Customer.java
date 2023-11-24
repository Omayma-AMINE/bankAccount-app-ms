package com.example.accountservice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Customer {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
}

package com.springTime.bankingWebApplication.model;


import jakarta.persistence.*;
import lombok.*;


import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountId;

    private String holderName;
    @Column(unique = true)
    private String accountNumber;
    private double balance;


    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Transaction> transactions = new ArrayList<Transaction>();


    public Account(String holderName, double balance) {
        this.holderName = holderName;
        this.balance = balance;
    }
}

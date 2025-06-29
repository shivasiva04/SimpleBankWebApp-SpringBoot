package com.springTime.bankingWebApplication.service;

import com.springTime.bankingWebApplication.model.Account;
import com.springTime.bankingWebApplication.model.Transaction;
import com.springTime.bankingWebApplication.repository.AccountRepo;
import com.springTime.bankingWebApplication.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

@Service
public class BankService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    // Create account
    public Account createAccount(String holderName, double balance){
        Account newAccount = new Account(holderName,balance);

        String accountNumber = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) +
                String.format("%04d",new Random().nextInt(10000));
        newAccount.setAccountNumber(accountNumber);
        return accountRepo.save(newAccount);
    }

    //Retrive Accounts
    public List<Account> getAllAccounts(){
        return accountRepo.findAll();
    }


    // Deposite
    public void deposite(long accountId, double amount){
        Account account = accountRepo.findById(accountId)
                .orElseThrow(()-> new IllegalArgumentException("Invalid account id"));

        account.setBalance(account.getBalance() + amount);
        accountRepo.save(account);

        Transaction transaction = new Transaction("Deposite", amount , LocalDateTime.now(), account);
        transactionRepo.save(transaction);
    }


    // Withdraw

    public void withdraw(long accountId, double amount){
        Account account = accountRepo.findById(accountId)
                .orElseThrow(()-> new IllegalArgumentException("Invalid account id"));

        if(account.getBalance()<amount){
            throw new IllegalArgumentException("Insufficient balance");
        }

        account.setBalance(account.getBalance() - amount);
        accountRepo.save(account);

        Transaction transaction = new Transaction("Withdraw", amount, LocalDateTime.now(), account);
        transactionRepo.save(transaction);
    }

    // Transaction History

    public List<Transaction> transactionHistory(long accountId, String type, LocalDateTime stratDate, LocalDateTime endDate){
        return transactionRepo.findByAccountIdAndOptionalFilters(accountId, type, stratDate, endDate);
    }


}

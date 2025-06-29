package com.springTime.bankingWebApplication.controller;

import com.springTime.bankingWebApplication.model.Account;
import com.springTime.bankingWebApplication.model.Transaction;
import com.springTime.bankingWebApplication.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BankController {

    @Autowired
    private BankService bankService;

    // Display all account

    @GetMapping("/")
    public String getAllAccounts(Model model){
        List<Account> accountList = bankService.getAllAccounts();
        model.addAttribute("accountList", accountList);
        return "Accounts";
    }

    // Add a new account
    @PostMapping("/accounts/add")
    public String CreateAccount(@RequestParam String holderName, @RequestParam double initialBalance){
        bankService.createAccount(holderName,initialBalance);
        return "redirect:/";
    }

    // Deposite
    @PostMapping("/{accountId}/deposite")
    public String deposite(@PathVariable("accountId") long accountId, @RequestParam("amount") double amount){
        bankService.deposite(accountId,amount);
        return "redirect:/";
    }

    // Withdraw
    @PostMapping("/{accountId}/withdraw")
    public String withdraw(@PathVariable("accountId") long accountId, @RequestParam("amount") double amount, Model model){
        try{
            bankService.withdraw(accountId,amount);
            return "redirect:/";
        }catch (IllegalArgumentException e){
            model.addAttribute("errorMessage",e.getMessage());
            model.addAttribute("accounts", bankService.getAllAccounts());
            return "Accounts";
        }
    }

    // view transaction history
    @GetMapping("/{accountId}/transactions")
    public String viewTransactionHistory(@PathVariable("accountId") long accountId, @RequestParam(required = false) String type, @RequestParam(required = false) LocalDateTime startDate, @RequestParam(required = false) LocalDateTime endDate, Model model){
        if(startDate == null){
            startDate = LocalDateTime.of(1900,1,1,0,0,0,0);
        }
        if(endDate == null){
            endDate = LocalDateTime.of(9999,12,31,23,59,59,999999);
        }
        List<Transaction> transactions = bankService.transactionHistory(accountId,type,startDate,endDate);
        model.addAttribute("transactions", transactions);
        return "Transactions";
    }
}

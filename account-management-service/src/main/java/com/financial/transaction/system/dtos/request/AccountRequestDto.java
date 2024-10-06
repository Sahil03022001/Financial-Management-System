package com.financial.transaction.system.dtos.request;

import com.financial.transaction.system.enums.AccountType;
import com.financial.transaction.system.enums.CurrencyType;
import jakarta.validation.constraints.NotBlank;

public class AccountRequestDto {

    @NotBlank(message = "userId is mandatory")
    private String userId;

    @NotBlank(message = "accountNumber is mandatory")
    private String accountNumber;

    private AccountType accountType;
    private Long balance;
    private CurrencyType currencyType;

    public AccountRequestDto() {
    }

    public AccountRequestDto(String userId, String accountNumber, AccountType accountType, Long balance, CurrencyType currencyType) {
        this.userId = userId;
        this.accountNumber = accountNumber;
        this.accountType = accountType;
        this.balance = balance;
        this.currencyType = currencyType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }
}

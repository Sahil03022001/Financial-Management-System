package com.financial.transaction.accountsystem.dtos.response;

import com.financial.transaction.accountsystem.enums.AccountStatus;
import com.financial.transaction.accountsystem.enums.CurrencyType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDto {

    private String accountNumber;
    private String userId;
    private Long balance;
    private CurrencyType currencyType;
    private AccountStatus accountStatus;
    private Date created;
    private Date updated;
}

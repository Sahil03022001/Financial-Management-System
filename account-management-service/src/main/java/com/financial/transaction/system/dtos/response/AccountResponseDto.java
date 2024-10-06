package com.financial.transaction.system.dtos.response;

import com.financial.transaction.system.enums.AccountStatus;
import com.financial.transaction.system.enums.CurrencyType;
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

package com.financial.transaction.accountsystem.entity;

import com.financial.transaction.system.enums.AccountStatus;
import com.financial.transaction.system.enums.AccountType;
import com.financial.transaction.system.enums.CurrencyType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity(name = "account")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String accountId;

    private String userId;

    @NotBlank(message = "Account number is mandatory")
    @Size(min = 10, max = 12, message = "Account number must be between 10 and 12 characters")
    @Pattern(regexp = "\\d+", message = "Account number must contain only digits")
    private String accountNumber;

    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    private Long balance;

    private CurrencyType currencyType;

    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    private Date created;

    private Date updated;

}

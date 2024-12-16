package com.financial.transaction.system.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionDto {

    private String sourceAccountId;
    private String destAccountId;
    private Long amount;
}

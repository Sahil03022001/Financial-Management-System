package com.financial.transaction.system.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestByAccountNumber {

    private String senderAccountNumber;
    private String receiverAccountNumber;
    private Long amount;
}

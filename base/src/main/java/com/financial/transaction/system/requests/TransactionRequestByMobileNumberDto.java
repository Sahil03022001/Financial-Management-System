package com.financial.transaction.system.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequestByMobileNumberDto {

    private String receiverMobileNumber;
    private String senderAccountNumber;
    private Long amount;
}

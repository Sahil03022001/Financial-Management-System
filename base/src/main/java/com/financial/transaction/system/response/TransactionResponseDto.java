package com.financial.transaction.system.response;

import java.util.Date;

public class TransactionResponseDto extends Response {

    private String transactionId;
    private Long amount;
    private String srcAccountNo;
    private String destAccountNo;
    private Date transactionDate;
}

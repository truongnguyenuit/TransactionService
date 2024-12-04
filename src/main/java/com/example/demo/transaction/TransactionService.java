package com.example.demo.transaction;

import com.example.demo.dto.TransactionDTO;
import com.example.demo.dto.TransactionReq;
import com.example.demo.utils.exception.ApiRequestException;

import java.util.List;

public interface TransactionService {
    TransactionDTO issueBook(TransactionReq req, String authorization) throws ApiRequestException;

    Transaction returnBook(Long transactionId, String authorization) throws ApiRequestException;

    List<Transaction> getTransactions();
}

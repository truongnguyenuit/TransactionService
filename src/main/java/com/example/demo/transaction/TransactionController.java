package com.example.demo.transaction;

import com.example.demo.dto.TransactionDTO;
import com.example.demo.dto.TransactionReq;
import com.example.demo.utils.exception.ApiRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TransactionDTO> issueBook(
            @RequestHeader String authorization,
            @RequestBody TransactionReq req) throws ApiRequestException {
        return new ResponseEntity<>(transactionService.issueBook(req, authorization), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Transaction> returnBook(
            @RequestHeader String authorization,
            @PathVariable Long id) throws ApiRequestException {
        return new ResponseEntity<>(transactionService.returnBook(id, authorization), HttpStatus.OK);
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('CUSTOMER')")
    public ResponseEntity<List<Transaction>> getTransactions() {
        return new ResponseEntity<>(transactionService.getTransactions(), HttpStatus.OK);
    }
}

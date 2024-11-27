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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    public ResponseEntity<TransactionDTO> issueBook(@RequestBody TransactionReq req) throws ApiRequestException {
        return new ResponseEntity<>(transactionService.issueBook(req), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Transaction> returnBook(@PathVariable Long id) throws ApiRequestException {
        return new ResponseEntity<>(transactionService.returnBook(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Transaction>> getTransactions() {
        return new ResponseEntity<>(transactionService.getTransactions(), HttpStatus.OK);
    }
}

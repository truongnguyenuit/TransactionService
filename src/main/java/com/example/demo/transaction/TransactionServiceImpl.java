package com.example.demo.transaction;

import com.example.demo.client.BookClient;
import com.example.demo.client.UserClient;
import com.example.demo.dto.BookDTO;
import com.example.demo.dto.TransactionDTO;
import com.example.demo.dto.UserDTO;

import com.example.demo.dto.TransactionReq;
import com.example.demo.utils.exception.ApiRequestException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final BookClient bookClient;
    private final UserClient userClient;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  BookClient bookClient,
                                  UserClient userClient) {
        this.transactionRepository = transactionRepository;
        this.bookClient = bookClient;
        this.userClient = userClient;
    }

    @Override
    public TransactionDTO issueBook(TransactionReq req, String authorization) throws ApiRequestException {
        BookDTO bookDTO = bookClient.getBookById(req.bookId(), authorization);

        if (bookDTO == null || bookDTO.isAvailable().equals(false)) {
            throw new ApiRequestException("Book not available");
        }

        UserDTO userDTO = userClient.getUserById(req.userId(), authorization);
        if (userDTO == null) {
            throw new ApiRequestException("User not found");
        }

        BookDTO updateBookIsAvailable = bookClient.updateBookIsAvailable(bookDTO.id(), false, authorization);
        if (updateBookIsAvailable.isAvailable().equals(true)) {
            throw new ApiRequestException("Book service problems");
        }

        Transaction transaction = transactionRepository.save(new Transaction(bookDTO.id(), userDTO.id()));
        return new TransactionDTO(
                transaction.getId(),
                bookDTO,
                userDTO,
                transaction.getReturned(),
                transaction.getCreatedAt(),
                transaction.getUpdatedAt()
        );
    }

    @Override
    public Transaction returnBook(Long transactionId, String authorization) throws ApiRequestException {
        Optional<Transaction> transaction = transactionRepository.findById(transactionId);
        if(transaction.isEmpty()) {
            throw new ApiRequestException("Transaction not found");
        }
        if(transaction.get().getReturned().equals(true)) {
            throw new ApiRequestException("Transaction is completed");
        }

        BookDTO updateBookIsAvailable = bookClient.updateBookIsAvailable(
                transaction.get().getBookId(),
                true,
                authorization);
        if (updateBookIsAvailable.isAvailable().equals(false)) {
            throw new ApiRequestException("Book service problems");
        }
        transaction.get().setReturned(true);
        transaction.get().setUpdatedAt(new Date());
        return transactionRepository.save(transaction.get());
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }
}

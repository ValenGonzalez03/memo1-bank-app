package com.aninfo.service;

import com.aninfo.model.BankTransaction;
import com.aninfo.model.Deposit;
import com.aninfo.model.Extraction;
import com.aninfo.repository.BankTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BankTransactionService {

    @Autowired
    private BankTransactionRepository transactionRepository;

    private BankTransaction determinateTransaction(Long cbu, Double sum, String type) {
        if (type.equals("Extraction")) {
            return new Extraction(cbu, sum);
        } else {
            return new Deposit(cbu,sum);
        }

    }

    public BankTransaction createBankTransaction(Long cbu, Double sum, String type) {
        BankTransaction transaction = this.determinateTransaction(cbu, sum, type);
        return transactionRepository.save(transaction);
    }

    public Optional<BankTransaction> findById(Integer id) {
        return transactionRepository.findById(id);
    }

    public void deleteById(Integer id) {
        transactionRepository.deleteById(id);
    }

    public List<BankTransaction> findAllByAccount(Long cbu) {
        List<BankTransaction> listTransactions = transactionRepository.findAll();
        List<BankTransaction> listTransactionsAccount = new ArrayList<>();
        for (BankTransaction bankTransaction : listTransactions) {
            if (bankTransaction.getCbuAccount().equals(cbu)) {
                listTransactionsAccount.add(bankTransaction);
            }
        }
        return listTransactionsAccount;
    }
}

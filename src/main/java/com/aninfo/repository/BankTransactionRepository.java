package com.aninfo.repository;

import com.aninfo.model.Account;
import com.aninfo.model.BankTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource
public interface BankTransactionRepository extends CrudRepository<BankTransaction, Integer> {

    @Override
    List<BankTransaction> findAll();
}

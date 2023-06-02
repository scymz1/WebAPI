package com.example.webapi.repository;

import com.example.webapi.pojo.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer> {
    List<Transaction> getTransactionByCustomerId(int id);

    @Override
    void delete(Transaction entity);
    //List<Transaction> getTransactionByCustomerIdAndDateBetween(int customerId, Date date1, Date date2);
}

package com.example.webapi.service;

import com.example.webapi.pojo.entity.Customer;
import com.example.webapi.pojo.entity.Transaction;
import com.example.webapi.pojo.responseDTO.UserRewardDTO;

public interface WebAPIService {
    int insertUser(Customer customer);

    int insertTransactions(Transaction transaction);


    UserRewardDTO getCustomerRewards(int customerId);

    Customer getCustomer(int id);

    String deleteTransactions(Transaction transaction);

    String deleteCustomer(Customer customer);
}

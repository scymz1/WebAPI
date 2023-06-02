package com.example.webapi.service;

import com.example.webapi.pojo.entity.Customer;
import com.example.webapi.pojo.entity.Transaction;
import com.example.webapi.pojo.responseDTO.UserRewardDTO;
import com.example.webapi.repository.CustomerRepo;
import com.example.webapi.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class WebAPIServiceImpl implements WebAPIService {
    public CustomerRepo customerRepo;
    public TransactionRepo transactionRepo;

    @Autowired
    public WebAPIServiceImpl(CustomerRepo customerRepo, TransactionRepo transactionRepo){
        this.customerRepo = customerRepo;
        this.transactionRepo = transactionRepo;
    }

    /**
     * Add user to dataBase.
     */
    @Override
    public int insertUser(Customer customer){
        this.customerRepo.save(customer);
        return customer.getId();
    }

    /**
     * Add transactions to dataBase.
     */
    @Override
    public int insertTransactions(Transaction transaction){
        this.transactionRepo.save(transaction);
        return transaction.getTransactionId();
    }

    /**
     * Get rewards of specific user each month as well as the total value.
     */
    @Override
    public UserRewardDTO getCustomerRewards(int customerId){
        List<Customer> customerList = customerRepo.getCustomersById(customerId);
        if(customerList == null || customerList.size() == 0){
            return null;
        }
        Customer customer = customerList.get(0);
        List<Transaction> transactionList = transactionRepo.getTransactionByCustomerId(customerId);
        HashMap<Month, Integer> records = new HashMap<>();
        for(Transaction t: transactionList){
            Month m = t.getDate().toLocalDate().getMonth();
            Float amount = t.getAmount();
            int reward = (int) Math.max(0, amount - 100) * 2;
            if(amount > 50){
                reward += (int) Math.min(amount - 50, 50);
            }
            records.put(m, records.getOrDefault(m, 0) + reward);
        }

        List<UserRewardDTO.RewardsEachMonth> rewardsEachMonthList = new ArrayList<>();
        int total = 0;
        for(Month m: records.keySet()){
            total += records.get(m);
            rewardsEachMonthList.add(new UserRewardDTO.RewardsEachMonth(m, records.get(m)));
        }
        return new UserRewardDTO(customer.getFirstName(), customer.getLastName(), total, rewardsEachMonthList);
    }

    /**
     * Get Specific information of one customer.
     */
    @Override
    public Customer getCustomer(int id){
        List<Customer> customerList = customerRepo.getCustomersById(id);
        if(customerList == null || customerList.size() == 0){
            return null;
        }
        return customerList.get(0);
    }

    @Override
    public String deleteTransactions(Transaction transaction) {
        this.transactionRepo.delete(transaction);
        return "Success";
    }

    @Override
    public String deleteCustomer(Customer customer) {
        this.customerRepo.delete(customer);
        return "Success";
    }
}

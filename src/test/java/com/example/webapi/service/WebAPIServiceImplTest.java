package com.example.webapi.service;

import com.example.webapi.pojo.entity.Customer;
import com.example.webapi.pojo.entity.Transaction;
import com.example.webapi.pojo.responseDTO.UserRewardDTO;
import com.example.webapi.repository.CustomerRepo;
import com.example.webapi.repository.TransactionRepo;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WebAPIServiceImplTest {

    private static WebAPIService webAPIService;

    @BeforeAll
    public static void TestInit() {
        CustomerRepo customerRepo = mock(CustomerRepo.class);
        TransactionRepo transactionRepo = mock(TransactionRepo.class);
        ArrayList<Customer> customerArrayList = new ArrayList<>();
        customerArrayList.add(new Customer(1, "Minghao", "Zhou", null));
        ArrayList<Customer> customerArrayList2 = new ArrayList<>();
        customerArrayList.add(new Customer(2, "Harry", "Potter", null));

        when(customerRepo.getCustomersById(anyInt())).thenReturn(null);
        when(customerRepo.getCustomersById(1)).thenReturn(customerArrayList);
        when(customerRepo.getCustomersById(2)).thenReturn(customerArrayList2);
        //System.out.println(customerRepo.getCustomersById(1));
        ArrayList<Transaction> transactions = new ArrayList<>();
        transactions.add(new Transaction(1, new Date(2022, 0, 30), 100.0F, customerArrayList.get(0)));
        transactions.add(new Transaction(3, new Date(2022, 1, 25), 251.0F, customerArrayList.get(0)));
        transactions.add(new Transaction(5, new Date(2022, 2, 30), 700.0F, customerArrayList.get(0)));

        when(transactionRepo.getTransactionByCustomerId(anyInt())).thenReturn(null);
        when(transactionRepo.getTransactionByCustomerId(1)).thenReturn(transactions);

        webAPIService = new WebAPIServiceImpl(customerRepo, transactionRepo);
    }

    @Test
    void insertUser() {
    }

    @Test
    void insertTransactions() {
    }

    @Test
    void getCustomerRewards() {
        UserRewardDTO userRewardDTO = webAPIService.getCustomerRewards(1);
        assertEquals(userRewardDTO.getFirstName(), "Minghao");
        assertEquals(userRewardDTO.getLastName(), "Zhou");
        for(UserRewardDTO.RewardsEachMonth rewardsEachMonth: userRewardDTO.getRewardsEachMonthList()) {
            if(rewardsEachMonth.getMonth().getValue() == 1){
                assertEquals(rewardsEachMonth.getRewards(), 50);
            } else if (rewardsEachMonth.getMonth().getValue() == 2) {

                assertEquals(rewardsEachMonth.getRewards(), 50 + 151 * 2);
            } else if (rewardsEachMonth.getMonth().getValue() == 3) {
                assertEquals(rewardsEachMonth.getRewards(), 600 * 2 + 50);
            }
        }
        assertEquals(userRewardDTO.getTotal(), 50 + 352 + 1250);
    }
}
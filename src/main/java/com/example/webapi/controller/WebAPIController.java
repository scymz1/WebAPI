package com.example.webapi.controller;

import com.example.webapi.pojo.entity.Customer;
import com.example.webapi.pojo.entity.Transaction;
import com.example.webapi.pojo.responseDTO.UserRewardDTO;
import com.example.webapi.service.WebAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webAPI")
public class WebAPIController {

    private final WebAPIService webAPIService;

    @Autowired
    public WebAPIController(WebAPIService webAPIService){
        this.webAPIService = webAPIService;
    }

    /**
     * Add customer to db.
     */
    @PostMapping("/customer")
    public ResponseEntity<Integer> addCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(webAPIService.insertUser(customer), HttpStatus.OK);
    }

    /**
     * add transaction to transaction table.
     */
    @PostMapping("/transaction")
    public ResponseEntity<Integer> addTransaction(@RequestBody Transaction transaction){
        return new ResponseEntity<>(webAPIService.insertTransactions(transaction), HttpStatus.OK);
    }


    /**
     * get one specific customer information.
     */
    @GetMapping("/customer")
    public ResponseEntity<Customer> getCustomer(@RequestParam int id){
        return new ResponseEntity<>(webAPIService.getCustomer(id), HttpStatus.OK);
    }

    /**
     * Get rewards of specific user each month as well as the total value.
     */
    @GetMapping("/rewards")
    public ResponseEntity<UserRewardDTO> getRewards(@RequestParam int id) {
        return new ResponseEntity<>(webAPIService.getCustomerRewards(id), HttpStatus.OK);
    }

    /**
     * Delete one specific transactions.
     */
    @DeleteMapping("/transaction")
    public ResponseEntity<String> deleteTransaction(@RequestBody Transaction transaction){
        return new ResponseEntity<>(webAPIService.deleteTransactions(transaction), HttpStatus.OK);
    }

    @DeleteMapping("/customer")
    public ResponseEntity<String> deleteCustomer(@RequestBody Customer customer){
        return new ResponseEntity<>(webAPIService.deleteCustomer(customer), HttpStatus.OK);
    }
}

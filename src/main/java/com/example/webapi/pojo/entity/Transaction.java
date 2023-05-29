package com.example.webapi.pojo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "Transaction")
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int transactionId;

    @Getter
    @Column
    Date date;

    @Getter
    @Column
    Float amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @Getter
    @Setter
    Customer customer;
}

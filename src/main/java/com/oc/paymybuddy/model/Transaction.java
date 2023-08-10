package com.oc.paymybuddy.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date operationDate;
    private String operationDescription;
    private Account account;

    public Transaction(Long id, Date operationDate, String operationDescription) {
        this.id = id;
        this.operationDate = operationDate;
        this.operationDescription = operationDescription;
    }
}

package com.oc.paymybuddy.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@ToString
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date operationDate;
    private String operationDescription;

    //@ManyToOne(fetch = FetchType.LAZY)
  //  private Account account;

    public Transaction(Long id, Date operationDate, String operationDescription) {
        this.id = id;
        this.operationDate = operationDate;
        this.operationDescription = operationDescription;
    }
}

package com.aninfo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BankTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    Long cbuAccount;
    Double sum;
    String type;

    public BankTransaction() {}

    public BankTransaction(Long cbuAccount, Double sum) {
        this.sum = sum;
        this.cbuAccount = cbuAccount;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCbuAccount() {
        return cbuAccount;
    }
    public void setCbuAccount(Long cbuAccount) {
        this.cbuAccount = cbuAccount;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

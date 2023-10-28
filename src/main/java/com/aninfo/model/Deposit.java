package com.aninfo.model;

import javax.persistence.Entity;

@Entity
public class Deposit extends BankTransaction{

    public Deposit() {

    }
    public Deposit(Long cbu, Double sum) {
        super(cbu, sum);
        this.type = "Deposit";
    }
}

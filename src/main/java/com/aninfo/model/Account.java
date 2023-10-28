package com.aninfo.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cbu;

    private Double balance;

    private boolean promoHasApplied = false;
    public Account(){
    }

    public Account(Double balance) {
        this.balance = balance;
    }

    public Account(Double balance, boolean promoHasApplied) {
        this.balance = balance;
        this.promoHasApplied = promoHasApplied;
    }

    public Long getCbu() {
        return cbu;
    }

    public void setCbu(Long cbu) {
        this.cbu = cbu;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public boolean isPromoHasApplied() {
        return promoHasApplied;
    }

    public void setPromoHasApplied(boolean promoHasApplied) {
        this.promoHasApplied = promoHasApplied;
    }
}

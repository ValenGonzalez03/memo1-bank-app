package com.aninfo.model;

import javax.persistence.Entity;

@Entity
public class Extraction extends BankTransaction {

    public Extraction() {

    }

    public Extraction(Long cbu, Double sum) {
        super(cbu, sum);
        this.type = "Extraction";
    }
}

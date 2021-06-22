package com.crypto.Alerter.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
public class PriceAlert {

    @Id
    @GeneratedValue
    private Long id;
    private String currency;
    private double price;
    private boolean active;

}

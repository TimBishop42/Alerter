package com.crypto.Alerter.model.response;

import lombok.Data;

@Data
public class BitrexCurrencyResponse {

    private String symbol;
    private String name;
    private String coinType;
    private String status;
    private Integer minConfirmations;
    private String notice;
    private Double txFee;
    private String logoUrl;



}

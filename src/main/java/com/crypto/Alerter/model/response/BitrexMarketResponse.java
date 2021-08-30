package com.crypto.Alerter.model.response;

import lombok.Data;

import java.util.List;

@Data
public class BitrexMarketResponse {

    private String symbol;
    private String baseCurrencySymbol;
    private String quoteCurrencySymbol;
    private Double minTradeSize;
    private Integer precision;
    private String status;
    private String createdAt;
    private String notice;
    private List<String> prohibitedIn;
    private List<String> associatedTermsOfService;
    private List<String> tags;

}
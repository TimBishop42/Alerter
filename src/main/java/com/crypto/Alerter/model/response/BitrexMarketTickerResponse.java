package com.crypto.Alerter.model.response;

import lombok.Data;

@Data
public class BitrexMarketTickerResponse {

    private String symbol;
    private Double lastTradeRate;
    private Double bidRate;
    private Double askRate;

}
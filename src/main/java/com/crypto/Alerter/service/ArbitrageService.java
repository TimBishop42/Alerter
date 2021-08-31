package com.crypto.Alerter.service;

import com.crypto.Alerter.client.BitrexClient;
import com.crypto.Alerter.client.DefiChainClient;
import com.crypto.Alerter.model.response.BitrexMarketResponse;
import com.crypto.Alerter.model.response.BitrexMarketTickerResponse;
import com.crypto.Alerter.model.response.DefiChainSwapsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ArbitrageService {

    @Autowired
    private BitrexClient bitrexClient;

    @Autowired
    private DefiChainClient defiChainClient;

    public Mono<BitrexMarketTickerResponse> getBitrexMarketBySymbol(String symbol) {
        Mono<BitrexMarketTickerResponse> responseMono = bitrexClient.getCurrencyBySymbol(symbol);
        return responseMono;
    }

    public Flux<BitrexMarketResponse> getBitrexMartkets() {
        return bitrexClient.getMarkets();
    }

    public Flux<Object> getDefiChainSwapMarkets() {
        return defiChainClient.getDefiChainSwapsMarket();
    }
}

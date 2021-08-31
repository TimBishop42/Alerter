package com.crypto.Alerter.client;

import com.crypto.Alerter.model.response.DefiChainSwapsResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class DefiChainClient {

    private WebClient defiChainWebClient = WebClient.create("https://api.defichain.io/v1");

    private static final String SWAPS_MARKET_ENDPOINT = "/listswaps";

    public Flux<Object> getDefiChainSwapsMarket() {
        return defiChainWebClient.get()
                .uri(SWAPS_MARKET_ENDPOINT)
                .retrieve()
                .bodyToFlux(Object.class);
    }
}

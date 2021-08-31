package com.crypto.Alerter.controller;


import com.crypto.Alerter.model.response.BitrexMarketResponse;
import com.crypto.Alerter.model.response.BitrexMarketTickerResponse;
import com.crypto.Alerter.service.ArbitrageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class Controller {

    @Autowired
    private ArbitrageService arbitrageService;

    @GetMapping(value = "/bitrex/market/{symbol}")
    public ResponseEntity<Mono<BitrexMarketTickerResponse>> getBitrexMarket(@PathVariable("symbol") String marketSymbol) {
        //tUDSTDFI
        log.info("Received request to call bitrex client by market symbol {}", marketSymbol);
        return new ResponseEntity<>(arbitrageService.getBitrexMarketBySymbol(marketSymbol), HttpStatus.OK);
    }

    @GetMapping(value = "/bitex/markets")
    public ResponseEntity<Flux<BitrexMarketResponse>> getAllMarkets() {
        log.info("Received request to call bitrex client and retrieve all markets");
        return new ResponseEntity<>(arbitrageService.getBitrexMartkets(), HttpStatus.OK);
    }

    @GetMapping(value = "/defichain/markets")
    public ResponseEntity<Flux<Object>> getDefiChainSwaps() {
        log.info("Received request to call defichain client and retrieve all markets");
        return new ResponseEntity<>(arbitrageService.getDefiChainSwapMarkets(), HttpStatus.OK);
    }


}

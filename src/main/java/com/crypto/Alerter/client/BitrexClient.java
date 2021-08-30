package com.crypto.Alerter.client;

import com.crypto.Alerter.crypt.CryptoSign;
import com.crypto.Alerter.model.response.BitrexCurrencyResponse;
import com.crypto.Alerter.model.response.BitrexMarketResponse;
import com.crypto.Alerter.model.response.BitrexMarketTickerResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class BitrexClient {

    @Value("${bitrex.api.url}")
    private String bitrexBaseUrl;

    @Value("${bitrex.key}")
    private String bitrexKey;

    @Value("${bitrex.secret}")
    private String bitrexSecret;

    @Autowired
    private CryptoSign cryptoSign;

    private WebClient bitrexWebClient = WebClient.create("https://api.bittrex.com/v3");

    private static final String SINGLE_CURRENCY_TICKER_BY_SYMBOL = "/markets/%s/ticker";
    private static final String MARKETS_ALL = "/markets";

    public Mono<BitrexMarketTickerResponse> getCurrencyBySymbol(String symbol) {
        String urlQuery = String.format(SINGLE_CURRENCY_TICKER_BY_SYMBOL, symbol);
        String fullUrlQuery = bitrexBaseUrl + urlQuery;
        String millisTime = String.valueOf(System.currentTimeMillis());
        String contentHash = cryptoSign.get512Hash("");
        String signedSig = getSignedSignature(Arrays.asList(millisTime, fullUrlQuery, HttpMethod.GET.toString(), contentHash, ""));
        log.info("Querying bitrex for market {}, millisTime: {}, url: {}, pubKey: {}", symbol, millisTime, fullUrlQuery, bitrexKey);
        return bitrexWebClient.get()
                .uri(urlQuery)
                .header("Api-Key", bitrexKey)
                .header("Api-Timestamp", millisTime)
                .header("Api-Content-Hash", contentHash)
                .header("Api-Signature", signedSig)
                .retrieve()
                .bodyToMono(BitrexMarketTickerResponse.class);
    }

    public Flux<BitrexMarketResponse> getMarkets() {
        String fullUrlQuery = bitrexBaseUrl + MARKETS_ALL;
        String millisTime = String.valueOf(System.currentTimeMillis());
        String contentHash = cryptoSign.get512Hash("");
        String signedSig = getSignedSignature(Arrays.asList(millisTime, fullUrlQuery, HttpMethod.GET.toString(), contentHash, ""));
        log.info("Querying bitrex for all markets, millisTime: {}, url: {}, pubKey: {}", millisTime, fullUrlQuery, bitrexKey);
        return bitrexWebClient.get()
                .uri(MARKETS_ALL)
                .header("Api-Key", bitrexKey)
                .header("Api-Timestamp", millisTime)
                .header("Api-Content-Hash", contentHash)
                .header("Api-Signature", signedSig)
                .retrieve()
                .bodyToFlux(BitrexMarketResponse.class);
    }

    private String getSignedSignature(List <String> headers) {
        String preSign = StringUtils.collectionToDelimitedString(headers, "");
        return cryptoSign.getHmac512(preSign, bitrexSecret);
    }

    @SneakyThrows
    private String getRequestBodyHash(String requestBody) {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.reset();
        if (isNull(requestBody)) {
            md.update("".getBytes("utf8"));
        } else {
            md.update(requestBody.getBytes("utf8"));
        }
        return String.format("%0128x", new BigInteger(1, md.digest()));

    }

}

package com.crypto.Alerter.crypt;

import org.junit.jupiter.api.Test;

public class CryptoSignTest {

    CryptoSign cryptoSign = new CryptoSign();

    @Test
    public void getHmac512Test() {

        String input = "Some Text";
        String secret = "123456";

        String output = cryptoSign.getHmac512(input, secret);
        System.out.println(output);

    }
}

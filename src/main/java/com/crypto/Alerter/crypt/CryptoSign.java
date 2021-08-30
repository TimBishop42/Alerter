package com.crypto.Alerter.crypt;

import lombok.Data;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import org.springframework.stereotype.Component;


@Data
@Component
public class CryptoSign {


    public String getHmac512(String input, String secret) {
        byte [] key = secret.getBytes();
        HmacUtils hm512 = new HmacUtils(HmacAlgorithms.HMAC_SHA_512, key);
        return hm512.hmacHex(input);
    }

    public String get512Hash(String input) {
        return DigestUtils.sha512Hex(input);
    }

}

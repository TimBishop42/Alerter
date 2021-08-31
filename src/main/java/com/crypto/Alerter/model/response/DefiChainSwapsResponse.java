package com.crypto.Alerter.model.response;

import lombok.Data;

@Data
public class DefiChainSwapsResponse {

    public String base_id;
    public String base_name;
    public String base_symbol;
    public String quote_id;
    public String quote_name;
    public String quote_symbol;
    public String last_price;
    public double base_volume;
    public double quote_volume;
}

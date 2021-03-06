package com.superhonor.shipment.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author liuweidong
 */
@Component
@PropertySource("classpath:auth.properties")
public class AuthParameters {

    private String jwtTokenSecret;
    private long tokenExpiredMs;

    public String getJwtTokenSecret() {
        return jwtTokenSecret;
    }

    @Value("${jwtTokenSecret}")
    public void setJwtTokenSecret(String jwtTokenSecret) {
        this.jwtTokenSecret = jwtTokenSecret;
    }

    public long getTokenExpiredMs() {
        return tokenExpiredMs;
    }

    @Value("${tokenExpiredMs}")
    public void setTokenExpiredMs(long tokenExpiredMs) {
        this.tokenExpiredMs = tokenExpiredMs;
    }
}

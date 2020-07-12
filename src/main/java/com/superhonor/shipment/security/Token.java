package com.superhonor.shipment.security;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author liuweidong
 */
@Setter
@Getter
@ToString
public class Token implements Serializable {

    private String tokenType;
    private String token;

    public Token() {
    }

    public Token(String tokenType, String token) {
        this.tokenType = tokenType;
        this.token = token;
    }
}

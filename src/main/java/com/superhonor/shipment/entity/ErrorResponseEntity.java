package com.superhonor.shipment.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author liuweidong
 */
@Setter
@Getter
public class ErrorResponseEntity implements Serializable {

    private int code;
    private String message;

    public ErrorResponseEntity(int code, String message) {
        this.code = code;
        this.message = message;
    }
}

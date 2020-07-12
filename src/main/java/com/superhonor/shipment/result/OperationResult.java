package com.superhonor.shipment.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

/**
 * @author liuweidong
 */
@Getter
@Setter
@ToString
public class OperationResult<T extends Object> implements Serializable {
    private int code;
    private String message;
    private Long timestamp;
    private T result;

    public static <T> OperationResult<T> Success(String message, T result) {
        return new OperationResult<>(message, result);
    }

    public static OperationResult Fail(int httpCode, String message) {
        return new OperationResult(httpCode, message);
    }

    public OperationResult() {
        timestamp = System.currentTimeMillis();
    }

    public OperationResult(String message, T result) {
        this(HttpServletResponse.SC_OK, message, result);
    }

    public OperationResult(int httpCode, String message) {
        this(httpCode, message, null);
    }

    public OperationResult(int httpCode, String message, T result) {
        this();
        this.code = httpCode;
        this.message = message;
        this.result = result;
    }
}
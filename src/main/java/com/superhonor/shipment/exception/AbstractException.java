package com.superhonor.shipment.exception;

/**
 * @author liuweidong
 */
public abstract class AbstractException extends RuntimeException {
    public AbstractException() {
    }

    public AbstractException(Throwable ex) {
        super(ex);
    }

    public AbstractException(String message) {
        super(message);
    }

    public AbstractException(String message, Throwable ex) {
        super(message, ex);
    }

    /**
     * 获取异常对应的业务编码
     * @return
     */
    public abstract int getCode();
}

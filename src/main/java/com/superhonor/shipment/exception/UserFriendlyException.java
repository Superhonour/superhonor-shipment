package com.superhonor.shipment.exception;

import javax.servlet.http.HttpServletResponse;

/**
 * @author liuweidong
 */
public class UserFriendlyException extends AbstractException {

    public UserFriendlyException(String message) {
        super(message);
    }

    @Override
    public int getCode() {
        return HttpServletResponse.SC_BAD_REQUEST;
    }
}

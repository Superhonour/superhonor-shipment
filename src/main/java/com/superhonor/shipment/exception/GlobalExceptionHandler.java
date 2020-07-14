package com.superhonor.shipment.exception;

import com.superhonor.shipment.result.OperationResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author liuweidong
 */
@Slf4j
@ControllerAdvice(basePackages = "com.superhonor")
public class GlobalExceptionHandler {

    final static String DEFAULT_ERROR_MASSAGE = "系统走神了,请稍候再试.";

    /**
     * 异常处理
     */
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public OperationResult handle(HttpServletResponse response, Exception ex) {
        OperationResult result = new OperationResult();
        if (ex instanceof UserFriendlyException) {
            log.error(ex.getMessage(), ex);
            result.setCode(((UserFriendlyException) ex).getCode());
            result.setMessage(ex.getMessage());
        } else {
            log.error(ex.getMessage(), ex);
            result.setCode(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            result.setMessage(DEFAULT_ERROR_MASSAGE);
        }
        return result;
    }
}

package com.superhonor.shipment.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.superhonor.shipment.result.OperationResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author liuweidong
 */

@Service("authenticationFailHandler")
public class AuthenticationFailHandlerextends extends SimpleUrlAuthenticationFailureHandler {

    @Override

    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception)throws IOException, ServletException {
        this.returnJson(response,exception);

    }

    private void returnJson(HttpServletResponse response,
                            AuthenticationException exception)throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSON.toJSONString(OperationResult.Fail(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage())));
    }

}


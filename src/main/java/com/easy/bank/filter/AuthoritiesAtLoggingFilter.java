package com.easy.bank.filter;

import jakarta.servlet.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class AuthoritiesAtLoggingFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(AuthoritiesAfterLoginFilter.class);

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        logger.info("Authentication validation in progress.");
        chain.doFilter(request, response);
    }
}

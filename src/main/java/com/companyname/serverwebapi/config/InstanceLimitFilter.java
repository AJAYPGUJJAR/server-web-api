package com.companyname.serverwebapi.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class InstanceLimitFilter implements Filter {
    private static final int MAX_INSTANCES = 10;
    private final AtomicInteger activeInstances = new AtomicInteger(0);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (activeInstances.incrementAndGet() > MAX_INSTANCES) {
            activeInstances.decrementAndGet();
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_TEMPORARY_REDIRECT, "Instance limit reached");
            return;
        }
        try {
            chain.doFilter(request, response);
        } finally {
            activeInstances.decrementAndGet();
        }
    }
}

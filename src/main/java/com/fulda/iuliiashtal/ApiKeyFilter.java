package com.fulda.iuliiashtal;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Component
public class ApiKeyFilter extends GenericFilterBean {

    @Value("${app.api.key}")
    private String apiKey;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String method = httpRequest.getMethod();

        // Handle preflight requests
        if ("OPTIONS".equalsIgnoreCase(method)) {
            httpResponse.setHeader("Access-Control-Allow-Origin", "*");
            httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, x-api-key");
            httpResponse.setHeader("Access-Control-Max-Age", "3600");
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            return; // Skip further processing for preflight requests
        }
        String path = httpRequest.getRequestURI();
        if (path.startsWith("/saas")) {
            // Check the x-api-key header for actual requests
            String requestApiKey = httpRequest.getHeader("x-api-key");
            if (!apiKey.equals(requestApiKey)) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().write("Unauthorized");
                return;
            }
        }
        chain.doFilter(request, response);
    }
}

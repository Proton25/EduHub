package com.examly.springapp.config;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * JwtAuthenticationEntryPoint is a class that implements the AuthenticationEntryPoint interface
 * to handle unauthorized access attempts in a Spring Security application.
 * When an authentication exception is thrown, this class is used to commence an authentication scheme.
 * It responds with a 401 Unauthorized status and a custom error message.
 */
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * This method is called when an authentication exception is thrown.
     * It sets the HTTP response status to 401 (Unauthorized) and sends an error message.
     * 
     * @param request The HttpServletRequest object.
     * @param response The HttpServletResponse object.
     * @param authException The AuthenticationException that caused the invocation.
     * @throws IOException if an input or output exception occurs.
     * @throws ServletException if a servlet-specific exception occurs.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.sendError(401, "Unauthorized");
    }
}
package com.springproject.userrepoinfo.interceptor;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MediaTypeInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String acceptHeader = request.getHeader("Accept");
        if (MediaType.APPLICATION_XML_VALUE.equals(acceptHeader)) {
            response.setStatus(HttpServletResponse.SC_NOT_ACCEPTABLE);
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.getWriter().write("{\"status\": 406, \"Message\": \"XML format is not supported.\"}");
            return false;
        }
        return true;
    }
}


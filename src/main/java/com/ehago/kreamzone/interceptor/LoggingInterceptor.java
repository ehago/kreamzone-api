package com.ehago.kreamzone.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@AllArgsConstructor
@Component
public class LoggingInterceptor implements HandlerInterceptor {

    private final ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ContentCachingRequestWrapper wrappingRequest = (ContentCachingRequestWrapper) request;
        log.info("Request URI : " + wrappingRequest.getRequestURI());

        if (wrappingRequest.getContentType() != null && wrappingRequest.getContentType().contains("application/json")) {
            if (wrappingRequest.getContentAsByteArray() != null && wrappingRequest.getContentAsByteArray().length != 0) {
                log.info("Request Body : {}", objectMapper.readTree(wrappingRequest.getContentAsByteArray()));
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        ContentCachingResponseWrapper wrappingResponse = (ContentCachingResponseWrapper) response;
        if (wrappingResponse.getContentType() != null && wrappingResponse.getContentType().contains("application/json")) {
            if (wrappingResponse.getContentAsByteArray() != null && wrappingResponse.getContentAsByteArray().length != 0) {
                log.info("Response Body : {}", objectMapper.readTree(wrappingResponse.getContentAsByteArray()));
            }
        }
    }
}

package com.faderw.stjob.model.spring;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author FaderW
 * 2019/6/4
 */
@Slf4j
public class ReqTraceLogFilter extends OncePerRequestFilter {

    private static final String REQID = "REQID";

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        try {
            String reqId = httpServletResponse.getHeader(REQID);
            if (StringUtils.isEmpty(reqId)) {
                //生成随机请求串
                reqId = RandomStringUtils.randomAlphanumeric(8);
            }
            MDC.put(REQID, reqId);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } finally {
            MDC.clear();
        }
    }
}

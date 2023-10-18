package com.github.jrebel.core.controller;

import com.github.jrebel.core.service.JrebelService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.jrebel.core.util.IPUtil;


@Slf4j
@RestController
public class JrebelController {

    @Autowired
    private JrebelService jrebelService;


    @RequestMapping({"/jrebel/leases"})
    public void jrebelLeases(HttpServletRequest request, HttpServletResponse response) {
        log.info("ip : {} ", IPUtil.getRemoteIp(request) + " -> " + request.getRequestURI());
        jrebelService.jrebelLeasesHandler(request, response);
    }

    @RequestMapping("/jrebel/leases/1")
    public void jrebelLeases1(HttpServletRequest request, HttpServletResponse response) {
        log.info("ip : {} ", IPUtil.getRemoteIp(request) + " -> " + request.getRequestURI());
        jrebelService.jrebelLeases1Handler(request, response);
    }

    @RequestMapping("/agent/leases")
    public void agentLeases(HttpServletRequest request, HttpServletResponse response) {
        log.info("ip : {} ", IPUtil.getRemoteIp(request) + " -> " + request.getRequestURI());
        jrebelService.jrebelLeasesHandler(request, response);
    }

    @RequestMapping("/agent/leases/1")
    public void agentLeases1(HttpServletRequest request, HttpServletResponse response) {
        log.info("ip : {} ", IPUtil.getRemoteIp(request) + " -> " + request.getRequestURI());
        jrebelService.jrebelLeases1Handler(request, response);
    }

    @RequestMapping("/jrebel/validate-connection")
    public void jrebelValidateHandler(HttpServletRequest request, HttpServletResponse response) {
        log.info("ip : {} ", IPUtil.getRemoteIp(request) + " -> " + request.getRequestURI());
        jrebelService.jrebelValidateHandler(request, response);
    }


}

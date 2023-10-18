package com.github.jrebel.core.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

@RestController
public class IndexController {

    @RequestMapping({"/", ""})
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        String licenseUrl =   System.getenv().getOrDefault("licenseUrl","//" + request.getServerName() + (request.getServerPort() == 80 ? "" : ":" + request.getServerPort()));
        return new ModelAndView("index", new HashMap<String, Object>() {{
            put("licenseUrl", licenseUrl);
            put("uuid", UUID.randomUUID().toString());
        }});
    }



}

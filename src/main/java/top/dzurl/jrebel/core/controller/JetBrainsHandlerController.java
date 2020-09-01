package top.dzurl.jrebel.core.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.dzurl.jrebel.core.service.JetBrainsService;
import top.dzurl.jrebel.core.util.IPUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
@RestController
public class JetBrainsHandlerController {

    @Autowired
    private JetBrainsService jrebelLeasesHandler;


    @RequestMapping("/rpc/ping.action")
    public void pingHandler(HttpServletRequest request, HttpServletResponse response) {
        log.info("ip : {} ", IPUtil.getRemoteIp(request) + " -> " + request.getRequestURI());
        jrebelLeasesHandler.pingHandler(request, response);
    }

    @RequestMapping("/rpc/obtainTicket.action")
    public void obtainTicketHandler(HttpServletRequest request, HttpServletResponse response) {
        log.info("ip : {} ", IPUtil.getRemoteIp(request) + " -> " + request.getRequestURI());
        jrebelLeasesHandler.obtainTicketHandler(request, response);
    }

    @RequestMapping("/rpc/releaseTicket.action")
    public void releaseTicketHandler(HttpServletRequest request, HttpServletResponse response) {
        log.info("ip : {} ", IPUtil.getRemoteIp(request) + " -> " + request.getRequestURI());
        jrebelLeasesHandler.releaseTicketHandler(request, response);
    }

    @SneakyThrows
    @RequestMapping("/guid")
    public void guid(HttpServletRequest request, HttpServletResponse response) {
        log.info("ip : {} ", IPUtil.getRemoteIp(request) + " -> " + request.getRequestURI());
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        String body = UUID.randomUUID().toString();
        response.getWriter().print(body);
    }

}

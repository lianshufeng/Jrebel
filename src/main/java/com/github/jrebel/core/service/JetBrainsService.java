package com.github.jrebel.core.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import com.github.jrebel.core.util.RsaSign;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Service
public class JetBrainsService {
    @SneakyThrows
    public void releaseTicketHandler(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        String salt = request.getParameter("salt");
        if (salt == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {

            String xmlContent = """
                       <ReleaseTicketResponse>
                            <message></message>
                            <responseCode>OK</responseCode>
                            <salt>%s</salt>
                        </ReleaseTicketResponse>
                    """.formatted(salt);
            String xmlSignature = RsaSign.Sign(xmlContent);
            String body = "<!-- " + xmlSignature + " -->\n" + xmlContent;
            response.getWriter().print(body);
        }
    }


    @SneakyThrows
    public void pingHandler(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        String salt = request.getParameter("salt");
        if (salt == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            String xmlContent = """
                    <PingResponse>
                        <message></message>
                        <responseCode>OK</responseCode>
                        <salt>%s</salt>
                    </PingResponse>
                    """.formatted(salt);
            String xmlSignature = RsaSign.Sign(xmlContent);
            String body = "<!-- " + xmlSignature + " -->\n" + xmlContent;
            response.getWriter().print(body);
        }

    }

    @SneakyThrows
    public void obtainTicketHandler(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        SimpleDateFormat fm = new SimpleDateFormat("EEE,d MMM yyyy hh:mm:ss Z", Locale.ENGLISH);
        String date = fm.format(new Date()) + " GMT";
        //response.setHeader("Date", date);
        //response.setHeader("Server", "fasthttp");
        String salt = request.getParameter("salt");
        String username = request.getParameter("userName");
        String prolongationPeriod = "607875500";
        if (salt == null || username == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {

            String xmlContent = """
                        <ObtainTicketResponse>
                            <message></message>
                            <prolongationPeriod>%s</prolongationPeriod>
                            <responseCode>OK</responseCode>
                            <salt>%s</salt>
                            <ticketId>1</ticketId>
                            <ticketProperties>licensee=%s licenseType=0</ticketProperties>
                        </ObtainTicketResponse>
                    """.formatted(prolongationPeriod, salt, username);
            String xmlSignature = RsaSign.Sign(xmlContent);
            String body = "<!-- " + xmlSignature + " -->\n" + xmlContent;
            response.getWriter().print(body);
        }
    }
}

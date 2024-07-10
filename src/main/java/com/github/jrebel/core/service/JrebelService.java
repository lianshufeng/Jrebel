package com.github.jrebel.core.service;

import com.github.jrebel.core.util.jrebel.JrebelSign;
import com.github.jrebel.core.util.JsonUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JrebelService {


    @SneakyThrows
    public void jrebelLeasesHandler(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        String clientRandomness = request.getParameter("randomness");
        String username = request.getParameter("username");
        String guid = request.getParameter("guid");
//        boolean offline = Boolean.parseBoolean(request.getParameter("offline"));
        final boolean offline = true;
        String validFrom = "null";
        String validUntil = "null";
        if (offline) {
            String clientTime = request.getParameter("clientTime");
            String offlineDays = request.getParameter("offlineDays");
            //long clinetTimeUntil = Long.parseLong(clientTime) + Long.parseLong(offlineDays)  * 24 * 60 * 60 * 1000;
            long clinetTimeUntil = Long.parseLong(clientTime) + 30L * 24 * 60 * 60 * 1000;
            validFrom = clientTime;
            validUntil = String.valueOf(clinetTimeUntil);
        }
        String jsonStr = """
                {
                        "serverVersion": "3.2.4",
                        "serverProtocolVersion": "1.1",
                        "serverGuid": "a1b4aea8-b031-4302-b602-670a990272cb",
                        "groupType": "managed",
                        "id": 1,
                        "licenseType": 1,
                        "evaluationLicense": false,
                        "signature": "OJE9wGg2xncSb+VgnYT+9HGCFaLOk28tneMFhCbpVMKoC/Iq4LuaDKPirBjG4o394/UjCDGgTBpIrzcXNPdVxVr8PnQzpy7ZSToGO8wv/KIWZT9/ba7bDbA8/RZ4B37YkCeXhjaixpmoyz/CIZMnei4q7oWR7DYUOlOcEWDQhiY=",
                        "serverRandomness": "H2ulzLlh7E0=",
                        "seatPoolType": "standalone",
                        "statusCode": "SUCCESS",
                        "offline": %s,
                        "validFrom": %s,
                        "validUntil": %s,
                        "company": "Administrator",
                        "orderId": "",
                        "zeroIds": [
                           \s
                        ],
                        "licenseValidFrom": 1490544001000,
                        "licenseValidUntil": %s
                    }
                """.formatted(offline, validFrom, validUntil, System.currentTimeMillis() );

        if (clientRandomness == null || username == null || guid == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        } else {
            Map map = JsonUtil.toObject(jsonStr, Map.class);
            JrebelSign jrebelSign = new JrebelSign();
            jrebelSign.toLeaseCreateJson(clientRandomness, guid, offline, validFrom, validUntil);
            String signature = jrebelSign.getSignature();
            map.put("signature", signature);
            map.put("company", username);
            String body = JsonUtil.toJson(map);
            response.getWriter().print(body);
        }
    }


    @SneakyThrows
    public void jrebelValidateHandler(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        String jsonStr = """
                {
                    "serverVersion": "3.2.4",
                    "serverProtocolVersion": "1.1",
                    "serverGuid": "a1b4aea8-b031-4302-b602-670a990272cb",
                    "groupType": "managed",
                    "statusCode": "SUCCESS",
                    "company": "Administrator",
                    "canGetLease": true,
                    "licenseType": 1,
                    "evaluationLicense": false,
                    "seatPoolType": "standalone"
                }
                """;
        response.getWriter().print(JsonUtil.toJson(JsonUtil.toObject(jsonStr, Object.class)));
    }

    @SneakyThrows
    public void jrebelLeases1Handler(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
        String username = request.getParameter("username");
        String jsonStr = """
                {
                    "serverVersion": "3.2.4",
                    "serverProtocolVersion": "1.1",
                    "serverGuid": "a1b4aea8-b031-4302-b602-670a990272cb",
                    "groupType": "managed",
                    "statusCode": "SUCCESS",
                    "msg": null,
                    "statusMessage": null
                }
                """;
        Map map = JsonUtil.toObject(jsonStr, Map.class);
        if (username != null) {
            map.put("company", username);
        }
        String body = JsonUtil.toJson(map);
        response.getWriter().print(body);
    }


}

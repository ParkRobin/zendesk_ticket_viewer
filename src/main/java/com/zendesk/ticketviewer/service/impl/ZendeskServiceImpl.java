package com.zendesk.ticketviewer.service.impl;

import com.zendesk.ticketviewer.dao.Ticket;
import com.zendesk.ticketviewer.service.ZendeskService;
import com.zendesk.ticketviewer.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class ZendeskServiceImpl implements ZendeskService {

    private final static String LIST_REQUEST = "api/v2/requests";
    private final static String SHOW_REQUEST = "api/v2/requests/%s";
    private final static String USER_REQUEST = "api/v2/users/%s";

    @Value("${zendesk.server-url}")
    private String serverUrl;

    private final OkHttpClient.Builder client;
    {
        client = new OkHttpClient.Builder();
        client.authenticator(new Authenticator() {
            @Nullable
            public Request authenticate(@Nullable Route route, @NotNull Response response) throws IOException {
                if (responseCount(response) >= 3) {
                    return null; // If we've failed 3 times, give up. - in real life, never give up!!
                }
                String credential = Credentials.basic("lpiao@ncsu.edu", "123456");
                return response.request().newBuilder().header("Authorization", credential).build();
            }
        });
    }

    private int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }

    public List<Ticket> getTicketList(){
        Request request = new Request.Builder()
                .url(serverUrl + LIST_REQUEST)
                .header("Content-Type", "application/json")
                .build();
        Call call = client.build().newCall(request);
        try {
            Response response = call.execute();
            if(response.isSuccessful()){
                String jsonString = response.body().string();
                String str = JsonUtils.toJsonNode(jsonString).get("requests").toString();
                return JsonUtils.fromJson(str, Ticket.class);
            }
        } catch (Exception e) {
            log.error("get ticket list error.", e);
        }
        return null;
    }

    public String getUserName(String id) {
        Request request = new Request.Builder()
                .url(serverUrl + String.format(USER_REQUEST, id))
                .header("Content-Type", "application/json")
                .build();
        Call call = client.build().newCall(request);
        try {
            Response response = call.execute();
            if(response.isSuccessful()){
                String jsonString = response.body().string();
                String userName = JsonUtils.toJsonNode(jsonString).get("user").get("name").textValue();
                return userName;
            }
        } catch (Exception e) {
            log.error("get user name error.", e);
        }
        return null;
    }

    public Ticket getTicket(int id) {
        Request request = new Request.Builder()
                .url(serverUrl + String.format(SHOW_REQUEST, id))
                .header("Content-Type", "application/json")
                .build();
        Call call = client.build().newCall(request);
        try {
            Response response = call.execute();
            if(response.isSuccessful()){
                String jsonString = response.body().string();
                String str = JsonUtils.toJsonNode(jsonString).get("request").toString();
                return JsonUtils.toBean(str, Ticket.class);
            }
        } catch (Exception e) {
            log.error("get ticket error.", e);
        }
        return null;
    }

}

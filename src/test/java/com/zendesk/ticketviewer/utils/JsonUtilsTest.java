package com.zendesk.ticketviewer.utils;

import com.zendesk.ticketviewer.dao.Ticket;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class JsonUtilsTest {

    @Test
    public void toJsonTest(){
        Ticket ticket = new Ticket("www.zendesk.com", 1, "Thu, 25 Nov 2021 15:55:38 GMT" , "Thu, 25 Nov 2021 15:55:38 GMT", "normal", "" ,"", "xxx", "", "open", "123");
        String jsonStr = JsonUtils.toJson(ticket);
        String expected = "{\"url\":\"www.zendesk.com\",\"id\":1,\"created_at\":\"Thu, 25 Nov 2021 15:55:38 GMT\",\"updated_at\":\"Thu, 25 Nov 2021 15:55:38 GMT\",\"type\":\"normal\",\"subject\":\"\",\"raw_subject\":\"\",\"description\":\"xxx\",\"priority\":\"\",\"status\":\"open\",\"requester_id\":\"123\"}";
        try{
            Assertions.assertEquals(expected, jsonStr);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void toBeanTest(){
        String jsonStr = "{\"url\":\"www.zendesk.com\",\"id\":1,\"created_at\":\"Thu, 25 Nov 2021 15:55:38 GMT\",\"updated_at\":\"Thu, 25 Nov 2021 15:55:38 GMT\",\"type\":\"normal\",\"subject\":\"\",\"raw_subject\":\"\",\"description\":\"xxx\",\"priority\":\"\",\"status\":\"open\",\"requester_id\":\"123\"}";
        Ticket expected = new Ticket("www.zendesk.com", 1, "Thu, 25 Nov 2021 15:55:38 GMT" , "Thu, 25 Nov 2021 15:55:38 GMT", "normal", "" ,"", "xxx", "", "open", "123");
        Ticket ticket = JsonUtils.toBean(jsonStr, Ticket.class);
        try{
            Assertions.assertEquals(expected, ticket);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void fromJsonTest(){
        String jsonStr = "[{\"url\":\"www.zendesk.com\",\"id\":1,\"created_at\":\"Thu, 25 Nov 2021 15:55:38 GMT\",\"updated_at\":\"Thu, 25 Nov 2021 15:55:38 GMT\",\"type\":\"normal\",\"subject\":\"\",\"raw_subject\":\"\",\"description\":\"xxx\",\"priority\":\"\",\"status\":\"open\",\"requester_id\":\"123\"},{\"url\":\"www.zendesk.com\",\"id\":2,\"created_at\":\"Thu, 25 Nov 2021 15:58:38 GMT\",\"updated_at\":\"Thu, 25 Nov 2021 15:58:38 GMT\",\"type\":\"normal\",\"subject\":\"\",\"raw_subject\":\"\",\"description\":\"xxx\",\"priority\":\"\",\"status\":\"open\",\"requester_id\":\"456\"}]";
        List<Ticket> tickets = JsonUtils.fromJson(jsonStr, Ticket.class);
        List<Ticket> expected = new ArrayList<Ticket>();
        expected.add(new Ticket("www.zendesk.com", 1, "Thu, 25 Nov 2021 15:55:38 GMT" , "Thu, 25 Nov 2021 15:55:38 GMT", "normal", "" ,"", "xxx", "", "open", "123"));
        expected.add(new Ticket("www.zendesk.com", 2, "Thu, 25 Nov 2021 15:58:38 GMT" , "Thu, 25 Nov 2021 15:58:38 GMT", "normal", "" ,"", "xxx", "", "open", "456"));
        try{
            Assertions.assertEquals(expected, tickets);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

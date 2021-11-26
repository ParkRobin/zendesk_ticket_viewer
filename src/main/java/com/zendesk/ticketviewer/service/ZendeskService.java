package com.zendesk.ticketviewer.service;

import com.zendesk.ticketviewer.dao.Ticket;

import java.util.List;

public interface ZendeskService {

    Ticket getTicket(int id);

    List<Ticket> getTicketList();

    String getUserName(String id);
}

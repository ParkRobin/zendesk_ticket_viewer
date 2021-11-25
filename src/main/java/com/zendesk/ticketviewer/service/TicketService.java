package com.zendesk.ticketviewer.service;

import com.zendesk.ticketviewer.dao.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> getTicketList();

    Ticket getTicket(int id);
}

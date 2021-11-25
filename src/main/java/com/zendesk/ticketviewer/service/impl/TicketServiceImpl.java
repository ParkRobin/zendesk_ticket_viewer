package com.zendesk.ticketviewer.service.impl;

import com.zendesk.ticketviewer.dao.Ticket;
import com.zendesk.ticketviewer.service.TicketService;
import com.zendesk.ticketviewer.service.ZendeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private ZendeskService zendeskService;

    public List<Ticket> getTicketList() {
        return zendeskService.getTicketList();
    }

    public Ticket getTicket(int id) {
        return zendeskService.getTicket(id);
    }
}

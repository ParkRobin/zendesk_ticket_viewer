package com.zendesk.ticketviewer.web.controller;

import com.zendesk.ticketviewer.dao.Ticket;
import com.zendesk.ticketviewer.service.TicketService;
import com.zendesk.ticketviewer.web.response.ResponseData;
import com.zendesk.ticketviewer.web.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static com.zendesk.ticketviewer.global.exception.TicketViewException.INVALID_ARGUMENT;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private TicketService ticketService;

    @CrossOrigin(origins = "*")
    @GetMapping("")
    public ResponseData<Ticket> getTicket(int id){
        Ticket ticket = ticketService.getTicket(id);
        if(ticket == null){
            return ResponseUtil.error(INVALID_ARGUMENT, request);
        }else{
            return ResponseUtil.ok(ticket, request);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/list")
    public ResponseData<List<Ticket>> getTicketList(){
        List<Ticket> ticketList = ticketService.getTicketList();
        if(ticketList == null){
            return ResponseUtil.error(INVALID_ARGUMENT, request);
        }else{
            return ResponseUtil.ok(ticketList, request);
        }
    }
}

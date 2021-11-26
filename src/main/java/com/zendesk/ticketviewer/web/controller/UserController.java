package com.zendesk.ticketviewer.web.controller;

import com.zendesk.ticketviewer.service.UserService;
import com.zendesk.ticketviewer.web.response.ResponseData;
import com.zendesk.ticketviewer.web.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static com.zendesk.ticketviewer.global.exception.TicketViewException.INVALID_ARGUMENT;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserService userService;

    @CrossOrigin(origins = "*")
    @GetMapping("/name")
    public ResponseData<String> getUserName(String id){
        String userName = userService.getUserName(id);
        if(userName == null){
            return ResponseUtil.error(INVALID_ARGUMENT, request);
        }else{
            return ResponseUtil.ok(userName, request);
        }
    }
}

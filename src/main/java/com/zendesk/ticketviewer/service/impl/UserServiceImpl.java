package com.zendesk.ticketviewer.service.impl;

import com.zendesk.ticketviewer.service.UserService;
import com.zendesk.ticketviewer.service.ZendeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ZendeskService zendeskService;

    public String getUserName(String id) {
        return zendeskService.getUserName(id);
    }
}

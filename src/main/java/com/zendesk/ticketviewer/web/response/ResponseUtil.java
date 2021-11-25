package com.zendesk.ticketviewer.web.response;


import com.zendesk.ticketviewer.global.exception.TicketViewException;

import javax.servlet.http.HttpServletRequest;

public class ResponseUtil {

    public static <T> ResponseData<T> ok(T data, HttpServletRequest request) {
        return new ResponseData(new MetaData(TicketViewException.SUCCESS.getCode(),
                TicketViewException.SUCCESS.getMessage(), request.getMethod(), request.getRequestURI()), data);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> ResponseData<T> error(TicketViewException exception, HttpServletRequest request) {
        return new ResponseData(new MetaData(exception.getCode(), exception.getMessage(),
                request.getMethod(), request.getRequestURI()), VoidData.VOID);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static <T> ResponseData<T> error(int errNum, String errMsg, HttpServletRequest request) {
        return new ResponseData(
                new MetaData(errNum, errMsg, request.getMethod(), request.getRequestURI()),
                VoidData.VOID);
    }
}

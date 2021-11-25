package com.zendesk.ticketviewer.global.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TicketViewException extends Exception{

    private int code;
    private String message;

    public static final TicketViewException SUCCESS = new TicketViewException(200, "SUCCESS");
    public static final TicketViewException INVALID_ARGUMENT = new TicketViewException(400, "Bad Request");
    public static final TicketViewException NO_FILE = new TicketViewException(404,"Page Not Found");
    public static final TicketViewException OPERATION_FAILED = new TicketViewException(405, "Method Not Allowed");
}

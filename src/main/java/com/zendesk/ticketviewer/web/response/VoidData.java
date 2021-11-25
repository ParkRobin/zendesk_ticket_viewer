package com.zendesk.ticketviewer.web.response;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonSerialize
public class VoidData implements Serializable {
    private static final long serialVersionUID = -5135830372473351385L;

    public static final VoidData VOID = new VoidData();
}

package com.zendesk.ticketviewer.web.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MetaData implements Serializable {

    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "error code", required = true, position = 1)
    private int errNum;
    @ApiModelProperty(value = "error message", position = 2)
    private String errMsg;
    @ApiModelProperty(value = "request method", position = 3)
    private String requestMethod;
    @ApiModelProperty(value = "request URI", position = 4)
    private String requestURI;
}

package com.zendesk.ticketviewer.web.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@ApiModel("response data")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class ResponseData<T> implements Serializable {

    @ApiModelProperty(value = "response state", required = true, position = 1)
    private MetaData meta;

    @ApiModelProperty(value = "response result", position = 2)
    private T result;
}

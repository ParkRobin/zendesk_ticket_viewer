package com.zendesk.ticketviewer.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @JsonProperty(value = "url")
    private String url;

    @JsonProperty(value = "id")
    private int id;

    @JsonProperty(value = "created_at")
    private String createdAt;

    @JsonProperty(value = "updated_at")
    private String updatedAt;

    @JsonProperty(value = "type")
    private String type;

    @JsonProperty(value = "subject")
    private String subject;

    @JsonProperty(value = "raw_subject")
    private String rawSubject;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "priority")
    private String priority;

    @JsonProperty(value = "status")
    private String status;

    @JsonProperty(value = "requester_id")
    private String requesterId;

}

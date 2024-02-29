package com.demo.dtos;

import java.util.Date;

public class ComplainIdDTO {

    private Integer id;
    private int sender;
    private Integer handler;
    private Date dateSent;
    private Date settlementDate;
    private String title;
    private String content;
    private Boolean status;

    public ComplainIdDTO() {
    }

    public ComplainIdDTO(Integer id, int sender, Integer handler, Date dateSent, Date settlementDate, String title,
                         String content, Boolean status) {
        this.id = id;
        this.sender = sender;
        this.handler = handler;
        this.dateSent = dateSent;
        this.settlementDate = settlementDate;
        this.title = title;
        this.content = content;
        this.status = status;
    }

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public Integer getHandler() {
        return handler;
    }

    public void setHandler(Integer handler) {
        this.handler = handler;
    }

    public Date getDateSent() {
        return dateSent;
    }

    public void setDateSent(Date dateSent) {
        this.dateSent = dateSent;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

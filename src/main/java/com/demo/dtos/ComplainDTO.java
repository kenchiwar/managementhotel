package com.demo.dtos;

import java.util.Date;

public class ComplainDTO {

    private Integer id;
    private Integer senderAccountId;
    private Integer handlerAccountId;
    private Date dateSent;
    private Date settlementDate;
    private String title;
    private String content;
    private String status;

    public ComplainDTO() {
    }

    public ComplainDTO(Integer id, Integer senderAccountId, Integer handlerAccountId, Date dateSent, Date settlementDate,
                       String title, String content, String status) {
        this.id = id;
        this.senderAccountId = senderAccountId;
        this.handlerAccountId = handlerAccountId;
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

    public Integer getSenderAccountId() {
        return senderAccountId;
    }

    public void setSenderAccountId(Integer senderAccountId) {
        this.senderAccountId = senderAccountId;
    }

    public Integer getHandlerAccountId() {
        return handlerAccountId;
    }

    public void setHandlerAccountId(Integer handlerAccountId) {
        this.handlerAccountId = handlerAccountId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

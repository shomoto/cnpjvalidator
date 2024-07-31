package com.example.CNPJValidator.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Date;

@Entity
public class HistoryRequest implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    private CNPJ cnpj;
    private String method;

    @Column(length = 5000)
    private String reply;

    public Date getDate() {
        return date;
    }

    private Date date;

    public HistoryRequest() {
        this.date=new Date();
    }

    public CNPJ getCnpj() {
        return cnpj;
    }

    public String getMethod() {
        return method;
    }

    public String getReply() {
        return reply;
    }


    public void setCnpj(CNPJ cnpj) {
        this.cnpj = cnpj;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setReply(Object reply) {
        this.reply = reply.toString();
    }
}

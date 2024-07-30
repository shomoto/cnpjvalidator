package com.example.CNPJValidator.entities;

public class HistoryRequest {

    private CNPJ cnpj;
    private String method;

    private Object reply;

    public void setCnpj(CNPJ cnpj) {
        this.cnpj = cnpj;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setReply(Object reply) {
        this.reply = reply;
    }
}

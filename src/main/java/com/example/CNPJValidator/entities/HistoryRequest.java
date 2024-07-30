package com.example.CNPJValidator.entities;



public class HistoryRequest {

    private CNPJ cnpj;
    private String method;

    public CNPJ getCnpj() {
        return cnpj;
    }

    public String getMethod() {
        return method;
    }

    public Object getReply() {
        return reply;
    }

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

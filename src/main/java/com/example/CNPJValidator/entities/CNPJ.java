package com.example.CNPJValidator.entities;

public class CNPJ {

    Integer raiz;
    Integer ordem;

    public Integer getRaiz() {
        return raiz;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public Integer getDv() {
        return dv;
    }

    Integer dv;

    public CNPJ(String cnpj) {


        raiz = this.getRaizFromString(cnpj);
        ordem = this.getOrdemFromString(cnpj);
        dv = this.getDVFromString(cnpj);

    }

    private Integer getDVFromString(String cnpj) {

        System.out.println("Set DV " + cnpj.substring(16,18));
        return Integer.parseInt (cnpj.substring(16,18));
        // 43.017.196/0001-30
    }

    private Integer getOrdemFromString(String cnpj) {

        System.out.println("Set Ordem "+ cnpj.substring(11,15));
        return Integer.parseInt (cnpj.substring(11,15));
        
    }

    private Integer getRaizFromString(String cnpj) {

        System.out.println("Set Raiz " + cnpj.substring(0,10));
        return Integer.parseInt (cnpj.substring(0,10).replace(".", ""));
        
    }
}

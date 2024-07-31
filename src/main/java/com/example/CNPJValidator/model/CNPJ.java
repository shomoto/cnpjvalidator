package com.example.CNPJValidator.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.io.Serializable;

@Entity
public class CNPJ implements Serializable {



    @Id
    @GeneratedValue
    private int id;
    private int raiz;
    private int ordem;


    public int getRaiz() {
        return raiz;
    }

    public int getOrdem() {
        return ordem;
    }

    public int getDv() {
        return dv;
    }

    int dv;

    public CNPJ(String cnpj) {



        raiz = this.getRaizFromString(cnpj);
        ordem = this.getOrdemFromString(cnpj);
        dv = this.getDVFromString(cnpj);



    }

    private int getDVFromString(String cnpj) {


        return Integer.parseInt (cnpj.substring(16,18));
        // 43.017.196/0001-30
    }

    private int getOrdemFromString(String cnpj) {


        return Integer.parseInt (cnpj.substring(11,15));
        
    }

    private int getRaizFromString(String cnpj) {


        return Integer.parseInt (cnpj.substring(0,10).replace(".", ""));
        
    }
}

package com.example.CNPJValidator.consumers;


import org.springframework.web.client.RestTemplate;

public class ReceitaConsumer {


    public Object getCnpjDetail(String cnpj) {


        RestTemplate restTemplate = new RestTemplate();
        String url = "https://publica.cnpj.ws/cnpj/" + cnpj;
        System.out.println(url);
        return  restTemplate.getForObject(url, Object.class);

    }
}


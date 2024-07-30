package com.example.CNPJValidator.services;

import br.com.safeguard.check.SafeguardCheck;
import br.com.safeguard.interfaces.Check;
import br.com.safeguard.types.ParametroTipo;
import com.example.CNPJValidator.entities.CNPJ;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ValidationControler {

    List<CNPJ> history = new ArrayList<>();

    @GetMapping("/validate")
    public String validate(@RequestParam(value = "cnpj") String cnpj , HttpServletResponse response) {

        if(cnpj.isEmpty()) {
            //do anything
            response.setStatus(400);
            return "Provided CNPJ value is empty";
        }

        CNPJ cnpjInstance = new CNPJ(cnpj);
        history.add(cnpjInstance);

        Check check = new SafeguardCheck();

        Boolean hasErrors;

        hasErrors =  check
                .elementOf(cnpj, ParametroTipo.CNPJ)
                .validate()
                .hasError();

        if(!hasErrors) {
            //do anything
            response.setStatus(200);
            return "Valid CNPJ";
        }else {

            response.setStatus(401);
            return "Invalid CNPJ";
        }

    }

    @GetMapping("/decompose")
    public String decompose(@RequestParam(value = "cnpj") String cnpj )
    {
        System.out.println("Entering method Decompose");
        CNPJ cnpjInstance = new CNPJ(cnpj);
        history.add(cnpjInstance);

        return "CNPJ composto por Raiz: "
                + cnpjInstance.getRaiz()
                + " Ordem: " + cnpjInstance.getOrdem()
                + " DV: " + cnpjInstance.getDv()
                ;
    }

    @GetMapping ("/history")
    public List<CNPJ> history ()
    {
        return history;
    }

}

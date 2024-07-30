package com.example.CNPJValidator;

import br.com.safeguard.check.SafeguardCheck;
import br.com.safeguard.interfaces.Check;
import br.com.safeguard.types.ParametroTipo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ValidationControler {

    @GetMapping("/validate")
    public String validate(@RequestParam(value = "cnpj") String cnpj , HttpServletResponse response) {

        if(cnpj.length()==0) {
            //do anything
            response.setStatus(400);
            return "Provided CNPJ value is empty";
        }

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

}

package com.example.CNPJValidator.services;

import br.com.safeguard.check.SafeguardCheck;
import br.com.safeguard.interfaces.Check;
import br.com.safeguard.types.ParametroTipo;
import com.example.CNPJValidator.consumers.ReceitaConsumer;
import com.example.CNPJValidator.entities.CNPJ;
import com.example.CNPJValidator.entities.HistoryRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class ValidationController {

    List<HistoryRequest> history = new ArrayList<>();

    @GetMapping("/validate")
    public String validate(@RequestParam(value = "cnpj") String cnpj , HttpServletResponse response) {

        String reply;

        Check check = new SafeguardCheck();

        Boolean hasErrors;

        hasErrors =  check
                .elementOf(cnpj, ParametroTipo.CNPJ)
                .validate()
                .hasError();

        if(cnpj.isEmpty()) {
            //do anything
            response.setStatus(400);
            reply = "Provided CNPJ value is empty";
        } else if (!hasErrors) {
            //do anything
            response.setStatus(200);
            reply = "Valid CNPJ";
        }else {
            response.setStatus(401);
            reply = "Invalid CNPJ";
        }

        saveHistory(cnpj, new Object(){}.getClass().getEnclosingMethod().getName(), reply);

        return reply;

    }

    private void saveHistory(String cnpj, String method, Object result) {

        HistoryRequest histReq = new HistoryRequest();
        histReq.setCnpj(new CNPJ(cnpj));
        histReq.setMethod(method);
        histReq.setReply(result);
        history.add(histReq);

    }

    private void saveHistory(CNPJ cnpj, String method, Object result) {

        HistoryRequest histReq = new HistoryRequest();
        histReq.setCnpj(cnpj);
        histReq.setMethod(method);
        histReq.setReply(result);
        history.add(histReq);

    }

    @GetMapping("/decompose")
    public String decompose(@RequestParam(value = "cnpj") String cnpj )
    {
        String reply;


        CNPJ cnpjInstance = new CNPJ(cnpj);


        reply=  "CNPJ composto por Raiz: "
                + cnpjInstance.getRaiz()
                + " Ordem: " + cnpjInstance.getOrdem()
                + " DV: " + cnpjInstance.getDv()
                ;

        saveHistory(cnpjInstance, new Object(){}.getClass().getEnclosingMethod().getName(), reply);

        return reply;
    }

    @GetMapping("/describe")

    public Object describe(@RequestParam(value = "cnpj") String cnpj )
    {
        Object reply;

        String cnpjNoSpecialChars = removeCnpjSpecialChars(cnpj);
        reply = new ReceitaConsumer().getCnpjDetail(cnpjNoSpecialChars);
        saveHistory(cnpj, new Object(){}.getClass().getEnclosingMethod().getName(), reply);

        return reply;
    }



    @GetMapping ("/history")
    public List<HistoryRequest> history ()
    {
        return history;
    }

    private String removeCnpjSpecialChars(String cnpj) {

        return cnpj.replace("/","").replace(".","").replace("-","");

    }

}

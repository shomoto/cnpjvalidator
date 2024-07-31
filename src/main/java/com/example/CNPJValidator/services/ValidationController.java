package com.example.CNPJValidator.services;

import br.com.safeguard.check.SafeguardCheck;
import br.com.safeguard.interfaces.Check;
import br.com.safeguard.types.ParametroTipo;
import com.example.CNPJValidator.consumers.ReceitaConsumer;
import com.example.CNPJValidator.model.CNPJ;
import com.example.CNPJValidator.model.HistoryRequest;
import com.example.CNPJValidator.repository.HistoryRequestRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public  class ValidationController {

    //private List<HistoryRequest> history = new ArrayList<>();
    //private final History history = new History();

    @Autowired
    private HistoryRequestRepository historyRequestRepository;

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

        saveHistory(new CNPJ(cnpj),method,result);

    }

    private void saveHistory(CNPJ cnpj, String method, Object result) {

        HistoryRequest histReq = new HistoryRequest();
        histReq.setCnpj(cnpj);
        histReq.setMethod(method);
        histReq.setReply(result);
        historyRequestRepository.save(histReq);


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
    public Object history ()
    {
        return historyRequestRepository.findAll();
    }

    private String removeCnpjSpecialChars(String cnpj) {

        return cnpj.replace("/","").replace(".","").replace("-","");

    }

}

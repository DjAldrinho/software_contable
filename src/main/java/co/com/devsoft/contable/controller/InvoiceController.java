package co.com.devsoft.contable.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InvoiceController {


    @GetMapping("/")
    public String index() {
       return  "index";
    }

}

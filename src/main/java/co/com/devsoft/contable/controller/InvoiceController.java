package co.com.devsoft.contable.controller;

import co.com.devsoft.contable.models.Invoice;
import co.com.devsoft.contable.services.InvoiceService;
import co.com.devsoft.contable.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String index(Model model) {
       model.addAttribute("inv", new Invoice());
       model.addAttribute("selectProducts", productService.getProductList());
       return  "index";
    }

    @RequestMapping(path = "/createInvoice", method = RequestMethod.POST)
    @ResponseBody
    public String saveInvoice(Invoice invoice)
    {
        try {
            Invoice invoice1 = service.save(invoice);
            return "Exito: " +  invoice1.getInvoice();
        } catch (Exception e){
            return "Error: " + e.getMessage();
        }
    }

}

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
        model.addAttribute("invoiceList", service.findAll());
        return  "index";
    }

    @GetMapping("/create")
    public String createInvoice(Model model) {
       model.addAttribute("inv", new Invoice());
       model.addAttribute("selectProducts", productService.getProductList());
       return  "create-invoice";
    }

    @RequestMapping(path = "/createInvoice", method = RequestMethod.POST)
    public String saveInvoice(Invoice invoice, Model model)
    {
        String message;

        try {
            System.out.println(invoice.getProductsList());
            Invoice invoice1 = service.save(invoice);
            message = "Factura #" +
                    invoice1.getInvoice() + " creado con exito!";
        } catch (Exception e){
            message = "Error: " + e.getMessage();
        }

        model.addAttribute("message", message);

        return "result";
    }

}

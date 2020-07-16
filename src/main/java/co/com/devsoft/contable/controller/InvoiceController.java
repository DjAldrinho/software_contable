package co.com.devsoft.contable.controller;

import co.com.devsoft.contable.models.Invoice;
import co.com.devsoft.contable.services.InvoiceService;
import co.com.devsoft.contable.services.ProductService;
import co.com.devsoft.contable.utils.PdfReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.ByteArrayInputStream;
import java.util.List;

@Controller
public class InvoiceController {

    @Autowired
    private InvoiceService service;

    @Autowired
    private ProductService productService;

    /** Despachar index con lista de facturas */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("invoiceList", service.findAll());
        return  "index";
    }

    /** Depachar pagina para creacion de factura */
    @GetMapping("/create")
    public String createInvoice(Model model) {
       model.addAttribute("inv", new Invoice());
       model.addAttribute("selectProducts", productService.getProductList());
       return  "create-invoice";
    }

    /** Metodo para recibir la peticion y realizar la insercion */
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


    /** Metodo para generar reporte */
    @RequestMapping(value = "/invoiceReport", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> invoiceReport() {

        List<Invoice> invoices = service.findAll();

        ByteArrayInputStream bis = PdfReport.invoicesReport(invoices);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition", "inline; filename=invoiceReport.pdf");

        return ResponseEntity
                .ok()
                .headers(httpHeaders)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

}

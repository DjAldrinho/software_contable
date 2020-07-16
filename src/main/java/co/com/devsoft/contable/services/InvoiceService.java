package co.com.devsoft.contable.services;

import co.com.devsoft.contable.models.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    /** Listar factura */
    public List<Invoice> findAll(){
        return (List<Invoice>) invoiceRepository.findAll();
    }

    /** Insertar factura */
    public Invoice save(Invoice entity){
        entity = invoiceRepository.save(entity);

        return entity;
    }

}

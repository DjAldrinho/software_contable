package co.com.devsoft.contable.services;

import co.com.devsoft.contable.models.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice save(Invoice entity){
        entity = invoiceRepository.save(entity);

        return entity;
    }

}

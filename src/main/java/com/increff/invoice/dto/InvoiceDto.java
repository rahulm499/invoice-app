package com.increff.invoice.dto;


import com.increff.invoice.invoice.PdfGenerator;
import com.increff.invoice.model.InvoiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InvoiceDto {

    @Autowired
    private PdfGenerator pdfGenerator;
    public String generatePdf(InvoiceData invoiceData) throws Exception {
        return pdfGenerator.generatePdf(invoiceData);
    }
}

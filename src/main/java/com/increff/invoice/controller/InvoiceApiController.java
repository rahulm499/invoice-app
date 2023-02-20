package com.increff.invoice.controller;

import com.increff.invoice.dto.InvoiceDto;
import com.increff.invoice.model.InvoiceData;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class InvoiceApiController {
    @Autowired
    private InvoiceDto dto;

    @ApiOperation(value= "Generates an invoice")
    @RequestMapping(path = "/api/generate-invoice", method = RequestMethod.POST)
    public String get(@RequestBody InvoiceData invoiceData) throws Exception {
       return dto.generatePdf(invoiceData);
    }

}

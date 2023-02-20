package com.increff.invoice.invoice;


import com.increff.invoice.model.InvoiceData;
import com.increff.invoice.model.InvoiceItemData;
import org.springframework.stereotype.Component;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

@Component
public class JavaToXml {
    private static final String xmlFilePath = "src/main/resources/apache\\tempXml.xml";
    public static void xmlGenerator(InvoiceData invoiceData) throws Exception {
        try{
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        Element root = document.createElement("invoice");
        document.appendChild(root);

        Element invoiceNumber = document.createElement("invoicenumber");
        invoiceNumber.appendChild(document.createTextNode(""+ invoiceData.getOrderId()));
        root.appendChild(invoiceNumber);

        Element invoiceDate = document.createElement("invoicedate");
        invoiceDate.appendChild(document.createTextNode(""+ invoiceData.getDate()));
        root.appendChild(invoiceDate);

        Element invoiceTime = document.createElement("invoicetime");
        invoiceTime.appendChild(document.createTextNode(""+ invoiceData.getTime()));
        root.appendChild(invoiceTime);

        //address element
        Element address = document.createElement("address");

        root.appendChild(address);

        // set an attribute to address element
        Attr attr = document.createAttribute("type");
        attr.setValue("billto");
        address.setAttributeNode(attr);

        //you can also use address.setAttribute("type", "from") for this

        // address sub elements
        Element name = document.createElement("name");
        name.appendChild(document.createTextNode("Customer"));
        address.appendChild(name);

        Element building = document.createElement("building");
        building.appendChild(document.createTextNode(""));
        address.appendChild(building);

        Element area = document.createElement("area");
        area.appendChild(document.createTextNode(""));
        address.appendChild(area);

        Element city = document.createElement("city");
        city.appendChild(document.createTextNode(""));
        address.appendChild(city);

        // create lineitems element
        Element lineitems = document.createElement("lineitems");

        root.appendChild(lineitems);

        for(InvoiceItemData invoiceItemData : invoiceData.getInvoiceItemDataList()){
            Element lineitem = document.createElement("lineitem");

            lineitems.appendChild(lineitem);

            // create lineitems sub element
            Element sno = document.createElement("sno");
            sno.appendChild(document.createTextNode(""+ invoiceItemData.getIndex()));
            lineitem.appendChild(sno);

            Element barcode = document.createElement("barcode");
            barcode.appendChild(document.createTextNode(""+ invoiceItemData.getBarcode()));
            lineitem.appendChild(barcode);

            Element qty = document.createElement("quantity");
            qty.appendChild(document.createTextNode(""+ invoiceItemData.getQuantity()));
            lineitem.appendChild(qty);

            Element desc = document.createElement("product");
            desc.appendChild(document.createTextNode(""+ invoiceItemData.getProduct()));
            lineitem.appendChild(desc);

            Element unitPrice = document.createElement("unit-price");
            unitPrice.appendChild(document.createTextNode(""+ invoiceItemData.getUnitPrice()));
            lineitem.appendChild(unitPrice);

            Element amount = document.createElement("amount");
            amount.appendChild(document.createTextNode(""+ invoiceItemData.getAmount()));
            lineitem.appendChild(amount);

        }

        Element total = document.createElement("total");
        total.appendChild(document.createTextNode(""+ invoiceData.getTotalAmount()));
        root.appendChild(total);


        // create the xml file
        //transform the DOM Object to an XML File
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
//            StreamResult streamResult = new StreamResult(new File(xmlFilePath));
        File file = new File(xmlFilePath);
        final StreamResult streamResult = new StreamResult(file.toURI().getPath());

        // If you use
        // StreamResult result = new StreamResult(System.out);
        // the output will be pushed to the standard output ...
        // You can use that for debugging

        transformer.transform(domSource, streamResult);

        System.out.println("XML file created");

        } catch (
        ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (
        TransformerException tfe) {
            tfe.printStackTrace();
        }
    }

}

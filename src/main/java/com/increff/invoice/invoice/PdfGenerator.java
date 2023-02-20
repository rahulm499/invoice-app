package com.increff.invoice.invoice;

import com.increff.invoice.model.InvoiceData;
import org.apache.commons.codec.binary.Base64;
import org.apache.fop.apps.FOUserAgent;
import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.springframework.stereotype.Service;

import javax.xml.transform.*;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayOutputStream;
import java.io.File;

@Service
public class PdfGenerator {
    public String generatePdf(InvoiceData invoiceData) throws Exception {
            JavaToXml javaToXml = new JavaToXml();
            javaToXml.xmlGenerator(invoiceData);
            String xmlfile = new File("src/main/resources/apache\\tempXml.xml").toURI().getPath();
            String xsltfile = new File("src/main/resources/apache\\style.xsl").toURI().getPath();
            FopFactory fopFactory = FopFactory.newInstance();
            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            try {
                Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, outStream);
                TransformerFactory factory = TransformerFactory.newInstance();
                Transformer transformer = factory.newTransformer(new StreamSource(xsltfile));
                Source src = new StreamSource(xmlfile);
                Result res = new SAXResult(fop.getDefaultHandler());
                transformer.transform(src, res);
            } catch (TransformerConfigurationException e) {
                throw new RuntimeException(e);
            } catch (TransformerException e) {
                throw new RuntimeException(e);
            } finally {
                outStream.close();
            }

            return Base64.encodeBase64String(outStream.toByteArray());
        }

}

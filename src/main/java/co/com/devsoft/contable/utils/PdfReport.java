package co.com.devsoft.contable.utils;

import co.com.devsoft.contable.models.Invoice;
import co.com.devsoft.contable.models.Product;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

/** Clase para generar Reporte en PDF*/
public class PdfReport {


    private static final Logger logger = LoggerFactory.getLogger(PdfReport.class);

    public static ByteArrayInputStream invoicesReport(List<Invoice> invoices) {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            //Abrimos el documento
            document.open();

            //Instaciamos PdfPtable con 4 Columnas
            PdfPTable table = new PdfPTable(4);
            //Establecemos el ancho del documento
            table.setWidthPercentage(90);
            //Ancho de cada columna
            table.setWidths(new int[]{5, 5, 5, 5});
            //Establecemos la fuente
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            //Establecemos las cabeceras

            PdfPCell hcell;

            hcell = new PdfPCell(new Phrase("Invoice", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Customer", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Details", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Products", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            //Hacemos el llenado de los datos
            for (Invoice invoice : invoices) {

                PdfPCell cell;
                String proudctsString = "";

                cell = new PdfPCell(new Phrase(invoice.getInvoice()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(invoice.getCustomer()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(invoice.getDetails()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);

                if(invoice.getProductsList().size() > 0){

                    for (Product product : invoice.getProductsList()) {
                        proudctsString += product.getName() + ",";
                    }

                    String productsString = proudctsString.substring(0, proudctsString.length() - 1);

                    cell = new PdfPCell(new Phrase(productsString));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    table.addCell(cell);
                }
            }

            PdfWriter.getInstance(document, out);

            document.add(table);

            //Cerramos el documento
            document.close();

        } catch (DocumentException ex) {
            logger.error("Error occurred:", ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}

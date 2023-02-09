package com.miu.waafinalproject.utils;

import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.miu.waafinalproject.model.responseDTO.PdfResponseModel;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;

public class PdfExporterUtils {
    private PdfResponseModel pdfResponseModel;

    public PdfExporterUtils(PdfResponseModel pdfModel) {
        this.pdfResponseModel = pdfModel;
    }

    private void writeHeader(Document doc) {
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph letterHeadTite = new Paragraph("SRNA REAL STATE PORTAL", font);
        letterHeadTite.setAlignment(Paragraph.ALIGN_CENTER);
        Paragraph letterHeadAddress = new Paragraph("1000 N 4th Street, Fairfield, IA 52557", font);
        letterHeadAddress.setAlignment(Paragraph.ALIGN_CENTER);
        Paragraph letterContact = new Paragraph("Ph: +1 641-145-2476, Fax: +1-456-2041, Email: waa.srna@outlook.com", font);
        letterContact.setAlignment(Paragraph.ALIGN_CENTER);
        Paragraph today = new Paragraph(LocalDate.now().toString(), font);
        today.setAlignment(Paragraph.ALIGN_RIGHT);
        doc.add(letterHeadTite);
        doc.add(letterHeadAddress);
        doc.add(letterContact);
    }

    private void writeBody(Document doc) {
        Font font = FontFactory.getFont(FontFactory.TIMES);
        font.setSize(12);
        font.setColor(Color.BLACK);
        Paragraph bodyPara = new Paragraph("Buyer's Name: " + pdfResponseModel.getBuyerName() + "\n"
                + "Seller's Name: " + pdfResponseModel.getSellerName() + "\n"
                + "Contract Date: " + pdfResponseModel.getContractDate() + "\n\n\n", font);
        Paragraph propertyDetailTitle = new Paragraph("Property Details:", font);
        Paragraph propertyDetail = new Paragraph(
                pdfResponseModel.getPropertyDetail().getDescription() + "\n"
                        + pdfResponseModel.getPropertyDetail().getFeatures() + "\n"
                        + pdfResponseModel.getAddress().getStreet() + ", " + pdfResponseModel.getAddress().getCity() + ", " + pdfResponseModel.getAddress().getState() + ", " + pdfResponseModel.getAddress().getZipcode() + "\n"
                        + "Cost Price: $ " + pdfResponseModel.getCostPrice() + "\n"
                        + "Selling Price: $ " + pdfResponseModel.getSellingPrice() + ".... after " + pdfResponseModel.getTax() + "% tax rate" + "\n",
                font);

        bodyPara.setAlignment(Paragraph.ALIGN_LEFT);
        propertyDetailTitle.setAlignment(Paragraph.ALIGN_LEFT);
        propertyDetail.setAlignment(Paragraph.ALIGN_LEFT);
        doc.add(bodyPara);
        doc.add(propertyDetailTitle);
        doc.add(propertyDetail);
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("BuyerName", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("E-mail", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Full Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Roles", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Enabled", font));
        table.addCell(cell);
    }

//    private void writeTableData(PdfPTable table) {
//        for (User user : listUsers) {
//            table.addCell(String.valueOf(user.getId()));
//            table.addCell(user.getEmail());
//            table.addCell(user.getFullName());
//            table.addCell(user.getRoles().toString());
//            table.addCell(String.valueOf(user.isEnabled()));
//        }
//    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();

        writeHeader(document);
        writeBody(document);

        document.close();
    }
}

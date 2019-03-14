import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import org.json.JSONException;


import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static final int QUESTIONS_PER_PAGE = 40;
    public static int questions = 40;

    public static void main(String[] args) throws DocumentException, IOException, JSONException {

        System.out.println("Hello World!");


        Document document = new Document(PageSize.A4.rotate(), 0.75f, 0.75f, 20, 0.75f);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/Users/bhanuprakash/Documents/bubble1.pdf"));
        document.open();

        for (int student = 0; student <1; student++) {
            //JSONObject currentStudent = students.getJSONObject(student);
            String name = "student name goes here";
            String className = "class name goes here";
            String assignmentName = "assignment goes here";

            String teacherName = "teacher name goes here";


            PdfPTable nameTable = new PdfPTable(3);
            PdfPTable headerTable = new PdfPTable(6);
            Image logoImg = Image.getInstance("/Users/bhanuprakash/Downloads/sixt_logo_1.png");

            logoImg.scalePercent(35f);
            logoImg.setAlignment(PdfPCell.ALIGN_CENTER);
            logoImg.setBorder(PdfPCell.NO_BORDER);

            PdfPCell logoCell = new PdfPCell(logoImg);
            logoCell.setColspan(6);
            logoCell.setBorder(PdfPCell.NO_BORDER);

            if (assignmentName.length() > 70) {
                assignmentName = assignmentName.substring(0, 70) + "...";
            }
            if (teacherName.length() > 25) {
                teacherName = teacherName.substring(0, 25) + "...";
            }
            if (className.length() > 25) {
                className = className.substring(0, 25) + "...";
            }
            if (name.length() > 25) {
                name = name.substring(0, 25) + "...";
            }
            Phrase phrase1 = new Phrase();
            phrase1.add(new Chunk("Name: ", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
            phrase1.add(new Chunk(name, new Font()));
            PdfPCell nameCell1 = new PdfPCell();

            nameCell1.addElement(phrase1);
            nameCell1.setNoWrap(true);
            nameCell1.setColspan(2);

            Phrase phrase2 = new Phrase();
            phrase2.add(new Chunk("Class: ", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
            phrase2.add(new Chunk(className, new Font()));
            PdfPCell nameCell2 = new PdfPCell();
            nameCell2.addElement(phrase2);
            nameCell2.setNoWrap(true);
            nameCell2.setColspan(2);

            Phrase phrase4 = new Phrase();
            phrase4.add(new Chunk("Teacher Name: ", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
            phrase4.add(new Chunk(teacherName, new Font()));
            PdfPCell nameCell4 = new PdfPCell();
            nameCell4.addElement(phrase4);
            nameCell4.setNoWrap(true);
            nameCell4.setColspan(2);

            Phrase phrase3 = new Phrase();
            phrase3.add(new Chunk("Assessment: ", new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD)));
            phrase3.add(new Chunk(assignmentName, new Font()));
            PdfPCell nameCell3 = new PdfPCell();
            nameCell3.addElement(phrase3);
            nameCell3.setNoWrap(true);
            nameCell3.setColspan(6);

            nameCell1.setBorder(PdfPCell.NO_BORDER);
            nameCell2.setBorder(PdfPCell.NO_BORDER);
            nameCell4.setBorder(PdfPCell.NO_BORDER);
            nameCell3.setBorder(PdfPCell.NO_BORDER);
            PdfPCell fillerCell = new PdfPCell(new Phrase(""));
            fillerCell.setColspan(6);
            fillerCell.setBorder(PdfPCell.NO_BORDER);

            nameTable.addCell(nameCell1);
            nameTable.addCell(nameCell2);
            nameTable.addCell(nameCell4);
            nameTable.addCell(nameCell3);

            //headerTable.addCell(logoCell);
            headerTable.addCell(nameCell1);
            headerTable.addCell(nameCell2);
            headerTable.addCell(nameCell4);
            headerTable.addCell(nameCell3);
            headerTable.addCell(fillerCell);
            headerTable.addCell(fillerCell);

            for (int page = 0; page < 1; page++) {
                document.newPage();
                PdfPTable table1 = new PdfPTable(7);
                PdfPTable table2 = new PdfPTable(7);
                PdfPTable table4 = new PdfPTable(1);
                PdfPTable table3 = new PdfPTable(1);
                PdfPTable tableMain = null;
                for (int i = 0; i < QUESTIONS_PER_PAGE; i++) {
                    if ((i + 1 + (page * QUESTIONS_PER_PAGE)) <= questions) {
                        if (i <= 19) {
                            addBubbles(page, table1, i);
                        } else {
                            addBubbles(page, table2, i);
                        }
                    }
                }
                PdfPCell cellt3 = new PdfPCell();
                cellt3.addElement(table1);
                cellt3.setBorderWidth(1.5f);
                cellt3.setPaddingTop(15);
                cellt3.setPaddingBottom(15);

                PdfPCell cellt4 = new PdfPCell();
                cellt4.addElement(table2);
                cellt4.setBorderWidth(1.5f);
                cellt4.setPaddingTop(15);
                cellt4.setPaddingBottom(15);

                table3.addCell(cellt3);
                table4.addCell(cellt4);

                PdfPCell cell = new PdfPCell();
                cell.addElement(table3);
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setVerticalAlignment(PdfPCell.ALIGN_TOP);

                PdfPCell cell4 = new PdfPCell();
                cell4.addElement(table4);
                cell4.setBorder(PdfPCell.NO_BORDER);
                cell4.setVerticalAlignment(PdfPCell.ALIGN_TOP);

                PdfPCell emCell = new PdfPCell(new Phrase(""));
                emCell.setBorder(PdfPCell.NO_BORDER);
                if (table2.size() > 0) {
                    tableMain = new PdfPTable(3);
                    tableMain.setWidths(new int[]{70, 70, 30});
                    tableMain.addCell(cell);
                    tableMain.addCell(cell4);
                    tableMain.addCell(emCell);
                } else {
                    tableMain = new PdfPTable(2);
                    tableMain.setWidths(new int[]{30, 30});
                    cell.setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                    tableMain.addCell(cell);
                    tableMain.addCell(emCell);
                }

                document.add(headerTable);
                document.add(tableMain);
                //vId is assignmentVersionId, sId is student Id and cId is class section Id. QR code cannot read more data properly so small forms of fields are used
                //String qrCodeString = assignmentVersionId + "_" + classSectionId + "_" + studentId + "_" + Integer.toString((page + 1));
                BarcodeQRCode qrCode = new BarcodeQRCode("hey curious..>", 100, 100, null);
                Image qrCodeImage = qrCode.getImage();
                qrCodeImage.scalePercent(120);
                qrCodeImage.setAbsolutePosition(650, 320);
                document.add(qrCodeImage);
                try {
                    PdfContentByte cb = writer.getDirectContent();
                    BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
                    cb.saveState();
                    cb.beginText();
                    cb.moveText(750, 30);
                    cb.setFontAndSize(bf, 12);
                    cb.showText("Page " + Integer.toString((page + 1)) + " of " + Integer.toString(1));
                    cb.endText();
                    cb.restoreState();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                document.newPage();
            }
        }
        document.close();

    }



    private static void addBubbles(int page, PdfPTable table1, int i) throws JSONException {
        int quesNum = (i  + (page * QUESTIONS_PER_PAGE));
        PdfPCell numCell = new PdfPCell(new Phrase("" + (quesNum + 1)));
        numCell.setBorder(PdfPCell.NO_BORDER);
        table1.addCell(numCell);
        //JSONObject json = questions.getJSONObject(quesNum);
        int numOfChoices = 6;
        java.util.List<String> choiceLabels = null;

        int remaining = 6 - numOfChoices;
        for (int j = 0; j < numOfChoices; j++) {
            Character label = (char) (65 + j);

            if((choiceLabels != null) && (choiceLabels.size() > j)){
                String choiceLabel = choiceLabels.get(j);
                label = choiceLabel.charAt(0);
            }
            String circleUrl = "/Users/bhanuprakash/Documents/a.png";
            Image img;
            try {
                img = Image.getInstance("/Users/bhanuprakash/Downloads/a3211.png");
                img.scalePercent(22f);
                img.setAlignment(PdfPCell.ALIGN_CENTER);
                PdfPCell bubbleCell = new PdfPCell(img);
                bubbleCell.setBorder(PdfPCell.NO_BORDER);
                bubbleCell.setPadding(2f);
                table1.addCell(bubbleCell);
            } catch (Exception ex) {
                //logger.error("File not found : " + label + ".png");
            }
        }

        if (numOfChoices < 6) {
            for (int k = 0; k < remaining; k++) {
                PdfPCell emptyCell = new PdfPCell(new Phrase(""));
                emptyCell.setBorder(PdfPCell.NO_BORDER);
                table1.addCell(emptyCell);
            }
        }
    }

}

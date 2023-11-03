package org.example.Components.Designs;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import org.example.Components.PdfDesign;
import org.example.Letter.LetterDTO;
import org.example.Resume.ResumeDTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;


public class MinimalistDesign extends PdfDesign {

    private static final String primaryFontName = "UbuntuMono-Regular.ttf";
    private static final String secondaryFontName = "UbuntuMono-Bold.ttf";
    private static final int primaryFontSize = 9;
    private static final int secondaryFontSize = 11;
    private static final Color light = new DeviceRgb(255, 255, 255);
    private static final Color dark = new DeviceRgb(25, 25, 25);
    private static final float topPadding = 20;
    private static final float rightPadding = 30;

    public MinimalistDesign() {}

    @Override
    public InputStreamResource createResumeDesign(ResumeDTO resumeDTO, String backgroundVersion) {
        // Initialize new pdf document
        // Create a ByteArrayOutputStream to hold the PDF content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document;
        Color fontColor;
        if (backgroundVersion.equals("1")) {
            // Light background
            fontColor = dark;
            document = initializeDocument(outputStream, true, false, null, light);
        } else {
            // Dark background
            fontColor = light;
            document = initializeDocument(outputStream, true, false, null, dark);
        }

        // Create fonts (for every document a new font has to be created otherwise it will lead to an PdfException)
        PdfFont[] fonts = createFonts(primaryFontName, secondaryFontName);
        PdfFont primaryFont = fonts[0];
        PdfFont secondaryFont = fonts[1];

        // LAYOUT
        // Initialize layout using a table which is used to align elements
        Table layout = new Table(3);

        // Add personal information to the layout
        List personalInformationList = new List();
        personalInformationList.setListSymbol("");
        personalInformationList.add(resumeDTO.getName());
        personalInformationList.add(resumeDTO.getSurname());
        if (!resumeDTO.getBirthday().isEmpty()) {
            personalInformationList.add(resumeDTO.getBirthday());
        }
        layout.addCell(createCell(personalInformationList, 1, 1, TextAlignment.LEFT, primaryFont, primaryFontSize, fontColor));

        // Add empty whitespace cell
        layout.addCell(new Cell().setBorder(Border.NO_BORDER));

        // Add contact information to the layout
        List contactInformationList = new List();
        contactInformationList.setListSymbol("");
        contactInformationList.add(resumeDTO.getEmail().toLowerCase());
        contactInformationList.add(resumeDTO.getPhone());
        if (!resumeDTO.getLink().isEmpty()) {
            contactInformationList.add(resumeDTO.getLink().toLowerCase());
        }
        layout.addCell(createCell(contactInformationList, 1, 1, TextAlignment.RIGHT, primaryFont, primaryFontSize, fontColor));

        // Add professional experience to the document body
        Paragraph sectionHeading1 = new Paragraph("Work\nexperience".toUpperCase());
        layout.addCell(createCell(sectionHeading1, 1, 1, TextAlignment.LEFT, secondaryFont, secondaryFontSize, fontColor)
                .setPaddingTop(topPadding).setPaddingRight(rightPadding));

        for (int i = 0; i < resumeDTO.getWorkExperience().size(); i++) {
            // Position and company
            List experienceItemHead = new List();
            experienceItemHead.setListSymbol("");
            experienceItemHead.add(resumeDTO.getWorkExperience().get(i).getEmployer().toUpperCase());
            experienceItemHead.add(resumeDTO.getWorkExperience().get(i).getJobTitle().toUpperCase());
            layout.addCell(createCell(experienceItemHead, 1, 1, TextAlignment.LEFT, secondaryFont, secondaryFontSize, fontColor)
                    .setPaddingTop(topPadding));

            // Duration
            Paragraph durationItem =  new Paragraph();
            durationItem.add(resumeDTO.getWorkExperience().get(i).getStartDate());
            durationItem.add("  -  ");
            if (resumeDTO.getWorkExperience().get(i).getCurrentFlag()) {
                durationItem.add("Present");
            } else {
                durationItem.add(resumeDTO.getWorkExperience().get(i).getEndDate());
            }
            layout.addCell(createCell(durationItem, 1, 1, TextAlignment.RIGHT, primaryFont, primaryFontSize, fontColor)
                    .setPaddingTop(topPadding));

            // Add empty whitespace cell
            layout.addCell(new Cell().setBorder(Border.NO_BORDER));

            // Description
            List descriptionItem = new List();
            for (int k = 0; k < resumeDTO.getWorkExperience().get(i).getBulletList().size(); k++) {
                descriptionItem.add(resumeDTO.getWorkExperience().get(i).getBulletList().get(k));
            }
            layout.addCell(createCell(descriptionItem, 1, 2, TextAlignment.LEFT, primaryFont, primaryFontSize, fontColor));

            // Add empty whitespace cell
            layout.addCell(new Cell().setBorder(Border.NO_BORDER));
        }

        // Add whitespace
        layout.addCell(new Cell().setBorder(Border.NO_BORDER));
        layout.addCell(new Cell().setBorder(Border.NO_BORDER));

        // Add relevant expertise to the document body
        Paragraph sectionHeading2 = new Paragraph("Relevant\nexpertise".toUpperCase());
        layout.addCell(createCell(sectionHeading2, 1, 1, TextAlignment.LEFT, secondaryFont, secondaryFontSize, fontColor)
                .setPaddingTop(topPadding).setPaddingRight(rightPadding));

        for (int i = 0; i < resumeDTO.getRelevantExpertise().size(); i++) {
            // Expertise
            Paragraph expertiseItem = new Paragraph();
            expertiseItem.add(resumeDTO.getRelevantExpertise().get(i).getExpertise().toUpperCase());

            // Description
            List descriptionItem = new List();
            for (int k = 0; k < resumeDTO.getRelevantExpertise().get(i).getBulletList().size(); k++) {
                descriptionItem.add(resumeDTO.getRelevantExpertise().get(i).getBulletList().get(k));
            }

            // Wrap items in table in order to ensure alignment
            Table expertiseWrap = new Table(1);
            expertiseWrap.addCell(createCell(expertiseItem, 1, 1, TextAlignment.LEFT, secondaryFont, secondaryFontSize, fontColor));
            expertiseWrap.addCell(createCell(descriptionItem, 1, 2, TextAlignment.LEFT, primaryFont, primaryFontSize, fontColor));
            layout.addCell(new Cell(1,2).add(expertiseWrap).setBorder(Border.NO_BORDER).setPaddingTop(topPadding));

            // Add empty whitespace cell
            layout.addCell(new Cell().setBorder(Border.NO_BORDER));
        }

        // Add whitespace
        layout.addCell(new Cell().setBorder(Border.NO_BORDER));
        layout.addCell(new Cell().setBorder(Border.NO_BORDER));

        // Add education to the document body
        Paragraph sectionHeading3 = new Paragraph("Education\n".toUpperCase());
        layout.addCell(createCell(sectionHeading3, 1, 1, TextAlignment.LEFT, secondaryFont, secondaryFontSize, fontColor)
                .setPaddingTop(topPadding).setPaddingRight(rightPadding));

        for (int i = 0; i < resumeDTO.getEducation().size(); i++) {
            // Degree
            List degreeItem = new List();
            degreeItem.setListSymbol("");
            degreeItem.add(resumeDTO.getEducation().get(i).getDegree().toUpperCase() + " " + resumeDTO.getEducation().get(i).getMajor().toUpperCase());
            layout.addCell(createCell(degreeItem, 1, 1, TextAlignment.LEFT, secondaryFont, secondaryFontSize, fontColor)
                    .setPaddingTop(topPadding));

            // Duration
            Paragraph durationItem =  new Paragraph();
            durationItem.add(resumeDTO.getEducation().get(i).getStartDate());
            durationItem.add("  -  ");
            if (resumeDTO.getEducation().get(i).getExpectedFlag()) {
                durationItem.add("Expected: " + resumeDTO.getEducation().get(i).getEndDate());
            } else {
                durationItem.add(resumeDTO.getEducation().get(i).getEndDate());
            }
            durationItem.add(resumeDTO.getEducation().get(i).getEndDate());
            layout.addCell(createCell(durationItem, 1, 1, TextAlignment.RIGHT, primaryFont, primaryFontSize, fontColor)
                    .setPaddingTop(topPadding));

            // Add empty whitespace cell
            layout.addCell(new Cell().setBorder(Border.NO_BORDER));

            // Institution, graduation year, and description
            List educationItem =  new List();
            educationItem.setListSymbol(List.DEFAULT_LIST_SYMBOL);
            educationItem.add(resumeDTO.getEducation().get(i).getSchool());
            for (int k = 0; k < resumeDTO.getEducation().get(i).getBulletList().size(); k++) {
                educationItem.add(resumeDTO.getEducation().get(i).getBulletList().get(k));
            }
            layout.addCell(createCell(educationItem, 1, 2, TextAlignment.LEFT, primaryFont, primaryFontSize, fontColor));

            // Add empty whitespace cell
            layout.addCell(new Cell().setBorder(Border.NO_BORDER));
        }

        layout.addCell(new Cell(1,3).setBorder(Border.NO_BORDER));
        document.add(layout);
        document.close();
        document.getPdfDocument().close();

        // Convert the ByteArrayOutputStream to an InputStream
        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        return new InputStreamResource(inputStream);
    }

    @Override
    public InputStreamResource createLetterDesign(LetterDTO letterDTO, String backgroundVersion) {
        // Initialize new pdf document
        // Create a ByteArrayOutputStream to hold the PDF content
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Document document;
        Color fontColor;
        if (backgroundVersion.equals("1")) {
            // Light background
            fontColor = dark;
            document = initializeDocument(outputStream, true, false, null, light);
        } else {
            // Dark background
            fontColor = light;
            document = initializeDocument(outputStream, true, false, null, dark);
        }

        // Create fonts (for every document a new font has to be created otherwise it will lead to an PdfException)
        PdfFont[] fonts = createFonts(primaryFontName, secondaryFontName);
        PdfFont primaryFont = fonts[0];
        PdfFont secondaryFont = fonts[1];

        // LAYOUT
        // Initialize layout using a table which is used to align elements
        Table layout = new Table(2);

        // Add receiver address if available
        if (letterDTO.getReceiverFlag()) {
            List receiverContact = new List();
            receiverContact.setListSymbol("");
            receiverContact.add(letterDTO.getReceiverContact().getCompany());
            receiverContact.add(letterDTO.getReceiverContact().getStreet() + " " + letterDTO.getReceiverContact().getHouseNumber());
            receiverContact.add(letterDTO.getReceiverContact().getPostalCode());
            receiverContact.add(letterDTO.getReceiverContact().getCity());
            layout.addCell(createCell(receiverContact, 1, 1, TextAlignment.LEFT, primaryFont, primaryFontSize, fontColor));
        }

        // Add personal and contact information to the layout
        List personalInformationList = new List();
        personalInformationList.setListSymbol("");
        personalInformationList.add(letterDTO.getName());
        personalInformationList.add(letterDTO.getSurname());
        if (!letterDTO.getBirthday().isEmpty()) {
            personalInformationList.add(letterDTO.getBirthday());
        }
        personalInformationList.add(letterDTO.getEmail().toLowerCase());
        personalInformationList.add(letterDTO.getPhone());
        if (!letterDTO.getLink().isEmpty()) {
            personalInformationList.add(letterDTO.getLink().toLowerCase());
        }
        layout.addCell(createCell(personalInformationList, 1, 1, TextAlignment.RIGHT, primaryFont, primaryFontSize, fontColor));

        // Add subject line
        Paragraph subjectLine = new Paragraph(letterDTO.getSubjectLine().toUpperCase());
        layout.addCell(createCell(subjectLine, 1, 2, TextAlignment.LEFT, secondaryFont, secondaryFontSize, fontColor)
                .setPaddingTop(100f));

        // Add salutation
        Paragraph salutation = new Paragraph();
        salutation.add("Dear ");
        if (!letterDTO.getReceiverFlag()) {
            salutation.add("Hiring Team");
        } else {
            salutation.add(letterDTO.getReceiverContact().getFormOfAddress() + " ");
            salutation.add(letterDTO.getReceiverContact().getReceiverSurname() + ",");
        }
        layout.addCell(createCell(salutation, 1, 2, TextAlignment.LEFT, primaryFont, primaryFontSize, fontColor)
                .setPaddingTop(topPadding));

        // Add letter text
        Paragraph letterText = new Paragraph(letterDTO.getContent());
        layout.addCell(createCell(letterText, 2, 2, TextAlignment.LEFT, primaryFont, primaryFontSize, fontColor));

        // Add signature / name at the end
        Paragraph signature = new Paragraph();
        signature.add(letterDTO.getName() + " " + letterDTO.getSurname());
        layout.addCell(createCell(signature, 1, 2, TextAlignment.LEFT, primaryFont, primaryFontSize, fontColor)
                .setPaddingTop(topPadding));

        layout.addCell(new Cell(1,2).setBorder(Border.NO_BORDER));
        document.add(layout);
        document.close();
        document.getPdfDocument().close();

        // Convert the ByteArrayOutputStream to an InputStream
        InputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        return new InputStreamResource(inputStream);
    }
}

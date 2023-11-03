package org.example.Components;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.BlockElement;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.properties.TextAlignment;
import org.example.Letter.LetterDTO;
import org.example.Resume.ResumeDTO;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

public abstract class PdfDesign {

    public static final String FONT_FOLDER_PATH = "./src/main/resources/fonts/";
    public static final String BACKGROUND_FOLDER_PATH = "./src/main/resources/pdfBackgrounds/";
    public final BackgroundColorHandler backgroundColorHandler = new BackgroundColorHandler();
    public final BackgroundImageHandler backgroundImageHandler = new BackgroundImageHandler();

    public abstract InputStreamResource createResumeDesign(ResumeDTO resumeDTO, String backgroundVersion);

    public abstract InputStreamResource createLetterDesign(LetterDTO letterDTO, String backgroundVersion);

    public Document initializeDocument(ByteArrayOutputStream outputStream, boolean usesColorHandler, boolean usesImageHandler, String imagePath, Color backgroundColor) {
        PdfWriter pdfWriter;
        pdfWriter = new PdfWriter(outputStream);
        PdfDocument pdf = new PdfDocument(pdfWriter);
        Document document = new Document(pdf, PageSize.A4);

        if (usesColorHandler && usesImageHandler) {
            throw new IllegalArgumentException("BackgroundColorHandler and BackgroundImageHandler cant both be used by the same document.");
        }

        if (usesColorHandler) {
            backgroundColorHandler.setPdfDocument(pdf);
            pdf.addEventHandler(PdfDocumentEvent.START_PAGE, backgroundColorHandler);
            backgroundColorHandler.setBackgroundColor(backgroundColor);
        }

        if (usesImageHandler) {
            backgroundImageHandler.setPdfDocument(pdf);
            pdf.addEventHandler(PdfDocumentEvent.START_PAGE, backgroundImageHandler);
            backgroundImageHandler.setImagePath(imagePath);
        }

        return document;
    }

    public Cell createCell(BlockElement element,
                            int rowSpan,
                            int colSpan,
                            TextAlignment alignment,
                            PdfFont font,
                            int fontSize,
                            Color fontColor) {
        Cell cell = new Cell(rowSpan, colSpan);
        cell.add(element);
        cell.setFont(font);
        cell.setFontSize(fontSize);
        cell.setFontColor(fontColor);
        cell.setPadding(0);
        cell.setTextAlignment(alignment);
        cell.setBorder(Border.NO_BORDER);
        return cell;
    }

    public PdfFont[] createFonts(String primaryFontName, String secondaryFontName) {
        PdfFont primaryFont; // Font for paragraphs (REGULAR)
        PdfFont secondaryFont; // Font for headings (BOLD)
        File primaryFontFile = new File(FONT_FOLDER_PATH, primaryFontName);
        File secondaryFontFile = new File(FONT_FOLDER_PATH, secondaryFontName);
        if (primaryFontFile.exists() && primaryFontFile.isFile() && secondaryFontFile.exists() && secondaryFontFile.isFile()) {
            // Set fonts
            try {
                primaryFont = PdfFontFactory.createFont(FONT_FOLDER_PATH + primaryFontName);
                secondaryFont = PdfFontFactory.createFont(FONT_FOLDER_PATH + secondaryFontName);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        } else {
            // Use Helvetica as fallback font
            try {
                primaryFont = PdfFontFactory.createFont(StandardFonts.HELVETICA);
                secondaryFont = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return new PdfFont[]{primaryFont, secondaryFont};
    }
}

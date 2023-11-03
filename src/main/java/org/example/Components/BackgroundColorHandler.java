package org.example.Components;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

public class BackgroundColorHandler implements IEventHandler {

    private Color backgroundColor;
    private PdfDocument pdfDocument;

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    public void setPdfDocument(PdfDocument pdfDocument) {
        this.pdfDocument = pdfDocument;
    }

    public BackgroundColorHandler() {}

    @Override
    public void handleEvent(Event event) {
        PdfCanvas canvas = new PdfCanvas(pdfDocument.getLastPage());
        canvas.saveState()
                .setFillColor(backgroundColor)
                .rectangle(PageSize.A4)
                .fill()
                .restoreState();
    }

}

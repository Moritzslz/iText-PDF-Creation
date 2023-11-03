package org.example.Components;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;


import java.net.MalformedURLException;

public class BackgroundImageHandler implements IEventHandler {

    private ImageData image;
    private String imagePath;
    private PdfDocument pdfDocument;

    public void setImagePath(String imagePath) { this.imagePath = imagePath; createImageData(); }
    public void setPdfDocument(PdfDocument pdfDocument) {
        this.pdfDocument = pdfDocument;
    }

    public BackgroundImageHandler() {}

    @Override
    public void handleEvent(Event event) {
        PdfCanvas canvas = new PdfCanvas(pdfDocument.getLastPage());
        canvas.addImageAt(image,0,0,true);
    }

    private void createImageData() {
        try {
            image = ImageDataFactory.create(imagePath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}

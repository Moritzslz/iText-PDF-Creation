package org.example.Components.Designs;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import org.example.Components.PdfDesign;
import org.example.Letter.LetterDTO;
import org.example.Resume.ResumeDTO;


public class ProfessionalDesign extends PdfDesign {

    private static final String primaryFontName = "";
    private static final String secondaryFontName = "";
    private static final int primaryFontSize = Integer.MAX_VALUE;
    private static final int secondaryFontSize = Integer.MAX_VALUE;
    private static final Color light = new DeviceRgb(255, 255, 255);
    private static final Color dark = new DeviceRgb(0, 0, 0);
    private static final float topPadding = 20;

    public ProfessionalDesign() {}

    @Override
    public InputStreamResource createResumeDesign(ResumeDTO resumeDTO, String backgroundVersion) {
        // TODO
        return null;
    }

    @Override
    public InputStreamResource createLetterDesign(LetterDTO letterDTO, String backgroundVersion) {
        // TODO
        return null;
    }
}

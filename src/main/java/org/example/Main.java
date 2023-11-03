package org.example;
import org.example.Components.Designs.MinimalistDesign;
import org.example.Resume.*;
import org.example.Letter.*;
import org.example.Components.*;
import java.util.ArrayList;

public class Main {

    public static PdfDesign minimalistDesign;
    public static PdfDesign professionalDesign;

    public static void main(String[] args) {

        System.out.println("Creating pdf...");

        // TODO Create new templates
        String templateName = "minimalist";
        String backgroundVersion = "1";

        // TODO Populate example resume DTO with data
        WorkExperience workExperience = new WorkExperience();
        RelevantExpertise relevantExpertise = new RelevantExpertise();
        Education education = new Education();
        Award award = new Award();
        ArrayList<String> skills = new ArrayList<>();

        // TODO inject resume Data into DTO constructor
        ResumeDTO resumeDTO = new ResumeDTO();

        // Testing Resume ceration
        createResume(resumeDTO, templateName, backgroundVersion);

        // TODO do the same for LetterDTO but with the right child classes
    }
    public static InputStreamResource createResume(ResumeDTO resumeDTO, String templateName, String backgroundVersion) {

        return switch (templateName.toLowerCase()) {
            case "minimalist" -> minimalistDesign.createResumeDesign(resumeDTO, backgroundVersion);
            case "professional" -> professionalDesign.createResumeDesign(resumeDTO, backgroundVersion);
            default -> professionalDesign.createResumeDesign(resumeDTO, backgroundVersion);
        };
    }

}

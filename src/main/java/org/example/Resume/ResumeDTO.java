package org.example.Resume;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResumeDTO {

    private String templateName;
    private String templateVersion;
    private String name;
    private String surname;
    private String birthday;
    private String email;
    private String phone;
    private String link;
    private String applyingFor;
    private String applyingAt;
    private ArrayList<WorkExperience> workExperience;
    private ArrayList<RelevantExpertise> relevantExpertise;
    private ArrayList<Education> education;
    private ArrayList<Award> awards;
    private ArrayList<String> skills;
}

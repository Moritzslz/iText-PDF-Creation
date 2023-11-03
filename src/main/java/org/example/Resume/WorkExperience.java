package org.example.Resume;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkExperience {

    private String employer;
    private String jobTitle;
    private Boolean currentFlag;
    private String startDate;
    private String endDate;
    private ArrayList<String> bulletList;
}

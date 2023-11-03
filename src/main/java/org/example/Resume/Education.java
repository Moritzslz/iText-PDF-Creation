package org.example.Resume;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Education {

    private String school;
    private String degree;
    private String major;
    private Boolean expectedFlag;
    private String startDate;
    private String endDate;
    private ArrayList<String> bulletList;
}

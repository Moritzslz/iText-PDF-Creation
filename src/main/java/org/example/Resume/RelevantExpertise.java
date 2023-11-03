package org.example.Resume;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RelevantExpertise {

    private String expertise;
    private ArrayList<String> bulletList;
}

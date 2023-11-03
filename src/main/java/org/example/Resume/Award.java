package org.example.Resume;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Award {

    private String award;
    private String issuer;
    private String startDate;
    private String endDate;
    private String description;
}

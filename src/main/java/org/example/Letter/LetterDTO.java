package org.example.Letter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LetterDTO {

    private String templateName;
    private String templateVersion;
    private String name;
    private String surname;
    private String birthday;
    private String email;
    private String phone;
    private String link;
    private String applyingFor;
    private String jobDescription;
    private String subjectLine;
    private String content;
    private Boolean receiverFlag;
    private ReceiverContact receiverContact;
    private ArrayList<String> skills;
}

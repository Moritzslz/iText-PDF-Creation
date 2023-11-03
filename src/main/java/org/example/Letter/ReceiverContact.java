package org.example.Letter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiverContact {

    private String company;
    private String street;
    private String houseNumber;
    private String postalCode;
    private String city;
    private String formOfAddress;
    private String receiverSurname;
}

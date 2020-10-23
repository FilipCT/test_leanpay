package si.leanpay.taf.data;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class PersonalData {

    private String firstName;
    private String lastName;
    private String email;
    private String postCode;
    private String region;
    private String street;
    private String houseNumber;
    private String gender;
    private String dateOfBirth;
    private String placeOfBirth;
    private String countryOfBirth;
    private String taxNumber;
}

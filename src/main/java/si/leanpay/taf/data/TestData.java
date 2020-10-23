package si.leanpay.taf.data;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class TestData {
    @CsvBindByName
    private String accessToken;
    @CsvBindByName
    private String phoneNumber;
    @CsvBindByName
    private String userName;
    @CsvBindByName
    private String password;
    private String idToken;
    private String requestToken;

    private int itemNumber;
    private String installmentPlan;
    private String paymentDate;
    private String firstName;
    private String lastName;
    private String securityPin;
}

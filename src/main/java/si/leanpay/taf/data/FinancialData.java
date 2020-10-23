package si.leanpay.taf.data;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class FinancialData {
    private String monthlyEarnings;
    private String creditLiabilities;
    private String typeOfEmployment;
    private String familyMembers;
    private String transactionNumber;
}

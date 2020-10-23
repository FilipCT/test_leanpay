package si.leanpay.taf.data;

import com.opencsv.bean.CsvBindByName;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class VendorData {
    @CsvBindByName
    private String vendorApiKey;
    @CsvBindByName
    private double amount;
    @CsvBindByName
    private String vendorFirstName;
    @CsvBindByName
    private String vendorLastName;
    @CsvBindByName
    private String vendorAddress;
    @CsvBindByName
    private String vendorZip;
    @CsvBindByName
    private String vendorCity;
    @CsvBindByName
    private String language;
    @CsvBindByName
    private String additionalMeta;
    @CsvBindByName
    private String vendorEmail;
    @CsvBindByName
    private int term;

}

package si.leanpay.taf.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.opencsv.bean.CsvToBeanBuilder;
import lombok.extern.log4j.Log4j2;
import si.leanpay.taf.data.FinancialData;
import si.leanpay.taf.data.PersonalData;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.List;
import java.util.Locale;

@Log4j2
public class DataHelper {
    private static final String TEST_DATA_DIR = "./src/test/resources/data/";

    /**
     * Private constructor
     */
    private DataHelper() {
        throw new UnsupportedOperationException();
    }

    /**
     * Map CSV to Object
     *
     * @param csvFile   - CSV file name without extension
     * @param className - TestData class
     * @return Object collection
     * @throws FileNotFoundException if file is not found
     */
    @SuppressWarnings("unchecked")
    public static Object[][] mapDataFromCSVToObject(Class className, String csvFile) throws FileNotFoundException {
        String renamedCSVFile = getCsvFileName(csvFile);

        Reader file = new FileReader(
                TEST_DATA_DIR + renamedCSVFile + ".csv");
        List<?> testData = new CsvToBeanBuilder(file)
                .withType(className).build().parse();

        Object[][] testNgData = new Object[testData.size()][1];
        for (int i = 0; i < testData.size(); i++) {
            testNgData[i][0] = testData.get(i);
        }
        return testNgData;
    }

    /**
     * Method to rename CSV file
     *
     * @param csvFile CSV file name
     * @return CSV file name without extension
     */
    private static String getCsvFileName(String csvFile) {
        String renamedCSVFile = csvFile;
        if (csvFile.endsWith(".csv")) {
            renamedCSVFile = csvFile.replace(".csv", "");
        }
        return renamedCSVFile;
    }

    public static void saveFinancialData(List<String> financialDataList, FinancialData financialData) {
        financialData.setMonthlyEarnings(financialDataList.get(0));
        financialData.setCreditLiabilities(financialDataList.get(1));
        financialData.setTypeOfEmployment(financialDataList.get(2));
        financialData.setFamilyMembers(financialDataList.get(3));
        financialData.setTransactionNumber(financialDataList.get(4));
    }

    public static void savePersonalData(List<String> personDataList, PersonalData personalData) {
        Faker faker = new Faker();
        FakeValuesService fakeValuesService = new FakeValuesService(
                new Locale("en-US"), new RandomService());
        personalData.setFirstName(faker.name().firstName());
        personalData.setLastName(faker.name().lastName());
        personalData.setEmail(fakeValuesService.bothify("????##@leanpay.com"));
        personalData.setPostCode(personDataList.get(3).toUpperCase());
        personalData.setRegion(personDataList.get(4).toUpperCase());
        personalData.setStreet(personDataList.get(5).toUpperCase());
        personalData.setHouseNumber(personDataList.get(6));
        personalData.setGender(personDataList.get(7));
        personalData.setDateOfBirth(personDataList.get(8));
        personalData.setPlaceOfBirth(personDataList.get(9).toUpperCase());
        personalData.setCountryOfBirth(personDataList.get(10).toUpperCase());
        personalData.setTaxNumber(faker.number().digits(8));
    }
}
